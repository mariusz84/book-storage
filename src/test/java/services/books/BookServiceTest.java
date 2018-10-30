package services.books;

import infra.OperationsOnDb;
import infra.dto.BooksSpecificationDto;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import services.dto.BookSpecification;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Smith";
    private static final String BOOK_TITLE = "Away";
    private static final String ON_SALE_DATE = "2015-03-04";

    @Mock
    private OperationsOnDb operationsOnDb;

    @Mock
    private BookSpecificationMapper bookSpecificationMapper;

    @InjectMocks
    private BookService bookService;

    @BeforeClass
    public static void setup() {
        MockitoAnnotations.initMocks(BookServiceTest.class);
    }

    @Test
    public void testSaveBooksForGivenAuthor() {
        doNothing().when(operationsOnDb).writeBookToDbIfAuthorDoesNotExist(FIRST_NAME, LAST_NAME);

        bookService.saveBooksForGivenAuthor(FIRST_NAME, LAST_NAME);

        verify(operationsOnDb, times(1)).writeBookToDbIfAuthorDoesNotExist(FIRST_NAME, LAST_NAME);
    }

    @Test
    public void shouldReadBookSpecification() {
        BooksSpecificationDto booksSpecificationDto = new BooksSpecificationDto();
        booksSpecificationDto.setAuthorFirstName(FIRST_NAME);
        booksSpecificationDto.setAuthorLastName(LAST_NAME);
        booksSpecificationDto.setBookTitle(BOOK_TITLE);
        booksSpecificationDto.setOnSaleDate(ON_SALE_DATE);

        List<BooksSpecificationDto> bookList = new ArrayList();
        bookList.add(booksSpecificationDto);

        List<BookSpecification> bookSpecificationList = new ArrayList<>();
        bookSpecificationList.add(BookSpecification.builder().authorFirstName(FIRST_NAME)
                .authorLastName(LAST_NAME).bookTitle(BOOK_TITLE).onSaleDate(ON_SALE_DATE).build());

        when(operationsOnDb.readBooksFromDb(FIRST_NAME, LAST_NAME)).thenReturn(bookList);
        when(bookSpecificationMapper.fromBookSpecification(bookList)).thenReturn(bookSpecificationList);

        List<BookSpecification> booksSpecificationList = bookService.readBookSpecification(FIRST_NAME, LAST_NAME);

        assertThat(booksSpecificationList.get(0).getAuthorFirstName(), equalTo(bookList.get(0).getAuthorFirstName()));
        assertThat(booksSpecificationList.get(0).getAuthorLastName(), equalTo(bookList.get(0).getAuthorLastName()));
        assertThat(booksSpecificationList.get(0).getBookTitle(), equalTo(bookList.get(0).getBookTitle()));
        assertThat(booksSpecificationList.get(0).getOnSaleDate(), equalTo(bookList.get(0).getOnSaleDate()));
    }
}