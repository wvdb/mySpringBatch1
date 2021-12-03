package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the WRS_COLLECTPOINT_NAME database table.
 * 
 */
@Embeddable
@Table(name="WRS_COLLECTPOINT_NAME")
public class WrsCollectpointName implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="COLLECTPOINT_NAME")
//	@ReadOnly(UpdateAction.RESTRICT)
	private String name;

	//ASSOCIATION relation to parent IsoLanguage
    @ManyToOne(optional=false,
    		   fetch   =FetchType.LAZY)
    @JoinColumn(referencedColumnName="ISO_LANG_CODE")  // column in parent table REF_ISO_LANGUAGE
	private IsoLanguage isoLanguage;
  
	/* Not needed because relation is COMPOSITION  
	private WrsCollectpoint xCollectpoint;
	*/


	//-------------------------
	//-- constructors        --
	//-------------------------
	public WrsCollectpointName() {
    }

	//-------------------------
	//-- getters and setters --
	//-------------------------
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public IsoLanguage getIsoLanguage() {
		return isoLanguage;
	}
	public void setIsoLanguage(IsoLanguage isoLanguage) {
		this.isoLanguage = isoLanguage;
	}

}