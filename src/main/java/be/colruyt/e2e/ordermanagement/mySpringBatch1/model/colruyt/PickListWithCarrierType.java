package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PickListWithCarrierType extends Picklist{
    private String carrierType;
    private Integer minSlotsAdvance;

    public PickListWithCarrierType(String carrierType, Integer minSlotsAdvance, long picklistId, Integer ffLocation, String picklistKind) {
        this.carrierType = carrierType;
        this.minSlotsAdvance = minSlotsAdvance;
        this.picklistId = picklistId;
        this.ffLocation = ffLocation;
        this.picklistKind = picklistKind;
    }
}
