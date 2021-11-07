//package be.colruyt.e2e.ordermanagement.mySpringBatch1;
//
//import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Customer;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.batch.core.ExitStatus;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.test.JobLauncherTestUtils;
//import org.springframework.batch.test.context.SpringBatchTest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.Resource;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBatchTest
//@EnableAutoConfiguration
//@ContextConfiguration(classes = {MySpringBatch2ApplicationTest.JobConfiguration.class})
//@TestPropertySource(value = {"classpath:application.properties"})
//@Slf4j
//public class MySpringBatch2ApplicationTest {
//
//	@Autowired
//	private JobLauncherTestUtils jobLauncherTestUtils;
////
////	@Autowired
////	private JdbcTemplate jdbcTemplate;
//
////	@Autowired
////	DataSource dataSource;
//
//	@Test
//	public void myTest1() throws Exception {
//		// when
//	    JobExecution jobExecution = jobLauncherTestUtils.launchJob();
//
//		// then
//		Assert.assertEquals(ExitStatus.COMPLETED.getExitCode(), jobExecution.getExitStatus().getExitCode());
//	}
//
//	@TestConfiguration
//	@EnableBatchProcessing
//	static class JobConfiguration {
//		@Value("${app.springBatchInputFileNameStep1}")
//		private Resource inputFileResourceStep1;
//
//		@Autowired
//		public JobBuilderFactory jobBuilderFactory;
//
//		@Autowired
//		public StepBuilderFactory stepBuilderFactory;
//
//		public ItemWriter<Customer> customerItemWriter1() {
//			return new CustomerItemWriter1();
//		}
//
//		@Bean(name = "customerItemReaderStep1")
//		public FlatFileItemReader<Customer> customerItemReaderStep1() {
//			return new FlatFileItemReaderBuilder<Customer>()
//					.name("customerItemReaderStep1")
//					.resource(inputFileResourceStep1)
//					.delimited()
//					.names("firstName", "lastName")
//					.fieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {{
//						setTargetType(Customer.class);
//					}})
//					.build();
//		}
//
//		@Bean
//		public Step step() {
//			return stepBuilderFactory.get("step1")
//					.<Customer, Customer>chunk(10)
//					.reader(customerItemReaderStep1())
//					.writer(customerItemWriter1())
//					.build();
//		}
//
//		@Bean
//		public Job job(Step step) {
//			return jobBuilderFactory.get("job")
//					.start(step)
//					.build();
//		}
//
//		@Bean
//		public JobLauncherTestUtils jobLauncherTestUtils() {
//			return new JobLauncherTestUtils();
//		}
//
//		public static class CustomerItemWriter1 implements ItemWriter<Customer> {
////			private JdbcTemplate jdbcTemplate;
////
////			public CustomerItemWriter1(DataSource dataSource) {
////				this.jdbcTemplate = new JdbcTemplate(dataSource);
////			}
//
//			@Override
//			public void write(List<? extends Customer> customers) {
//				for (Customer customer : customers) {
//					log.info("CustomerItemWriter1: customer = {} ", customer);
//				}
//			}
//		}
//
//	}
//
//}
