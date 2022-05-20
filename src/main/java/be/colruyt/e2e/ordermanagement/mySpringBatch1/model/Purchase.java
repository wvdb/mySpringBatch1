package be.colruyt.e2e.ordermanagement.mySpringBatch1.model;

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
public class Purchase implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int purchaseId;

    @NotEmpty()
    private String name;

    private Float cost;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "purchase_id", nullable = false)
    private Set<PurchaseStatus> purchaseStatuses;
}