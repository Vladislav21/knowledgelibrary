package ru.cosysoft.knowledgelibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * API Documentation:
 * http://localhost:8080/swagger-ui.html
 * https://knowledgelibrary.herokuapp.com/swagger-ui.html
 */
@SpringBootApplication
public class KnowledgelibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnowledgelibraryApplication.class, args);
    }

}
