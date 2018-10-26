package infra.data.mongodb;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "authors")
public class Books {

    @Indexed
    public String lastName;

    public String firstName;
    public String title;
    public String onSaleDate;

    public Books() {
    }

    public Books(String firstName, String lastName, String title, String onSaleDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.onSaleDate = onSaleDate;
    }
}