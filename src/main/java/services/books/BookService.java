package services.books;

import infra.OperationsOnDb;
import org.springframework.stereotype.Service;
import infra.dto.BooksSpecificationDto;
import services.dto.BookSpecification;

import java.util.List;

@Service
public class BookService {
    private final OperationsOnDb operationsOnDb;
    private final BookSpecificationMapper bookSpecificationMapper;

    public BookService(final BookSpecificationMapper bookSpecificationMapper, final OperationsOnDb operationsOnDb) {
        this.bookSpecificationMapper = bookSpecificationMapper;
        this.operationsOnDb = operationsOnDb;
    }

    public void saveBooksForGivenAuthor(final String firstName, final String lastName) {
        operationsOnDb.writeBookToDbIfAuthorDoesNotExist(firstName, lastName);
    }

    public List<BookSpecification> readBookSpecification(final String firstName, final String lastName) {
        List<BooksSpecificationDto> booksSpecificationsDto = operationsOnDb.readBooksFromDb(firstName, lastName);
        return bookSpecificationMapper.fromBookSpecification(booksSpecificationsDto);
    }
}