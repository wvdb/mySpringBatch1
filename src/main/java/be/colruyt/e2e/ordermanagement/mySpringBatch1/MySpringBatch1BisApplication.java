package be.colruyt.e2e.ordermanagement.mySpringBatch1;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableBatchProcessing
public class MySpringBatch1BisApplication {
	public static void main(String[] args) {
		System.exit(SpringApplication
				.exit(SpringApplication.run(MySpringBatch1BisApplication.class, args)));
	}
}
