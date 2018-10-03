package services.books;

import infra.dto.BooksSpecificationDto;
import org.springframework.stereotype.Component;
import services.dto.BookSpecification;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@Component
public class BookSpecificationMapper {

    public List<BookSpecification> fromBookSpecification(final List<BooksSpecificationDto> booksSpecificationsDto) {
        checkArgument(booksSpecificationsDto != null);
        List<BookSpecification> bookSpecifications = booksSpecificationsDto.stream().map(booksSpecificationDto ->
                BookSpecification.builder().authorFirstName(booksSpecificationDto.getAuthorFirstName()).authorLastName(booksSpecificationDto.getAuthorLastName())
                        .bookTitle(booksSpecificationDto.getBookTitle()).onSaleDate(booksSpecificationDto.getOnSaleDate()).build()).collect(Collectors.toList());
        return bookSpecifications;
    }
}