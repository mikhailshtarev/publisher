package ru.shtarev.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.shtarev.publisher.service.PublisherService;

@SpringBootApplication
public class PublisherApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(PublisherApplication.class, args);
        PublisherService publisherService = run.getBean(PublisherService.class);
        publisherService.start();
    }

}
