package api.error;

import api.dto.ErrorDto;
import api.error.exceptions.BookUnavailableException;
import api.error.exceptions.ForbiddenResourceException;
import api.error.exceptions.UnauthorizedUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookStorageExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(BookStorageExceptionHandler.class);

    @ExceptionHandler(ForbiddenResourceException.class)
    public ResponseEntity<ErrorDto> handleForbiddenResourceException(ForbiddenResourceException e) {
        LOG.error("Forbidden: ", e);
        ErrorDto errorDto = ErrorDto.builder().setMessage(e.getMessage()).setCode(HttpStatus.FORBIDDEN.name()).build();
        return new ResponseEntity<>(errorDto, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BookUnavailableException.class)
    public ResponseEntity<ErrorDto> handleBookUnavailableException(BookUnavailableException e) {
        LOG.error("Book does not exist in a store: ", e);
        ErrorDto errorDto = ErrorDto.builder().setCode(HttpStatus.NOT_FOUND.name()).setMessage(e.getMessage()).build();
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseEntity<ErrorDto> handleUnauthorizedUserException(UnauthorizedUserException e) {
        LOG.error("Unauthorized: ", e);
        ErrorDto errorDto = ErrorDto.builder().setCode(HttpStatus.UNAUTHORIZED.name()).setMessage(e.getMessage()).build();
        return new ResponseEntity<>(errorDto, HttpStatus.UNAUTHORIZED);
    }
}