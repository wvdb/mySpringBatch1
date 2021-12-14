package be.colruyt.e2e.ordermanagement.mySpringBatch1.controller;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.wim.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Slf4j
public class CustomerItemProcessor {

    @Autowired
    private Validator validator;

    protected boolean isCustomerValid(Customer customer) {
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        boolean isValid;

        if (violations.isEmpty()) {
           isValid = !customer.getLastName().toLowerCase().startsWith("trump");
            if (isValid)  {
                log.info("Customer {} {} is valid.", customer.getFirstName(), customer.getLastName());
            }
            else {
                log.error("Customer {} {} is not valid.", customer.getFirstName(), customer.getLastName());
            }
        }
        else {
            log.error("Validation Customer {} failed with violations {}.", customer, violations);
            isValid = false;
        }

        return isValid;
    }

}
