package it.andreascrimieri.test.configuration;

import it.andreascrimieri.test.mapper.UserMapper;
import it.andreascrimieri.test.mapper.UserMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public UserMapper userMapper() {
        return new UserMapperImpl();
    }
}
