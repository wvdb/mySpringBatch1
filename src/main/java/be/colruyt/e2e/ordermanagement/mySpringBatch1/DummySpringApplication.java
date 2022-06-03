package be.colruyt.e2e.ordermanagement.mySpringBatch1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@SpringBootApplication
@PropertySource({"application.properties"})
public class DummySpringApplication {
    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(DummySpringApplication.class, args)));
        SpringApplication.run(DummySpringApplication.class, args);

        log.info("testje");
    }
}
