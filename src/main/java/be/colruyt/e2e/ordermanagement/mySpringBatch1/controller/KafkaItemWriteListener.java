package be.colruyt.e2e.ordermanagement.mySpringBatch1.controller;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

@Slf4j
public class KafkaItemWriteListener implements ItemWriteListener<Customer> {
    @Override
    public void beforeWrite(@NotNull List<? extends Customer> customers) {
        log.info("KafkaItemWriteListener - beforeWrite " + customers);
    }

    @Override
    public void afterWrite(@NotNull List<? extends Customer> customers) {
        log.info("KafkaItemWriteListener - afterWrite " + customers);
    }

    @Override
    public void onWriteError(@NotNull Exception exception, @NotNull List<? extends Customer> customers) {
        log.error("KafkaItemWriteListener - onWriteError " + customers, exception);
    }
}
