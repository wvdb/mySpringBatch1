package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * The persistent class for the CARRIER_TYPE database table.
 * 
 */
@Entity
@Table(name="CARRIER_TYPE")
public class CarrierType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="CARRIER_TYPE")
    private String carrierType;
    
    @Column(name="MIN_SLOTS_ADVANCE")
	private Integer minSlotsAdvance;
    
    //ASSOCIATION relation to childs CarrierTypeSlot
    
    //COMPOSITION relation to CarrierTypeName
    @OneToMany(targetEntity = CarrierTypeName.class,
    		   fetch        = FetchType.LAZY,
    		   cascade      = CascadeType.ALL,    /* entrie(s) in list can be added/modified (child must be added/modified) */
               orphanRemoval= true)               /* entrie(s) in list can be removed, in that case also remove child itself (if no new parent was assigned) */ 
    /* No join columns : mappedBy tells us to find join in child */
    @JoinColumn(name="ISO_LANG_CODE")
	private Set<CarrierTypeName> carrierTypeNames;
    
    //-------------------------
    //-- constructors        --
    //-------------------------
    public CarrierType() {
    }


    //-------------------------
    //-- getters and setters --
    //-------------------------
    public String getCarrierType() {
        return carrierType;
    }
    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
    }
    
    public Integer getMinSlotsAdvance() {
		return minSlotsAdvance;
	}
    public void setMinSlotsAdvance(Integer minSlotsAdvance) {
		this.minSlotsAdvance = minSlotsAdvance;
	}

	public Set<CarrierTypeName> getCarrierTypeNames() {
		return carrierTypeNames;
	}

	public void setCarrierTypeNames(Set<CarrierTypeName> carrierTypeNames) {
		this.carrierTypeNames = carrierTypeNames;
	}

}
