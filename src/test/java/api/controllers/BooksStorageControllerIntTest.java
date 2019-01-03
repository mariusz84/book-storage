package api.controllers;

import api.config.LocationConfig;
import api.dto.BooksSpecificationDto;
import api.error.exceptions.BookUnavailableException;
import infra.data.mongodb.Books;
import infra.data.mongodb.BooksRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import services.books.BookService;
import services.dto.BookSpecification;
import services.health.PenguinHealthCheckService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = api.controllers.BooksStorageController.class)
@AutoConfigureMockMvc
@ContextConfiguration
@EnableWebMvc
public class BooksStorageControllerIntTest {
    private static final String SERVICE_ENDPOINT = "/resources/books";
    private static final String APPLICATION_JSON_TYPE = "application/json";

    private static final String firstName = "John";
    private static final String lastName = "Smith";
    private static final String bookTitle = "Away";
    private static final String onSaleDate = "2015-03-04";

    private static List<BookSpecification> listOfBooksReturnedByBookService = new ArrayList<>();
    private static List<BooksSpecificationDto> listOfBooksReturnedByController = new ArrayList<>();
    private static List<Books> booksList = new ArrayList<>();
    private static List<Books> emptyBooksList = new ArrayList<>();
    private static Books book = new Books(firstName, lastName, bookTitle, onSaleDate);

    private static final BookSpecification booksSpecification = BookSpecification.builder()
            .authorFirstName(firstName).authorLastName(lastName).bookTitle(bookTitle)
            .onSaleDate(onSaleDate).build();

    private static final BooksSpecificationDto booksSpecificationDto = BooksSpecificationDto.builder()
            .authorFirstName(firstName).authorLastName(lastName).bookTitle(bookTitle)
            .onSaleDate(onSaleDate).build();

    @MockBean
    private BookService bookService;

    @MockBean
    private BookSpecificationDtoMapper bookSpecificationDtoMapper;

    @MockBean
    private PenguinHealthCheckService penguinHealthCheckService;

    @MockBean
    BooksRepository booksRepository;

    @MockBean
    LocationConfig locationConfig;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @InjectMocks
    private BooksStorageController booksStorageController;

    @BeforeClass
    public static void setup() {
        listOfBooksReturnedByBookService.add(booksSpecification);
        listOfBooksReturnedByController.add(booksSpecificationDto);
        booksList.add(book);
    }

    @Before
    public void prepareTest() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(value = "admin")
    @Test
    public void shouldReturn201OnBookSpecificationCreationWhenBookDoesNotExistInStore() throws Exception {
        when(booksRepository.findByFirstName(firstName)).thenReturn(emptyBooksList);
        when(booksRepository.findByLastName(lastName)).thenReturn(emptyBooksList);
        doNothing().when(bookService).saveBooksForGivenAuthor(firstName, lastName);

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post(SERVICE_ENDPOINT).with(csrf()).param("firstName", firstName)
                .param("lastName", lastName).contentType(APPLICATION_JSON_TYPE));
        resultActions.andExpect(status().isCreated());
    }

    @WithMockUser(value = "admin")
    @Test
    public void shouldReturn302OnBookSpecificationCreationWhenBookExistsInStore() throws Exception {
        BooksStorageController booksStorageController = new BooksStorageController(bookService, bookSpecificationDtoMapper, penguinHealthCheckService);
        BooksStorageController bookStorageSpy = Mockito.spy(booksStorageController);

        when(booksRepository.findByFirstName(firstName)).thenReturn(booksList);
        when(booksRepository.findByLastName(lastName)).thenReturn(booksList);
        doReturn(URI.create(SERVICE_ENDPOINT + "/" + firstName + "/" + lastName)).when(bookStorageSpy).getLocationUrl(firstName, lastName);

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post(SERVICE_ENDPOINT).with(csrf()).param("firstName", firstName)
                .param("lastName", lastName).contentType(APPLICATION_JSON_TYPE));
        resultActions.andExpect(status().isFound());
    }

    @WithMockUser(value = "admin")
    @Test
    public void shouldReturn200OnGettingBookSpecification() throws Exception {
        when(booksRepository.findByFirstName(firstName)).thenReturn(booksList);
        when(booksRepository.findByLastName(lastName)).thenReturn(booksList);
        when(bookService.readBookSpecification(firstName, lastName)).thenReturn(listOfBooksReturnedByBookService);
        when(bookSpecificationDtoMapper.fromBookSpecification(listOfBooksReturnedByBookService)).thenReturn(listOfBooksReturnedByController);

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(SERVICE_ENDPOINT).param("firstName", firstName)
                .param("lastName", lastName).contentType(APPLICATION_JSON_TYPE));
        resultActions.andExpect(status().isOk());
    }

//    @WithMockUser(value = "admin")
//    @Test(expected = BookUnavailableException.class)
//    public void shouldThrowBookUnavailableExceptionOnGettingBookSpecificationIfBookDoesNotExist() throws Exception {
//        when(booksRepository.findByFirstName(firstName)).thenReturn(emptyBooksList);
//        when(booksRepository.findByLastName(lastName)).thenReturn(emptyBooksList);
//        when(bookService.readBookSpecification(firstName, lastName)).thenReturn(listOfBooksReturnedByBookService);
//
//        mvc.perform(MockMvcRequestBuilders.get(SERVICE_ENDPOINT).param("firstName", firstName)
//                .param("lastName", lastName).contentType(APPLICATION_JSON_TYPE));
//    }
}