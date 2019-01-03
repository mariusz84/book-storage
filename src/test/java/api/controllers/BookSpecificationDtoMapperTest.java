package api.controllers;

import api.dto.BooksSpecificationDto;
import org.junit.Test;
import services.dto.BookSpecification;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BookSpecificationDtoMapperTest {

    private final BookSpecificationDtoMapper bookSpecificationDtoMapper = new BookSpecificationDtoMapper();

    @Test
    public void shouldMapEmptyObject() {
        List<BookSpecification> listOfBooks = new ArrayList();
        listOfBooks.add(BookSpecification.builder().build());

        List<BooksSpecificationDto> booksSpecificationList = bookSpecificationDtoMapper.fromBookSpecification(listOfBooks);

        assertThat(booksSpecificationList, notNullValue());
        assertThat(booksSpecificationList.get(0).getAuthorFirstName(), nullValue());
        assertThat(booksSpecificationList.get(0).getAuthorLastName(), nullValue());
        assertThat(booksSpecificationList.get(0).getBookTitle(), nullValue());
        assertThat(booksSpecificationList.get(0).getOnSaleDate(), nullValue());
    }

    @Test
    public void shouldMapFullObject() {
        String firstName = "John";
        String lastName = "Smith";
        String bookTitle = "Away";
        String onSaleDate = "2015-03-04";

        BookSpecification booksSpecification = BookSpecification.builder()
                .authorFirstName(firstName).authorLastName(lastName).bookTitle(bookTitle)
                .onSaleDate(onSaleDate).build();

        List<BookSpecification> listOfBooks = new ArrayList();
        listOfBooks.add(booksSpecification);

        List<BooksSpecificationDto> booksSpecificationList = bookSpecificationDtoMapper.fromBookSpecification(listOfBooks);

        assertThat(booksSpecificationList, notNullValue());
        assertThat(booksSpecificationList.get(0).getAuthorFirstName(), equalTo(firstName));
        assertThat(booksSpecificationList.get(0).getAuthorLastName(), equalTo(lastName));
        assertThat(booksSpecificationList.get(0).getBookTitle(), equalTo(bookTitle));
        assertThat(booksSpecificationList.get(0).getOnSaleDate(), equalTo(onSaleDate));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionOnNullParameter() {
        bookSpecificationDtoMapper.fromBookSpecification(null);
    }
}