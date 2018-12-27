package api.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class BooksSpecificationDtoTest {
    private String firstName = "John";
    private String lastName = "Smith";
    private String bookTitle = "Away";
    private String onSaleDate = "2015-03-04";

    @Test
    public void shouldCheckEqualsAndHashCode() {
        EqualsVerifier.forClass(BooksSpecificationDto.class)
                .verify();
    }

    @Test
    public void shouldCreateEmptyObjectViaBuilder() {
        BooksSpecificationDto booksSpecificationDto = BooksSpecificationDto.builder().build();

        assertThat(booksSpecificationDto, notNullValue());
        assertThat(booksSpecificationDto.getAuthorFirstName(), nullValue());
        assertThat(booksSpecificationDto.getAuthorLastName(), nullValue());
        assertThat(booksSpecificationDto.getBookTitle(), nullValue());
        assertThat(booksSpecificationDto.getOnSaleDate(), nullValue());
    }

    @Test
    public void shouldCreateFullObjectViaBuilder() {
        BooksSpecificationDto booksSpecificationDto = BooksSpecificationDto.builder()
                .authorFirstName(firstName).authorLastName(lastName).bookTitle(bookTitle)
                .onSaleDate(onSaleDate).build();

        assertThat(booksSpecificationDto, notNullValue());
        assertThat(booksSpecificationDto.getAuthorFirstName(), equalTo(firstName));
        assertThat(booksSpecificationDto.getAuthorLastName(), equalTo(lastName));
        assertThat(booksSpecificationDto.getBookTitle(), equalTo(bookTitle));
        assertThat(booksSpecificationDto.getOnSaleDate(), equalTo(onSaleDate));
    }

    @Test
    public void shouldCheckToString() {
        BooksSpecificationDto bookSpecification = BooksSpecificationDto.builder()
                .authorFirstName(firstName).authorLastName(lastName).bookTitle(bookTitle)
                .onSaleDate(onSaleDate).build();

        String bookSpecificationValue = "BooksSpecificationDto{authorFirstName=John, authorLastName=Smith, bookTitle=Away, onSaleDate=2015-03-04}";
        assertThat(bookSpecification.toString(), equalTo(bookSpecificationValue));
    }
}