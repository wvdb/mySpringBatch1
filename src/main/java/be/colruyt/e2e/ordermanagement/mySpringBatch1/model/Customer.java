package be.colruyt.e2e.ordermanagement.mySpringBatch1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements org.apache.kafka.common.serialization.Serializer<Customer> {
    private int customerId;

    @NotEmpty()
    @Size(min = 4, message = "First-name must be of min 4 characters")
    private String firstName;

    @NotEmpty()
    private String lastName;

    private ZonedDateTime customerCreationDate = ZonedDateTime.now();

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public byte[] serialize(String s, Customer customer) {
        return customer.toString().getBytes();
    }
}
