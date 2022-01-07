package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

//@Entity
//@Table(name="PICKING_DELIVERY_UNIT")
@Embeddable
@Data
public class PickingDeliveryUnit implements Serializable {
//    @EmbeddedId
//    private PickingDeliveryUnitPK pickingDeliveryUnitPK;

    @Column(name="FF_ORDER_ID")
    private Long ffOrderId;

    @Column(name="DEL_UNIT_SERIAL")
    private String delUnitSerial;

    @Column(name="DEL_UNIT_BARCODE")
    private String delUnitBarcode;

    @Column(name="TS_INSERT")
    private Timestamp tsInsert;

    @Column(name      ="TS_LAST_UPD")
    private Timestamp tsLastUpd;
}
