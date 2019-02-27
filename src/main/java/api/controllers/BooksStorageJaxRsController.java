package api.controllers;

import api.config.LocationConfig;
import api.error.exceptions.BookUnavailableException;
import com.google.common.annotations.VisibleForTesting;
import infra.data.mongodb.BooksRepository;
import services.books.BookService;
import services.dto.BookSpecification;
import services.health.PenguinHealthCheckService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/jax/resources/books")
@Produces(MediaType.APPLICATION_JSON)
public class BooksStorageJaxRsController {
    private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";
    private static final String CONTENT_TYPE = "Content-Type";
    private final BookService bookService;
    private final BookSpecificationDtoMapper bookSpecificationDtoMapper;
    private final PenguinHealthCheckService penguinHealthCheckService;

    @Inject
    private BooksRepository booksRepository;

    @Inject
    private LocationConfig locationConfig;

    public BooksStorageJaxRsController(final BookService bookService, final BookSpecificationDtoMapper bookSpecificationDtoMapper, final PenguinHealthCheckService penguinHealthCheckService) {
        this.bookService = bookService;
        this.bookSpecificationDtoMapper = bookSpecificationDtoMapper;
        this.penguinHealthCheckService = penguinHealthCheckService;
    }

    @POST
    public Response postBookResponse(@QueryParam("firstName") String firstName,
                                     @QueryParam("lastName") String lastName) {
        if (!checkIfAuthorExists(firstName, lastName)) {
            bookService.saveBooksForGivenAuthor(firstName, lastName);
            return Response.status(Response.Status.CREATED).header(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8).build();
        } else {
            return Response.status(Response.Status.FOUND).header(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8).location(getLocationUrl(firstName, lastName)).build();
        }
    }

    @GET
    public Response getBookSpecificationResponse(@QueryParam("firstName") String firstName,
                                                 @QueryParam("lastName") String lastName) {
        List<BookSpecification> bookSpecifications = bookService.readBookSpecification(firstName, lastName);
        if (checkIfAuthorExists(firstName, lastName)) {
            return Response.status(Response.Status.OK).header(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8).entity(bookSpecificationDtoMapper.fromBookSpecification(bookSpecifications)).build();
        } else {
            throw new BookUnavailableException("Books written by " + firstName + " " + lastName + " do not exist in Store");
        }
    }

    @VisibleForTesting
    Boolean checkIfAuthorExists(final String firstName, final String lastName) {
        return 0 != booksRepository.findByLastName(lastName).size() || 0 != booksRepository.findByFirstName(firstName).size();
    }

    @VisibleForTesting
    URI getLocationUrl(String firstName, String lastName) {
        return URI.create(locationConfig.getLocation() + "/" + firstName + "/" + lastName);
    }
}