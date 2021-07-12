package be.colruyt.e2e.ordermanagement.mySpringBatch1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PurchaseItemComplex implements Serializable {
//    @EmbeddedId
//    private PurchaseItemComplexId purchaseItemComplexId;

//    @ManyToOne
//    @MapsId("customerId")
//    private Customer customer;
//
//    @ManyToOne
//    @MapsId("itemId")
//    private ItemBis itemBis;

    private int itemId;
    private ZonedDateTime purchaseDate;
    private Float purchaseAmount;

//    @Embeddable
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class PurchaseItemComplexId implements Serializable {
//        private int customerId;
//        private int itemId;
//    }
}
