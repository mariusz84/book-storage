package api.controllers;

import api.dto.BooksSpecificationDto;
import services.dto.BookSpecification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@Component
public class BookSpecificationDtoMapper {

    public List<BooksSpecificationDto> fromBookSpecification(final List<BookSpecification> bookSpecifications) {
        checkArgument(bookSpecifications != null);
        List<BooksSpecificationDto> booksSpecificationsDto = bookSpecifications.stream().map(bookSpecification -> BooksSpecificationDto.builder().authorFirstName(bookSpecification.getAuthorFirstName()).authorLastName(bookSpecification.getAuthorLastName())
                .bookTitle(bookSpecification.getBookTitle()).onSaleDate(bookSpecification.getOnSaleDate()).build()).collect(Collectors.toList());
        return booksSpecificationsDto;
    }
}