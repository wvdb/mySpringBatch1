package be.colruyt.e2e.ordermanagement.mySpringBatch1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"application.properties", "metrics-sample.properties"})
public class MySpringBatch1Application {

	public static void main(String[] args) {
//		System.exit(SpringApplication.exit(SpringApplication.run(MySpringBatch1Application.class, args)));
		SpringApplication.run(MySpringBatch1Application.class, args);
	}

}
