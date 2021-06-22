package be.colruyt.e2e.ordermanagement.mySpringBatch1;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.controller.CustomerItemLowerCaseProcessor;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.controller.CustomerItemUpperCaseProcessor;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.controller.JobCompletionNotificationListener;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.controller.KafkaItemWriteListener;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.kafka.KafkaItemWriter;
import org.springframework.batch.item.kafka.builder.KafkaItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.kafka.core.KafkaTemplate;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@Slf4j
public class MyBatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${app.springBatchInputFileName}")
    private Resource inputFileResource;

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    @Bean
    public FlatFileItemReader<Customer> reader() {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("personItemReader")
                .resource(inputFileResource)
                .delimited()
                .names("firstName", "lastName")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {{
                    setTargetType(Customer.class);
                }})
                .build();
    }

    @Bean
    public CustomerItemUpperCaseProcessor upperCaseProcessor() {
        return new CustomerItemUpperCaseProcessor();
    }

    @Bean
    public CustomerItemLowerCaseProcessor lowerCaseProcessor() {
        return new CustomerItemLowerCaseProcessor();
    }

    @Bean(name = "JdbcBatchItemWriter")
    public JdbcBatchItemWriter<Customer> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Customer>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO customer (first_name, last_name) VALUES (:firstName, :lastName)")
                .dataSource(dataSource)
                .build();
    }

    @Bean(name = "KafkaItemWriter")
    public KafkaItemWriter<String, Customer> writer(KafkaTemplate<String, Customer> kafkaTemplate) {
        return new KafkaItemWriterBuilder<String, Customer>()
                .kafkaTemplate(kafkaTemplate)
                .itemKeyMapper(customer -> Integer.toString(customer.getCustomerId()))
                .build();
    }

    @Bean
    public Job importCustomerJob(JobCompletionNotificationListener listener, Step step1, Step step2, Step step3) {
        return jobBuilderFactory.get("importCustomerJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step1)
                .next(step2)
                .next(step3)
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Customer> writer) {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(10)
                .reader(reader())
                .processor(upperCaseProcessor())
                .writer(writer)
                .build();
    }

    @Bean
    public Step step2(JdbcBatchItemWriter<Customer> writer) {
        return stepBuilderFactory.get("step2")
                .<Customer, Customer>chunk(10)
                .reader(reader())
                .processor(lowerCaseProcessor())
                .writer(writer)
                .build();
    }

    @Bean
    public Step step3(KafkaItemWriter<String, Customer> writer) {
        return stepBuilderFactory.get("step3")
                .<Customer, Customer>chunk(10)
                .reader(reader())
                .processor(lowerCaseProcessor())
                .writer(writer)
                .listener(new KafkaItemWriteListener())
                .build();
    }

}

