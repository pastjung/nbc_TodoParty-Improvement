package com.study.todoparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  // TimeStamped 사용
@SpringBootApplication
public class TodoPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoPartyApplication.class, args);
    }

}
