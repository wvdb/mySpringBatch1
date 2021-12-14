package be.colruyt.e2e.ordermanagement.mySpringBatch1.controller;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.wim.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

@Slf4j
public class KafkaItemWriteListener implements ItemWriteListener<Customer> {
    @Override
    public void beforeWrite(List<? extends Customer> customers) {
        log.info("KafkaItemWriteListener - beforeWrite " + customers);
    }

    @Override
    public void afterWrite(List<? extends Customer> customers) {
        log.info("KafkaItemWriteListener - afterWrite " + customers);
    }

    @Override
    public void onWriteError(Exception exception, List<? extends Customer> customers) {
        log.error("KafkaItemWriteListener - onWriteError " + customers, exception);
    }
}
