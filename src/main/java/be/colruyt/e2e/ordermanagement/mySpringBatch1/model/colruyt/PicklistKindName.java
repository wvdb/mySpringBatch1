package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the PICKLIST_KIND_LANG database table.
 * 
 */
@Entity
@Table(name="PICKLIST_KIND_NAME")
public class PicklistKindName implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PicklistKindNamePK id;

	@Column(name="NAME")
	private String name;

	//COMPOSITION relation to parent PicklistKind
	@ManyToOne(optional=false,
			   fetch   =FetchType.LAZY)
	@JoinColumn(referencedColumnName="KIND")           // column in parent table PICKLIST_KIND
	private PicklistKind picklistKind;

    //ASSOCIATION relation to parent IsoLanguage
    @ManyToOne(optional=false,
    		   fetch   =FetchType.LAZY)
    @JoinColumn(referencedColumnName="ISO_LANG_CODE")  // column in parent table REF_ISO_LANGUAGE
	private IsoLanguage isoLanguage;
  
    //-------------------------
    //-- constructors        --
    //-------------------------
    public PicklistKindName() {
    }

    //-------------------------
    //-- getters and setters --
    //-------------------------
    public PicklistKindNamePK getId() {
		return this.id;
	}
	public void setId(PicklistKindNamePK id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	/* COMPOSITION so relation managed by parent: hence 'package protected' (no 'private/public/protected') */
	PicklistKind getPicklistKind() {
		return this.picklistKind;
	}
	void setPicklistKind(PicklistKind picklistKind) {
		this.picklistKind = picklistKind;
	}
	
	public IsoLanguage getIsoLanguage() {
		return this.isoLanguage;
	}
	public void setIsoLanguage(IsoLanguage isoLanguage) {
		this.isoLanguage = isoLanguage;
	}
	
}