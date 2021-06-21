package be.colruyt.e2e.ordermanagement.mySpringBatch1.controller;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

//            jdbcTemplate.query("SELECT first_name, last_name FROM customer",
//                    (rs, row) -> new Customer(
//                            rs.getString(1),
//                            rs.getString(2))
//            ).forEach(customer -> log.info("Found <" + customer + "> in the database."));

            int cntr = jdbcTemplate.query("SELECT first_name, last_name FROM customer",
                    (rs, row) -> new Customer(
                            rs.getString(1),
                            rs.getString(2))
            ).size();

            log.info("!!! JOB FINISHED! " + cntr + " entries exist.");
        }
    }
}