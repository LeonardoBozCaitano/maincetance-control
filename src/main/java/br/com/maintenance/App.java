package br.com.maintenance;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class App extends SpringBootServletInitializer {
    public static void main(String[] args) {
        new App().configure(new SpringApplicationBuilder(AppConfig.class)).run(args);
    }
}
