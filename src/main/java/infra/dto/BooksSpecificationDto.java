package infra.dto;

import java.util.Objects;

public class BooksSpecificationDto {
    private String authorFirstName;
    private String authorLastName;
    private String bookTitle;
    private String onSaleDate;

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getOnSaleDate() {
        return onSaleDate;
    }

    public void setOnSaleDate(String onSaleDate) {
        this.onSaleDate = onSaleDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksSpecificationDto that = (BooksSpecificationDto) o;
        return Objects.equals(authorFirstName, that.authorFirstName) &&
                Objects.equals(authorLastName, that.authorLastName) &&
                Objects.equals(bookTitle, that.bookTitle) &&
                Objects.equals(onSaleDate, that.onSaleDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorFirstName, authorLastName, bookTitle, onSaleDate);
    }
}