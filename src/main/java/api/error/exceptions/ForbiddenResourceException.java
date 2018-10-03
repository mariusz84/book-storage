package api.error.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public final class ForbiddenResourceException extends RuntimeException {

	public ForbiddenResourceException(String message) {
        super(message);
    }
}
