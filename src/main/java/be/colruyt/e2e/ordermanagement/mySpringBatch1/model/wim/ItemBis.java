package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.wim;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemBis implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int itemId;

    @NotEmpty()
    private String name;

    @ManyToMany(mappedBy = "itemsBis")
    private Set<Customer> customers;

    public ItemBis(String name) {
        this.name = name;
    }
}
