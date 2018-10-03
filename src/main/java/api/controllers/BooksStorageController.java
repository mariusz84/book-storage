package api.controllers;

import api.error.exceptions.BookUnavailableException;
import infra.data.mongodb.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.books.BookService;
import services.dto.BookSpecification;
import services.health.PenguinHealthCheckService;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

import static org.springframework.http.MediaType.valueOf;

@RestController
@RequestMapping(value = "/resources/books")
@Produces(MediaType.APPLICATION_JSON)
public class BooksStorageController {
    private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";
    private final BookService bookService;
    private final BookSpecificationDtoMapper bookSpecificationDtoMapper;
    private final PenguinHealthCheckService penguinHealthCheckService;

    @Autowired
    private BooksRepository booksRepository;

    public BooksStorageController(final BookService bookService, final BookSpecificationDtoMapper bookSpecificationDtoMapper, final PenguinHealthCheckService penguinHealthCheckService) {
        this.bookService = bookService;
        this.bookSpecificationDtoMapper = bookSpecificationDtoMapper;
        this.penguinHealthCheckService = penguinHealthCheckService;
    }

    @PostMapping()
    public ResponseEntity postBookResponse(@RequestParam(name = "firstName") String firstName,
                                           @RequestParam(name = "lastName") String lastName) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(valueOf(APPLICATION_JSON_CHARSET_UTF_8));
        if (!checkIfAuthorExists(firstName, lastName)) {
            bookService.saveBooksForGivenAuthor(firstName, lastName);
            return new ResponseEntity(responseHeaders, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(responseHeaders, HttpStatus.FOUND);
        }
    }

    @GetMapping(params = {"firstName", "lastName"})
    @ResponseBody
    public ResponseEntity getBookSpecificationResponse(@RequestParam(name = "firstName") String firstName,
                                                       @RequestParam(name = "lastName") String lastName) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(valueOf(APPLICATION_JSON_CHARSET_UTF_8));
        List<BookSpecification> bookSpecifications = bookService.readBookSpecification(firstName, lastName);
        if (checkIfAuthorExists(firstName, lastName)) {
            return new ResponseEntity(bookSpecificationDtoMapper.fromBookSpecification(bookSpecifications), responseHeaders, HttpStatus.OK);
        } else {
            throw new BookUnavailableException("Books written by " + firstName + " " + lastName+ " do not exist in Store");
        }
    }

    private Boolean checkIfAuthorExists(final String firstName, final String lastName) {
        if (0 != booksRepository.findByLastName(lastName).size() || 0 != booksRepository.findByFirstName(firstName).size()) {
            return true;
        } else
            return false;
    }
}