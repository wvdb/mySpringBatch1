package be.colruyt.e2e.ordermanagement.mySpringBatch1.controller;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Customer;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MyController {

    @Autowired
    private Customer customer;

    @GetMapping(value="/dummy", produces = {MediaType.APPLICATION_JSON_VALUE})
    @RateLimiter(name = "getMessageRateLimit", fallbackMethod = "getMessageFallBack")
    public ResponseEntity dummy() {
        log.info("Created customer = " + customer);
        return ResponseEntity.ok(customer);
    }

    public ResponseEntity<String> getMessageFallBack(RequestNotPermitted exception) {
        log.info("Rate limit has applied, So no further calls are getting accepted");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests : No further request will be accepted. Please try after sometime");
    }

}
