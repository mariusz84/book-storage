package infra.dto.authors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Authors {

    private Author author;

    public Authors() {
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Authors{" + "FirstName='" + author.getAuthorfirst() + '\'' +
                ", LastName='" + author.getAuthorlast() + '\'' +
                '}';
    }
}