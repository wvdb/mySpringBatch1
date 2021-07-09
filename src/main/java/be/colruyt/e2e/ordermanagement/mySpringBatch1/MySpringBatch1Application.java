package be.colruyt.e2e.ordermanagement.mySpringBatch1;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Customer;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Item;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Post;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
@PropertySource({"application.properties", "metrics-sample.properties"})
public class MySpringBatch1Application  {

	public static void main(String[] args) {
//		System.exit(SpringApplication.exit(SpringApplication.run(MySpringBatch1Application.class, args)));
//		SpringApplication.run(MySpringBatch1Application.class, args);

		ConfigurableApplicationContext ctx = SpringApplication.run(MySpringBatch1Application.class, args);

		RepositoryRestConfiguration restConfiguration = ctx.getBean(RepositoryRestConfiguration.class);

		restConfiguration.exposeIdsFor(Customer.class, Item.class, Post.class);
	}

}
