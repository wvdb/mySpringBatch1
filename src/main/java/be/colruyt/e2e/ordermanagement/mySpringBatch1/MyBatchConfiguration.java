package be.colruyt.e2e.ordermanagement.mySpringBatch1;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.controller.*;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.kafka.KafkaItemWriter;
import org.springframework.batch.item.kafka.builder.KafkaItemWriterBuilder;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.core.KafkaTemplate;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableBatchProcessing
@Slf4j
public class MyBatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${app.springBatchInputFileNameStep1}")
    private Resource inputFileResourceStep1;

    @Value("${app.springBatchInputFileNameStep3}")
    private Resource inputFileResourceStep3;

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    @Autowired
    DataSource dataSource;

    @Bean(name = "customerItemReaderStep1")
    public FlatFileItemReader<Customer> customerItemReaderStep1() {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("customerItemReaderStep1")
                .resource(inputFileResourceStep1)
                .delimited()
                .names("firstName", "lastName")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {{
                    setTargetType(Customer.class);
                }})
                .build();
    }

    @Bean(name = "customerItemReaderStep3")
    public FlatFileItemReader<Customer> customerItemReaderStep3() {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("customerItemReaderStep3")
                .resource(inputFileResourceStep3)
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

    @Bean
    public CustomerItemDummyProcessor dummyProcessor() {
        return new CustomerItemDummyProcessor();
    }

//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.HSQL)
//                .build();
//    }

    @Bean(name = "JdbcBatchItemWriter")
    public JdbcBatchItemWriter<Customer> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Customer>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO customer (first_name, last_name) VALUES (:firstName, :lastName)")
                .dataSource(dataSource)
                .build();
    }

    public ItemWriter<Customer> customerItemWriter1() {
        return new CustomerItemWriter1(dataSource);
    }

    public ItemWriter<Customer> customerItemWriter2() {
        return new CustomerItemWriter2(dataSource);
    }

    @Bean(name = "CompositeItemWriter")
    public CompositeItemWriter<Customer> itemWriter() {
        CompositeItemWriter<Customer> compositeItemWriter = new CompositeItemWriter<>();
        compositeItemWriter.setDelegates(Arrays.asList(customerItemWriter1(), customerItemWriter2()));
        return compositeItemWriter;
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
//                .start(step1)
//                .next(step2)
//                .next(step3)
                .start(step2)
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Customer> writer) {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(10)
                .reader(customerItemReaderStep1())
                .processor(upperCaseProcessor())
                .writer(writer)
                .build();
    }

    @Bean
    public Step step2(CompositeItemWriter<Customer> compositeItemWriter) {
        return stepBuilderFactory.get("step2")
                .<Customer, Customer>chunk(5)
                .reader(customerItemReaderStep1())
                .processor(lowerCaseProcessor())
                .writer(compositeItemWriter)
                .faultTolerant()
                .skip(IllegalArgumentException.class)
                .skipLimit(3)
                .build();
    }

    @Bean
    public Step step3(KafkaItemWriter<String, Customer> writer) {
        return stepBuilderFactory.get("step3")
                .<Customer, Customer>chunk(5)
                .reader(customerItemReaderStep3())
                .processor(dummyProcessor())
                .writer(writer)
                .listener(new KafkaItemWriteListener())
                .build();
    }

    public static class CustomerItemWriter1 implements ItemWriter<Customer> {

        private JdbcTemplate jdbcTemplate;

        public CustomerItemWriter1(DataSource dataSource) {
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        }

        @Override
        public void write(List<? extends Customer> customers) {
            for (Customer customer : customers) {
                log.info("CustomerItemWriter1: customer = {} ", customer);
                jdbcTemplate.update(String.format("INSERT INTO customer (first_name, last_name) VALUES ('%s', '%s')", customer.getFirstName(), customer.getLastName()));
            }
        }
    }

    public static class CustomerItemWriter2 implements ItemWriter<Customer> {

        private JdbcTemplate jdbcTemplate;

        public CustomerItemWriter2(DataSource dataSource) {
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        }

        @Override
        public void write(List<? extends Customer> customers) {
            for (Customer customer : customers) {
                log.info("CustomerItemWriter2: customer = {} ", customer);
                jdbcTemplate.update(String.format("INSERT INTO customer_bis (first_name, last_name) VALUES ('%s', '%s')", customer.getFirstName(), customer.getLastName()));

//                if (customer.getFirstName().equals("floriaan5")) {
//                    throw new IllegalArgumentException("foutje bedankt");
//                }
                //                if ("foo".equalsIgnoreCase(customer.getLastName())) {
//                    jdbcTemplate.update("UPDATE people SET name = 'foo!!' WHERE id = 1");
//                }
            }
        }
    }
}

