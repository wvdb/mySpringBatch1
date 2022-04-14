package be.colruyt.e2e.ordermanagement.mySpringBatch1;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.kafka.core.KafkaTemplate;

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

    @Value("${app.springBatchInputFileNameStep1}")
    private Resource inputFileResourceStep2;

    @Value("${app.springBatchInputFileNameStep3}")
    private Resource inputFileResourceStep3;

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

//    @Bean(name = "customerItemReaderStep1")
//    public FlatFileItemReader<Customer> customerItemReaderStep1() {
//        return new FlatFileItemReaderBuilder<Customer>()
//                .name("customerItemReaderStep1")
//                .resource(inputFileResourceStep1)
//                .delimited()
//                .names("firstName", "lastName")
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {{
//                    setTargetType(Customer.class);
//                }})
//                .build();
//    }

//    @Bean(name = "customerItemReaderStep2")
//    public FlatFileItemReader<Customer> customerItemReaderStep2() {
//        return new FlatFileItemReaderBuilder<Customer>()
//                .name("customerItemReaderStep2")
//                .resource(inputFileResourceStep2)
//                .delimited()
//                .names("firstName", "lastName")
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {{
//                    setTargetType(Customer.class);
//                }})
//                .build();
//    }
//
//    @Bean(name = "customerItemReaderStep3")
//    public FlatFileItemReader<Customer> customerItemReaderStep3() {
//        return new FlatFileItemReaderBuilder<Customer>()
//                .name("customerItemReaderStep3")
//                .resource(inputFileResourceStep3)
//                .delimited()
//                .names("firstName", "lastName")
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {{
//                    setTargetType(Customer.class);
//                }})
//                .build();
//    }
//
//    @Bean
//    public CustomerItemUpperCaseProcessor upperCaseProcessor() {
//        return new CustomerItemUpperCaseProcessor();
//    }
//
//    @Bean
//    public CustomerItemLowerCaseProcessor lowerCaseProcessor() {
//        return new CustomerItemLowerCaseProcessor();
//    }
//
//    @Bean
//    public CustomerItemDummyProcessor dummyProcessor() {
//        return new CustomerItemDummyProcessor();
//    }
//
//    @Bean(name = "writer1")
//    public JdbcBatchItemWriter<Customer> writer1(@Qualifier("ORACLE_DataSource") DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<Customer>()
//                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//                .sql("INSERT INTO customer (first_name, last_name) VALUES (:firstName, :lastName)")
//                .dataSource(dataSource)
//                .build();
//    }
//
//    @Bean(name = "writer2")
//    public JdbcBatchItemWriter<Customer> writer2(@Qualifier("MySQL_DataSource") DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<Customer>()
//                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//                .sql("INSERT INTO customer_bis (first_name, last_name) VALUES (:firstName, :lastName)")
//                .dataSource(dataSource)
//                .build();
//    }
//
//    @Bean(name = "CompositeItemWriter")
//    public CompositeItemWriter<Customer> txnWriter() {
//        CompositeItemWriter<Customer> compositeItemWriter = new CompositeItemWriter<>();
//        compositeItemWriter.setDelegates(Arrays.asList(writer1(null), writer2(null)));
//        return compositeItemWriter;
//    }
//
//    @Bean(name = "KafkaItemWriter")
//    public KafkaItemWriter<String, Customer> writer(KafkaTemplate<String, Customer> kafkaTemplate) {
//        return new KafkaItemWriterBuilder<String, Customer>()
//                .kafkaTemplate(kafkaTemplate)
//                .itemKeyMapper(customer -> Integer.toString(customer.getCustomerId()))
//                .build();
//    }
//
//    @Bean
//    public Job importCustomerJob(JobCompletionNotificationListener listener, Step step1, Step step2, Step step3) {
//        return jobBuilderFactory.get("importCustomerJob")
//                .incrementer(new RunIdIncrementer())
//                .validator(parametersValidator())
//                .listener(listener)
////                .start(step1)
////                .next(step2)
////                .next(step3)
//                .start(step2)
//                .build();
//    }
//
//    private JobParametersValidator parametersValidator() {
//        return new JobParametersValidator() {
//            @Override
//            public void validate(JobParameters parameters) throws JobParametersInvalidException {
//                try {
//                    if (inputFileResourceStep2.contentLength() == 0) {
//                        throw new JobParametersInvalidException("The file is empty.");
//                    }
//                } catch (IOException e) {
//                    throw new JobParametersInvalidException("The file might be empty.");
//                }
//            }
//        };
//    }
//
////    @Bean
////    public Step step1(JdbcBatchItemWriter<Customer> writer1) {
////        return stepBuilderFactory.get("step1")
////                .<Customer, Customer>chunk(10)
////                .reader(customerItemReaderStep1())
////                .processor(upperCaseProcessor())
////                .writer(writer1)
////                .build();
////    }
//
//    @Bean
//    public Step step2(@Qualifier("chainTxManager") PlatformTransactionManager transactionManager) {
//        DefaultTransactionAttribute txAttribute = new DefaultTransactionAttribute();
//        txAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
//        txAttribute.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
//        txAttribute.setTimeout(600);
//
//        return stepBuilderFactory.get("step2")
//                .transactionManager(transactionManager)
//                .<Customer, Customer>chunk(5)
//                .faultTolerant()
//                .reader(customerItemReaderStep2())
//                .processor(lowerCaseProcessor())
//                .writer(txnWriter())
//                .transactionAttribute(txAttribute)
//                .build();
//    }

//    @Bean
//    public Step step3(KafkaItemWriter<String, Customer> writer) {
//        return stepBuilderFactory.get("step3")
//                .<Customer, Customer>chunk(5)
//                .reader(customerItemReaderStep3())
//                .processor(dummyProcessor())
//                .writer(writer)
//                .listener(new KafkaItemWriteListener())
//                .build();
//    }

}

