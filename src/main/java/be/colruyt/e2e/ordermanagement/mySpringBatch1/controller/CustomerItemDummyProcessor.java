package be.colruyt.e2e.ordermanagement.mySpringBatch1.controller;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.wim.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class CustomerItemDummyProcessor extends CustomerItemProcessor implements ItemProcessor<Customer, Customer>  {

    @Override
    public Customer process(final Customer inputCustomer) {
        return new Customer(inputCustomer.getCustomerId(), inputCustomer.getFirstName() + " dummy", inputCustomer.getLastName().toLowerCase(), inputCustomer.getCustomerCreationDate());
    }

}