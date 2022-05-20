package be.colruyt.e2e.ordermanagement.mySpringBatch1;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

        String barCode = "12345";

        System.out.println("resultaat = " + (StringUtils.repeat( "0", 14 ) + barCode).substring( barCode.length() ));

        log.info("testje");
    }
}
