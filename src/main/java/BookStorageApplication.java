import com.google.common.annotations.VisibleForTesting;
import infra.data.mongodb.BooksRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"api", "services", "infra"})
@EnableMongoRepositories(basePackageClasses = BooksRepository.class)
@EnableAutoConfiguration()
@VisibleForTesting
public class BookStorageApplication {
    public static void main(final String[] args) {
        SpringApplication.run(BookStorageApplication.class, args);
    }
}