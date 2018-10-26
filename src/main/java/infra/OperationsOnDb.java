package infra;

import infra.dto.BooksSpecificationDto;
import infra.data.mongodb.Books;
import infra.data.mongodb.BooksRepository;
import infra.dto.authors.Authors;
import infra.penguin_client.PenguinAuthorsClient;
import infra.penguin_client.PenguinWorksClient;
import infra.dto.works.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationsOnDb {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private PenguinAuthorsClient penguinAuthorsClient;

    @Autowired
    private PenguinWorksClient penguinWorksClient;

    public void writeBookToDbIfAuthorDoesNotExist(final String firstName, final String lastName) {
        Authors authors = penguinAuthorsClient.getPenguinAuthor(firstName, lastName);
        List<String> workIds = authors.getAuthor().getWorks().getWorks();
        workIds.forEach(workId -> {
            Work work = penguinWorksClient.getPenguinBook(workId);
            booksRepository.save(new Books(authors.getAuthor().getAuthorfirst(), authors.getAuthor().getAuthorlast(),
                    work.getTitleweb(), work.getOnsaledate()));
        });
    }

    public List<BooksSpecificationDto> readBooksFromDb(final String firstName, final String lastName) {
        List<Books> books = booksRepository.findAll();
        List<BooksSpecificationDto> booksSpeciifications = books.stream().map(book ->
                getBooksSpecification(book.firstName, book.lastName, book.title, book.onSaleDate)).collect(Collectors.toList());
        return booksSpeciifications;
    }

    private BooksSpecificationDto getBooksSpecification(String firstName, String lastName,
                                                        String title, String onSaleDate) {
        BooksSpecificationDto booksSpecificationDto = new BooksSpecificationDto();
        booksSpecificationDto.setAuthorFirstName(firstName);
        booksSpecificationDto.setAuthorLastName(lastName);
        booksSpecificationDto.setBookTitle(title);
        booksSpecificationDto.setOnSaleDate(onSaleDate);
        return booksSpecificationDto;
    }
}