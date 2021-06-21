package be.colruyt.e2e.ordermanagement.mySpringBatch1;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validator;
import java.time.ZonedDateTime;

@Configuration
public class AppConfig {

    @Bean
	public Customer customer () {
		return new Customer(1, "wim", "van den brande", ZonedDateTime.now());
	}

	@Bean
	public Validator validator() {
		return new org.springframework.validation.beanvalidation.LocalValidatorFactoryBean();
	}
}
