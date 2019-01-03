package services.dto;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public final class BookSpecification {
    private final String authorFirstName;
    private final String authorLastName;
    private final String bookTitle;
    private final String onSaleDate;

    private BookSpecification(Builder builder) {
        this.authorFirstName = builder.authorFirstName;
        this.authorLastName = builder.authorLastName;
        this.bookTitle = builder.bookTitle;
        this.onSaleDate = builder.onSaleDate;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getOnSaleDate() {
        return onSaleDate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("authorFirstName", authorFirstName)
                .add("authorLastName", authorLastName)
                .add("bookTitle", bookTitle)
                .add("onSaleDate", onSaleDate)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookSpecification that = (BookSpecification) o;
        return Objects.equals(authorFirstName, that.authorFirstName) &&
                Objects.equals(authorLastName, that.authorLastName) &&
                Objects.equals(bookTitle, that.bookTitle) &&
                Objects.equals(onSaleDate, that.onSaleDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorFirstName, authorLastName, bookTitle, onSaleDate);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String authorFirstName;
        private String authorLastName;
        private String bookTitle;
        private String onSaleDate;

        public Builder authorFirstName(String authorFirstName) {
            this.authorFirstName = authorFirstName;
            return this;
        }

        public Builder authorLastName(String authorLastName) {
            this.authorLastName = authorLastName;
            return this;
        }

        public Builder bookTitle(String bookTitle) {
            this.bookTitle = bookTitle;
            return this;
        }

        public Builder onSaleDate(String onSaleDate) {
            this.onSaleDate = onSaleDate;
            return this;
        }

        public BookSpecification build() {
            return new BookSpecification(this);
        }
    }
}