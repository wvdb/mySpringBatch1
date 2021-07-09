package be.colruyt.e2e.ordermanagement.mySpringBatch1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int itemId;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @NotEmpty()
    private String name;

    public Item(String name) {
        this.name = name;
    }
}
