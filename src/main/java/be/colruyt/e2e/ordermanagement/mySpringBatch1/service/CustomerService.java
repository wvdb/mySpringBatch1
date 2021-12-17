package be.colruyt.e2e.ordermanagement.mySpringBatch1.service;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.poc.Dummy1;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.wim.Customer;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.wim.CustomerRepository;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.repo.poc.Dummy1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Dummy1Repository dummy1Repository;

//    @Transactional(timeout = 5, timeoutString = "Failure because transaction toke too long.")
    @Transactional(timeout = 5)
    public void createCustomer(String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName);
        Dummy1 dummy1 = new Dummy1(lastName);

        customerRepository.save(customer);
        dummy1Repository.save(dummy1);
    }
}
