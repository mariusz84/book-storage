package infra.dto;

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
}