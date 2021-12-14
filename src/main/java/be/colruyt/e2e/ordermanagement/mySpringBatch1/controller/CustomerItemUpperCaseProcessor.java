package be.colruyt.e2e.ordermanagement.mySpringBatch1.controller;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.wim.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class CustomerItemUpperCaseProcessor extends  CustomerItemProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(final Customer inputCustomer) {
        Customer outputCustomer = new Customer(inputCustomer.getCustomerId(), inputCustomer.getFirstName().toUpperCase(), inputCustomer.getLastName().toUpperCase(), inputCustomer.getCustomerCreationDate());

        if (isCustomerValid(outputCustomer)) {
            log.info("Converted (" + inputCustomer + ") into (" + outputCustomer + ")");
        }
        else {
            outputCustomer = null;
        }

        return outputCustomer;
    }

}