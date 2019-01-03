package api.controllers;

import api.dto.BooksSpecificationDto;
import api.error.exceptions.BookUnavailableException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import services.books.BookService;
import services.dto.BookSpecification;
import services.health.PenguinHealthCheckService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BooksStorageControllerIntegrationTest {
    private static final String SERVICE_ENDPOINT = "/resources/books";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String APPLICATION_JSON_TYPE = "application/json;charset=UTF-8";

    private static final String firstName = "John";
    private static final String lastName = "Smith";
    private static final String bookTitle = "Away";
    private static final String onSaleDate = "2015-03-04";

    private static List<BookSpecification> listOfBooksReturnedByBookService;
    private static List<BooksSpecificationDto> listOfBooksReturnedByController;

    private static final BookSpecification booksSpecification = BookSpecification.builder()
            .authorFirstName(firstName).authorLastName(lastName).bookTitle(bookTitle)
            .onSaleDate(onSaleDate).build();

    private static final BooksSpecificationDto booksSpecificationDto = BooksSpecificationDto.builder()
            .authorFirstName(firstName).authorLastName(lastName).bookTitle(bookTitle)
            .onSaleDate(onSaleDate).build();

    @Mock
    private BookService bookService;

    @Mock
    private BookSpecificationDtoMapper bookSpecificationDtoMapper;

    @Mock
    private PenguinHealthCheckService penguinHealthCheckService;

    @BeforeClass
    public static void setup() {
        listOfBooksReturnedByBookService = new ArrayList();
        listOfBooksReturnedByBookService.add(booksSpecification);

        listOfBooksReturnedByController = new ArrayList();
        listOfBooksReturnedByController.add(booksSpecificationDto);
    }

    @Test
    public void shouldReturn201OnBookSpecificationCreationWhenBookDoesNotExistInStore() {
        BooksStorageController booksStorageController = new BooksStorageController(bookService, bookSpecificationDtoMapper, penguinHealthCheckService);
        BooksStorageController bookStorageSpy = Mockito.spy(booksStorageController);

        doReturn(false).when(bookStorageSpy).checkIfAuthorExists(firstName, lastName);
        doNothing().when(bookService).saveBooksForGivenAuthor(firstName, lastName);

        ResponseEntity response = bookStorageSpy.postBookResponse(firstName, lastName);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(response.getHeaders().get(CONTENT_TYPE_HEADER).get(0), equalTo(APPLICATION_JSON_TYPE));
    }

    @Test
    public void shouldReturn302OnBookSpecificationCreationWhenBookExistsInStore() {
        BooksStorageController booksStorageController = new BooksStorageController(bookService, bookSpecificationDtoMapper, penguinHealthCheckService);
        BooksStorageController bookStorageSpy = Mockito.spy(booksStorageController);

        doReturn(true).when(bookStorageSpy).checkIfAuthorExists(firstName, lastName);
        doReturn(URI.create(SERVICE_ENDPOINT + "/" + firstName + "/" + lastName)).when(bookStorageSpy).getLocationUrl(firstName, lastName);

        ResponseEntity response = bookStorageSpy.postBookResponse(firstName, lastName);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.FOUND));
        assertThat(response.getHeaders().get(CONTENT_TYPE_HEADER).get(0), equalTo(APPLICATION_JSON_TYPE));
    }

    @Test
    public void shouldReturn200OnGettingBookSpecification() {
        BooksStorageController booksStorageController = new BooksStorageController(bookService, bookSpecificationDtoMapper, penguinHealthCheckService);
        BooksStorageController bookStorageSpy = Mockito.spy(booksStorageController);

        doReturn(true).when(bookStorageSpy).checkIfAuthorExists(firstName, lastName);
        when(bookService.readBookSpecification(firstName, lastName)).thenReturn(listOfBooksReturnedByBookService);
        when(bookSpecificationDtoMapper.fromBookSpecification(listOfBooksReturnedByBookService)).thenReturn(listOfBooksReturnedByController);

        ResponseEntity response = bookStorageSpy.getBookSpecificationResponse(firstName, lastName);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getHeaders().get(CONTENT_TYPE_HEADER).get(0), equalTo(APPLICATION_JSON_TYPE));

        List<BooksSpecificationDto> booksSpecificationResponse = (List<BooksSpecificationDto>) response.getBody();

        assertThat(booksSpecificationResponse.get(0).getAuthorFirstName(), equalTo(firstName));
        assertThat(booksSpecificationResponse.get(0).getAuthorLastName(), equalTo(lastName));
        assertThat(booksSpecificationResponse.get(0).getBookTitle(), equalTo(bookTitle));
        assertThat(booksSpecificationResponse.get(0).getOnSaleDate(), equalTo(onSaleDate));
    }

    @Test(expected = BookUnavailableException.class)
    public void shouldThrowBookUnavailableExceptionOnGettingBookSpecificationIfBookDoesNotExist() {
        BooksStorageController booksStorageController = new BooksStorageController(bookService, bookSpecificationDtoMapper, penguinHealthCheckService);
        BooksStorageController bookStorageSpy = Mockito.spy(booksStorageController);

        doReturn(false).when(bookStorageSpy).checkIfAuthorExists(firstName, lastName);
        when(bookService.readBookSpecification(firstName, lastName)).thenReturn(listOfBooksReturnedByBookService);

        bookStorageSpy.getBookSpecificationResponse(firstName, lastName);
    }
}