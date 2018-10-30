package services.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookSpecificationTest {

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
        String firstName = "John";
        String lastName = "Smith";
        String bookTitle = "Away";
        String onSaleDate = "2015-03-04";

        BookSpecification bookSpecification = BookSpecification.builder()
                .authorFirstName(firstName).authorLastName(lastName).bookTitle(bookTitle)
                .onSaleDate(onSaleDate).build();

        assertThat(bookSpecification, notNullValue());
        assertThat(bookSpecification.getAuthorFirstName(), equalTo(firstName));
        assertThat(bookSpecification.getAuthorLastName(), equalTo(lastName));
        assertThat(bookSpecification.getBookTitle(), equalTo(bookTitle));
        assertThat(bookSpecification.getOnSaleDate(), equalTo(onSaleDate));
    }
}