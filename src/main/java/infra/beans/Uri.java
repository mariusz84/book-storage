package infra.beans;

import infra.UriProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Uri {

    @Bean
    public UriProvider uriProvider(){
        return new UriProvider();
    }
}
