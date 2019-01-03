package services.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookSpecificationTest {
    private String firstName = "John";
    private String lastName = "Smith";
    private String bookTitle = "Away";
    private String onSaleDate = "2015-03-04";

    @Test
    public void shouldCheckEqualsAndHashCode() {
        EqualsVerifier.forClass(BookSpecification.class)
                .verify();
    }

    @Test
    public void shouldCreateEmptyObjectViaBuilder() {
        BookSpecification bookSpecification = BookSpecification.builder().build();

        assertThat(bookSpecification, notNullValue());
        assertThat(bookSpecification.getAuthorFirstName(), nullValue());
        assertThat(bookSpecification.getAuthorLastName(), nullValue());
        assertThat(bookSpecification.getBookTitle(), nullValue());
        assertThat(bookSpecification.getOnSaleDate(), nullValue());
    }

    @Test
    public void shouldCreateFullObjectViaBuilder() {
        BookSpecification bookSpecification = BookSpecification.builder()
                .authorFirstName(firstName).authorLastName(lastName).bookTitle(bookTitle)
                .onSaleDate(onSaleDate).build();

        assertThat(bookSpecification, notNullValue());
        assertThat(bookSpecification.getAuthorFirstName(), equalTo(firstName));
        assertThat(bookSpecification.getAuthorLastName(), equalTo(lastName));
        assertThat(bookSpecification.getBookTitle(), equalTo(bookTitle));
        assertThat(bookSpecification.getOnSaleDate(), equalTo(onSaleDate));
    }

    @Test
    public void shouldCheckToString() {
        BookSpecification bookSpecification = BookSpecification.builder()
                .authorFirstName(firstName).authorLastName(lastName).bookTitle(bookTitle)
                .onSaleDate(onSaleDate).build();

        String bookSpecificationValue = "BookSpecification{authorFirstName=John, authorLastName=Smith, bookTitle=Away, onSaleDate=2015-03-04}";
        assertThat(bookSpecification.toString(), equalTo(bookSpecificationValue));
    }
}