package api.error;

import api.dto.ErrorDto;
import api.error.exceptions.BookUnavailableException;
import api.error.exceptions.ForbiddenResourceException;
import api.error.exceptions.UnauthorizedUserException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class BookStorageExceptionHandlerTest {

    @InjectMocks
    private BookStorageExceptionHandler bookStorageExceptionHandler;

    @Test
    public void shoudHandleForbiddenResourceException() {
        String errorMessage = "Error message";
        ForbiddenResourceException exception = new ForbiddenResourceException(errorMessage);
        ResponseEntity<ErrorDto> responseEntity = bookStorageExceptionHandler.handleForbiddenResourceException(exception);

        assertThat(responseEntity, notNullValue());
        assertThat(responseEntity.getBody().getMessage(), equalTo(errorMessage));
    }

    @Test
    public void shouldHandleBookUnavailableException() {
        String errorMessage = "Error message";
        UnauthorizedUserException exception = new UnauthorizedUserException(errorMessage);
        ResponseEntity<ErrorDto> responseEntity = bookStorageExceptionHandler.handleUnauthorizedUserException(exception);

        assertThat(responseEntity, notNullValue());
        assertThat(responseEntity.getBody().getMessage(), equalTo(errorMessage));
    }

    @Test
    public void shouldHandleUnauthorizedUserException() {
        String errorMessage = "Error message";
        BookUnavailableException exception = new BookUnavailableException(errorMessage);
        ResponseEntity<ErrorDto> responseEntity = bookStorageExceptionHandler.handleBookUnavailableException(exception);

        assertThat(responseEntity, notNullValue());
        assertThat(responseEntity.getBody().getMessage(), equalTo(errorMessage));
    }
}