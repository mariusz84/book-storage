package api.dto;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ErrorDtoTest {

    @Test
    public void shouldCreateEmptyObjectViaBuilder() {
        ErrorDto errorDto = ErrorDto.builder().build();

        assertThat(errorDto, notNullValue());
        assertThat(errorDto.getCode(), nullValue());
        assertThat(errorDto.getMessage(), nullValue());
    }

    @Test
    public void shouldCreateFullObjectViaBuilder() {
        String code = "10100";
        String message = "Error occured";

        ErrorDto errorDto = ErrorDto.builder().setCode(code).setMessage(message).build();

        assertThat(errorDto, notNullValue());
        assertThat(errorDto.getCode(), equalTo(code));
        assertThat(errorDto.getMessage(), equalTo(message));
    }
}