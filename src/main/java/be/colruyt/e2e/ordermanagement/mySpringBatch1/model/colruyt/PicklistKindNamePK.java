package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the PICKLIST_KIND_NAME database table.
 * 
 */
@Embeddable
public class PicklistKindNamePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PICKLIST_KIND")
	private String picklistKind;

	@Column(name="ISO_LANG_CODE")
	private String isoLangCode;

	
	//-------------------------
	//-- constructors        --
	//-------------------------
	public PicklistKindNamePK() {
    }
	
	
	//-------------------------
	//-- getters and setters --
	//-------------------------
	/* COMPOSITION so relation managed by parent: hence 'package protected'
	   (no 'private/public/protected') */
	
	String getPicklistKind() {
		return this.picklistKind;
	}
	void setPicklistKind(String picklistKind) {
		this.picklistKind = picklistKind;
	}

	
	public String getIsoLangCode() {
		return this.isoLangCode;
	}
	public void setIsoLangCode(String isoLangCode) {
		this.isoLangCode = isoLangCode;
	}


	//-------------------------
	//-- overrides           --
	//-------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((isoLangCode == null) ? 0 : isoLangCode.hashCode());
		result = prime * result + ((picklistKind == null) ? 0 : picklistKind.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PicklistKindNamePK other = (PicklistKindNamePK) obj;
		if (isoLangCode == null) {
			if (other.isoLangCode != null)
				return false;
		} else if (!isoLangCode.equals(other.isoLangCode))
			return false;
		if (picklistKind == null) {
			if (other.picklistKind != null)
				return false;
		} else if (!picklistKind.equals(other.picklistKind))
			return false;
		return true;
	}


}