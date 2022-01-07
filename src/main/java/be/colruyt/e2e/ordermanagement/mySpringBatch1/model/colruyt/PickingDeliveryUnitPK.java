package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Embeddable
@IdClass(PickingDeliveryUnit.class)
public class PickingDeliveryUnitPK implements Serializable {
    @Id
    private Long ffOrderId;

    @Id
    private Long picklistId;
}
