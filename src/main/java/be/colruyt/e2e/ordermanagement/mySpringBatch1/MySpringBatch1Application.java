package be.colruyt.e2e.ordermanagement.mySpringBatch1;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.wim.Customer;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.wim.Item;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.wim.Post;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@SpringBootApplication
@PropertySource({"application.properties", "metrics-sample.properties"})
@EnableJms
public class MySpringBatch1Application  {

	public static void main(String[] args) {
		// flavor 1: run spring batch and quit
//		System.exit(SpringApplication.exit(SpringApplication.run(MySpringBatch1Application.class, args)));
//		SpringApplication.run(MySpringBatch1Application.class, args);

		// flavor 2: start spring boot and usage spring-data-rest controllers
		ConfigurableApplicationContext ctx = SpringApplication.run(MySpringBatch1Application.class, args);
		RepositoryRestConfiguration restConfiguration = ctx.getBean(RepositoryRestConfiguration.class);
		restConfiguration.exposeIdsFor(Customer.class, Item.class, Post.class);
	}

//	@Bean
//	public JmsListenerContainerFactory<?> myFactory(
//			@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory,
//			DefaultJmsListenerContainerFactoryConfigurer configurer) {
//		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//		configurer.configure(factory, connectionFactory);
//		return factory;
//	}

	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
}
