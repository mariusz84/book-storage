package api.error.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public final class UnauthorizedUserException extends RuntimeException {

	public UnauthorizedUserException(String message) {
        super(message);
    }
}
