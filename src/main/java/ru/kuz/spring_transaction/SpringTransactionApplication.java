package ru.kuz.spring_transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringTransactionApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(SpringTransactionApplication.class, args);
        System.out.println(context);
    }

}
