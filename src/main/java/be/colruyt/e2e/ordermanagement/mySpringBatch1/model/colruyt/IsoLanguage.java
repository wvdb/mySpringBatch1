package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * The persistent class for the REF_ISO_LANGUAGE database table.
 * 
 */
@Entity
@Table(name="REF_ISO_LANGUAGE")
public class IsoLanguage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ISO_LANG_CODE")
	private String isoLangCode;

	@Column(name="ISO_LANG_DESC")
	private String isoLangDesc;

	@Column(name="ISO_LANG_NAME")
    private String isoLangName;

	/* Not needed because relation is ASSOCIATION 
	private List<PickingResultLang> pickingResultLangs;
	*/

	/* Not needed because relation is ASSOCIATION 
	private List<PickingStatusLang> pickingStatusLangs;
	*/

	/* Not needed because relation is ASSOCIATION 
	private List<PicklistKindLang> picklistKindLangs;
	*/
	
	/* Not needed because relation is ASSOCIATION 
	private List<XArticleLang> xArticleLang;
	*/
	
	/* Not needed because relation is ASSOCIATION 
	private List<XCollectpointLang> xCollectpointLang;
	*/

	//-------------------------
	//-- constructors        --
	//-------------------------
	public IsoLanguage() {
    }

	//-------------------------
	//-- getters and setters --
	//-------------------------
	public String getIsoLangCode() {
		return this.isoLangCode;
	}
	public void setIsoLangCode(String isoLangCode) {
		this.isoLangCode = isoLangCode;
	}

	public String getIsoLangDesc() {
		return this.isoLangDesc;
	}
	public void setIsoLangDesc(String isoLangDesc) {
		this.isoLangDesc = isoLangDesc;
	}

	public String getIsoLangName() {
		return isoLangName;
	}
	public void setIsoLangName(String isoLangName) {
		this.isoLangName = isoLangName;
	}
	
	/* Not needed because relation is ASSOCIATION 
	public List<PickingResultLang> getPickingResultLangs() {
		return this.pickingResultLangs;
	}
	public void setPickingResultLangs(List<PickingResultLang> pickingResultLangs) {
		this.pickingResultLangs = pickingResultLangs;
	}
	*/
	
	/* Not needed because relation is ASSOCIATION 
	public List<PickingStatusLang> getPickingStatusLangs() {
		return this.pickingStatusLangs;
	}
	public void setPickingStatusLangs(List<PickingStatusLang> pickingStatusLangs) {
		this.pickingStatusLangs = pickingStatusLangs;
	}
	*/
	
	/* Not needed because relation is ASSOCIATION 
	public List<PicklistKindLang> getPicklistKindLangs() {
		return this.picklistKindLangs;
	}
	public void setPicklistKindLangs(List<PicklistKindLang> picklistKindLangs) {
		this.picklistKindLangs = picklistKindLangs;
	}
	*/
	
}