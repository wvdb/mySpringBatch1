package be.colruyt.e2e.ordermanagement.mySpringBatch1.job;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class JobConfiguration {


    private static final String TRANSPORT_PLANNING_DOCUMENT_GENERATE_JOB = "TransportPlanningDocGenerateJob";

    private static final String TRANSPORT_PLANNING_DOCUMENT_GENERATE_STEP = "TransportPlanningDocGenerateStep";

//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Value("${app.springBatchInputFileNameStep1}")
//    private Resource inputFileResourceStep1;
//
//    @Autowired
//    private StepBuilderFactory steps;

//    @Bean
//    public Job clientDocumentGenerateJob(@Qualifier("step1") Step step1) {
//        return jobBuilderFactory.get( TRANSPORT_PLANNING_DOCUMENT_GENERATE_JOB )
//                .flow( step1 )
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step step1(FlatFileItemReader<Customer> reader,
//                      CustomerWriter customerWriter) {
//        return steps.get("step1").<Customer, Customer> chunk(10)
//                .reader( reader )
//                .writer( customerWriter )
//                .build();
//    }

//    @Bean(name = "writer1")
//    public JdbcBatchItemWriter<Customer> writer1(@Qualifier("ORACLE_DataSource") DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<Customer>()
//                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//                .sql("INSERT INTO customer (first_name, last_name) VALUES (:firstName, :lastName)")
//                .dataSource(dataSource)
//                .build();
//    }

//    @Bean(name = "customerItemReaderStep1")
//    public FlatFileItemReader<Customer> customerItemReader() {
//        return new FlatFileItemReaderBuilder<Customer>()
//                .name("customerItemReader")
//                .resource(inputFileResourceStep1)
//                .delimited()
//                .names("customerId", "firstName", "lastName")
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {{
//                    setTargetType(Customer.class);
//                }})
//                .build();
//    }
//
//
//    @Bean
//    public ExecutionContext executionContext() {
//        return new ExecutionContext();
//    }
//
//    @Bean
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager(); // return the implementation you want
//    }

}
