package be.colruyt.e2e.ordermanagement.mySpringBatch1.controller;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Customer;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.CustomerRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MyController {

    @Autowired
    private Customer customer;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(value="/dummy",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> dummy() {
        log.info("Created customer = " + customer);
        return ResponseEntity.ok(customer);
    }

    @GetMapping(value="/customer/{customerId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity customer1(@PathVariable(value = "customerId", required = true) Integer customerId) {
        if (customerRepository.findById(customerId).isPresent()) {
            return ResponseEntity.ok(customerRepository.findById(customerId).get());
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity customer2(@RequestBody DummyCustomerId dummyCustomerId) {
        if (customerRepository.findById(dummyCustomerId.getCustomerId()).isPresent()) {
            return ResponseEntity.ok(customerRepository.findById(dummyCustomerId.getCustomerId()).get());
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Data
    private static class DummyCustomerId {
        private Integer customerId;
    }
}
