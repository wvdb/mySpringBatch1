package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * The persistent class for the FF_KIND database table.
 * 
 */
@Entity
@Table(name="FF_ORDER_TREATMENT")
public class FfOrderTreatment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TREATMENT")
	private String treatment;

	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="NAME")
	private String name;

	/* Not needed because relation is ASSOCIATION 
	private List<FfOrder> ffOrders;
	*/
	
	/* Not needed because relation is ASSOCIATION 
    private List<PicklistKind> picklistKinds;
    */

	//-------------------------
	//-- constructors        --
	//-------------------------
	public FfOrderTreatment() {
    }

	//-------------------------
	//-- getters and setters --
	//-------------------------
	public TreatmentEnum getTreatment() {
		TreatmentEnum eNum = TreatmentEnum.findByCode(this.treatment);
		if (eNum == null) {
			throw new RuntimeException("No TreatmentEnum for '"+this.treatment+"'");
		}
		return eNum;
	}
	public void setKind(TreatmentEnum treatment) {
		this.treatment = treatment.getCode();
	}

	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	/*
	public List<FfOrder> getFfOrders() {
		return this.ffOrders;
	}
	public void setFfOrders(List<FfOrder> ffOrders) {
		this.ffOrders = ffOrders;
	}
	*/
	
}