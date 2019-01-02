import com.google.common.annotations.VisibleForTesting;
import infra.data.mongodb.BooksRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@ComponentScan({"api", "services", "infra"})
@EnableMongoRepositories(basePackageClasses = BooksRepository.class)
@EnableAutoConfiguration()
@VisibleForTesting
@EnableJms
public class BookStorageApplication {

    
    public static void main(final String[] args) {
        SpringApplication.run(BookStorageApplication.class, args);
//        ConfigurableApplicationContext context = SpringApplication.run(BookStorageApplication.class, args);

//        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
//
//        // Send a message with a POJO - the template reuse the message converter
//        System.out.println("Sending an email message.");
//        jmsTemplate.convertAndSend("jms/booksQueue", "haha");

    }
}