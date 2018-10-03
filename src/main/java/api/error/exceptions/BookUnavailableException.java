package api.error.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public final class BookUnavailableException extends RuntimeException {

    public BookUnavailableException(String message) {
        super(message);
    }
}
