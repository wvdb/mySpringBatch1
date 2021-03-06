package be.colruyt.e2e.ordermanagement.mySpringBatch1.controller;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Slf4j
public class CustomerItemProcessor {

    @Autowired
    private Validator validator;

    protected Customer isCustomerValid(Customer customer) {
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        if (violations.isEmpty()) {
            customer = customer.getLastName().toLowerCase().startsWith("trump") ? null : customer;
        }
        else {
            log.error("Validation Customer {} failed with violations {}.", customer, violations);
            customer = null;
        }

        return customer;
    }

}
