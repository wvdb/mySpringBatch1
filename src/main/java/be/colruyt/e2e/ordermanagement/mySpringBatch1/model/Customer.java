package be.colruyt.e2e.ordermanagement.mySpringBatch1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer implements org.apache.kafka.common.serialization.Serializer<Customer> {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int customerId;

    @NotEmpty()
    @Size(min = 4, message = "First-name must be of min 4 characters")
    private String firstName;

    @NotEmpty()
    private String lastName;

    private ZonedDateTime customerCreationDate = ZonedDateTime.now();

    // Items is an association managed with Spring Data Rest (SDR): a repo exists
    @OneToMany(mappedBy = "customer")
    private Set<Item> items;

    // Purchase is an association managed with JPA: NO repo exists
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Set<Purchase> purchases;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(int customerId, String firstName, String lastName, ZonedDateTime customerCreationDate) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerCreationDate = customerCreationDate;
    }

    @Override
    public byte[] serialize(String s, Customer customer) {
        return customer.toString().getBytes();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getCustomerId() == customer.getCustomerId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId());
    }
}
