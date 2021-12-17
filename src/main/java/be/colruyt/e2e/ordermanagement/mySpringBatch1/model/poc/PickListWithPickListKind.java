package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.poc;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt.Picklist;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class PickListWithPickListKind extends Picklist {
    private String pickListKind;
    private String crateType;

    public PickListWithPickListKind(String pickListKind, String crateType, long picklistId, Integer ffLocation) {
        this.pickListKind = pickListKind;
        this.crateType = crateType;
        this.picklistId = picklistId;
        this.ffLocation = ffLocation;
    }
}
