package be.colruyt.e2e.ordermanagement.mySpringBatch1.controller;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MyController {

    @Autowired
    private Customer customer;

    @GetMapping(value="/dummy",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> dummy() {
        log.info("Created customer = " + customer);
        return ResponseEntity.ok(customer);
    }

}
