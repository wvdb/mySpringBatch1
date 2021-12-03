package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the PICKLIST_LINE database table.
 * 
 */
@Embeddable
public class PicklistLinePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PICKLIST_ID")
	private long picklistId;

	@Column(name="PICKLIST_LINE_NR")
	private long picklistLineNr;

	
	//-------------------------
	//-- constructors        --
	//-------------------------
	public PicklistLinePK() {
    }
	
	
	//-------------------------
	//-- getters and setters --
	//-------------------------
	/* COMPOSITION so relation managed by parent: hence 'package protected'
	   (no 'private/public/protected') */
	
	long getPicklistId() {
		return this.picklistId;
	}
	void setPicklistId(long picklistId) {
		this.picklistId = picklistId;
	}

	
	public long getPicklistLineNr() {
		return this.picklistLineNr;
	}
	public void setPicklistLineNr(long picklistLineNr) {
		this.picklistLineNr = picklistLineNr;
	}

	//-------------------------
	//-- constructors        --
	//-------------------------
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PicklistLinePK)) {
			return false;
		}
		PicklistLinePK castOther = (PicklistLinePK)other;
		return 
			(this.picklistId == castOther.picklistId)
			&& (this.picklistLineNr == castOther.picklistLineNr);

    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.picklistId ^ (this.picklistId >>> 32)));
		hash = hash * prime + ((int) (this.picklistLineNr ^ (this.picklistLineNr >>> 32)));
		
		return hash;
    }
}