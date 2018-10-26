package infra.data.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BooksRepository extends MongoRepository<Books, String> {
    List<Books> findByFirstName(String firstName);

    List<Books> findByLastName(String lastName);
}