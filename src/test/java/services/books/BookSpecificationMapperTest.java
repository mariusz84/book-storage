package services.books;

import infra.dto.BooksSpecificationDto;
import org.junit.Test;
import services.dto.BookSpecification;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

public class BookSpecificationMapperTest {

    private final BookSpecificationMapper bookSpecificationMapper = new BookSpecificationMapper();

    @Test
    public void shouldMapEmptyObject() {
        List<BooksSpecificationDto> bookList = new ArrayList();
        bookList.add(new BooksSpecificationDto());

        List<BookSpecification> bookSpecificationList = bookSpecificationMapper.fromBookSpecification(bookList);

        assertThat(bookSpecificationList, notNullValue());
        assertThat(bookSpecificationList.get(0).getAuthorFirstName(), nullValue());
        assertThat(bookSpecificationList.get(0).getAuthorLastName(), nullValue());
        assertThat(bookSpecificationList.get(0).getBookTitle(), nullValue());
        assertThat(bookSpecificationList.get(0).getOnSaleDate(), nullValue());
    }

    @Test
    public void shouldMapFullObject() {
        String firstName = "John";
        String lastName = "Smith";
        String bookTitle = "Away";
        String onSaleDate = "2015-03-04";

        List<BooksSpecificationDto> listOfBooks = new ArrayList();

        BooksSpecificationDto booksSpecificationDto = new BooksSpecificationDto();
        booksSpecificationDto.setAuthorFirstName(firstName);
        booksSpecificationDto.setAuthorLastName(lastName);
        booksSpecificationDto.setBookTitle(bookTitle);
        booksSpecificationDto.setOnSaleDate(onSaleDate);

        listOfBooks.add(booksSpecificationDto);

        List<BookSpecification> bookSpecificationList = bookSpecificationMapper.fromBookSpecification(listOfBooks);

        assertThat(bookSpecificationList, notNullValue());
        assertThat(bookSpecificationList.get(0).getAuthorFirstName(), equalTo(firstName));
        assertThat(bookSpecificationList.get(0).getAuthorLastName(), equalTo(lastName));
        assertThat(bookSpecificationList.get(0).getBookTitle(), equalTo(bookTitle));
        assertThat(bookSpecificationList.get(0).getOnSaleDate(), equalTo(onSaleDate));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionOnNullParameter() {
        bookSpecificationMapper.fromBookSpecification(null);
    }
}