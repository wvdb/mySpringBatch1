package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the PICKLIST_KIND database table.
 * 
 */
//@NamedQueries ({
//	@NamedQuery(name  = PicklistKind.SEL_ALL_PICKLISTKIND,
//			    query = PicklistKind.QUERY_SEL_ALL_PICKLISTKIND)
//})
//@FetchGroups({
//    @FetchGroup(name      = PicklistKind.FETCHGROUP_PICKLISTKINDNAMES,
//    		    attributes={@FetchAttribute(name="picklistKindNames")}
//               )
//})
@Entity
@Table(name="PICKLIST_KIND")
public class PicklistKind implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FETCHGROUP_PICKLISTKINDNAMES = "PicklistKind.fetchgroup.picklistKindNames";

	public static final String SEL_ALL_PICKLISTKIND = "PicklistKind.query.selAll";
	static final String QUERY_SEL_ALL_PICKLISTKIND  = "SELECT p"
			                                         +" FROM PicklistKind p";

	public PicklistKind(String kind) {
		this.kind = kind;
	}

	@Id
	@Column(name="KIND")
	private String kind;

	@Column(name="CRATE_TYPE")
	private String crateType;

	@Column(name="UI_ALWAYS_ORDER_COMM")
	private String uiAlwaysOrderComm;

	@Column(name="UI_ALWAYS_SELECT_CP")
	private String uiAlwaysSelectCp;

	@Column(name="UI_ASK_NUM_CRATE")
	private String uiAskNumCrate;

	@Column(name="UI_CRATE_IDENTIFICATION")
	private String uiCrateIdentification;

	@Column(name="UI_SHOW_BUTTON_JRM")
	private String uiShowButtonJrm;

	@Column(name="UI_SHOW_PICKLIST_JRM")
	private String uiShowPicklistJrm;

	@Column(name="WEIGHT_DIFF_HIGH")
	private int weightDiffHigh;     /* max % allowed to be picked above ordered weight */

	@Column(name="WEIGHT_DIFF_LOW") 
	private int weightDiffLow;       /* max % allowed to be picked below ordered weight */
	
	@Column(name="UI_ALLOW_ADDITIONS")
	private String uiAllowAdditions;
	
	@Column(name="UI_ALLOW_ADVANCE")
	private String uiAllowAdvance;
	
	@Column(name="UI_DISPLAY_SEQ_NR")
	private int uiDisplaySeqNr;
	
	@Column(name="UI_CONFIRM_CRATE_BC")
	private String uiConfirmCrateBarcode;
	
	@Column(name="GROUP_CODE")
	private int groupCode;
	
	@Column(name="UI_SHOW_SETTINGS_JRM")
	private String uiShowSettingsJrm;
	
	@Column(name="UI_RGB_COLOR")
	private String uiRgbColor;

	@Column(name="DU_SCANNING")
	private String duScanning;
	
	@Column(name="PU_SCANNING")
	private String puScanning;

	/* Not needed because relation is ASSOCIATION 
	private List<FfOrderPart> ffOrderParts;
	*/
	
	//ASSOCIATION relation to parent FfOrderTreatment
    @ManyToOne(optional=true,
               fetch=FetchType.LAZY)
    @JoinColumn(name="TREATMENT", referencedColumnName="TREATMENT")  // column in parent table FF_ORDER_TREATMENT
     private FfOrderTreatment ffOrderTreatment;

    //ASSOCIATION relation to parent PicklistCompositionType 
    @ManyToOne(optional=true,
               fetch   =FetchType.LAZY)
    @JoinColumn(name="COMPOSITION_TYPE", referencedColumnName="COMPOSITION_TYPE")  // column in parent table PICKLIST_COMPOSITION_TYPE
    private PicklistCompositionType compositionType;
    
	//COMPOSITION relation to PicklistKindName
//    @OneToMany(targetEntity = PicklistKindName.class,
//               fetch        = FetchType.LAZY,
//      	       cascade      = CascadeType.ALL,    /* entrie(s) in list can be added/modified (child must be added/modified) */
//               orphanRemoval= true)               /* entrie(s) in list can be removed, in that case also remove child itself (if no new parent was assigned) */
////    @JoinColumn(referencedColumnName="ISO_LANG_CODE")
//	@JoinColumns(
//			{
//					@JoinColumn(referencedColumnName="PICKLIST_KIND"),
//					@JoinColumn(referencedColumnName="ISO_LANG_CODE")
//			}
//	)
//	private Set<PicklistKindName> picklistKindNames;

	
	/* Not needed because relation is ASSOCIATION 
	private List<WrsPicklist> wrsPicklists;
	*/

	//-------------------------
	//-- constructors        --
	//-------------------------
	public PicklistKind() {
    }

	//-------------------------
	//-- getters and setters --
	//-------------------------
	public String getKind() {
		return this.kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getCrateType() {
		return this.crateType;
	}
	public void setCrateType(String crateType) {
		this.crateType = crateType;
	}

	public FfOrderTreatment getFfOrderTreatment() {
        return ffOrderTreatment;
    }
    public void setFfOrderTreatment(FfOrderTreatment ffOrderTreatment) {
        this.ffOrderTreatment = ffOrderTreatment;
    }

    public BooleanEnum getUiAlwaysOrderComm() {
		BooleanEnum eNum = BooleanEnum.findByCode(this.uiAlwaysOrderComm);
		if (eNum == null) {
			throw new RuntimeException("No BooleanEnum for '"+this.uiAlwaysOrderComm+"'");
		}
		return eNum;
	}
	public void setUiAlwaysOrderComm(BooleanEnum uiAlwaysOrderComm) {
		this.uiAlwaysOrderComm = uiAlwaysOrderComm.getCode();
	}

	public BooleanEnum getUiAlwaysSelectCp() {
		BooleanEnum eNum = BooleanEnum.findByCode(this.uiAlwaysSelectCp);
		if (eNum == null) {
			throw new RuntimeException("No BooleanEnum for '"+this.uiAlwaysSelectCp+"'");
		}
		return eNum;
	}
	public void setUiAlwaysSelectCp(BooleanEnum uiAlwaysSelectCp) {
		this.uiAlwaysSelectCp = uiAlwaysSelectCp.getCode();
	}

	public BooleanEnum getUiAskNumCrate() {
		BooleanEnum eNum = BooleanEnum.findByCode(this.uiAskNumCrate);
		if (eNum == null) {
			throw new RuntimeException("No BooleanEnum for '"+this.uiAskNumCrate+"'");
		}
		return eNum;
	}
		
	public void setUiAskNumCrate(BooleanEnum uiAskNumCrate) {
		this.uiAskNumCrate = uiAskNumCrate.getCode();
	}

	public BooleanEnum getUiCrateIdentification() {
		BooleanEnum eNum = BooleanEnum.findByCode(this.uiCrateIdentification);
		if (eNum == null) {
			throw new RuntimeException("No BooleanEnum for '"+this.uiCrateIdentification+"'");
		}
		return eNum;
	}
	public void setUiCrateIdentification(BooleanEnum uiCrateIdentification) {
		this.uiCrateIdentification = uiCrateIdentification.getCode();
	}

	public int getWeightDiffHigh() {
		return weightDiffHigh;
	}
	public void setWeightDiffHigh(int weightDiffHigh) {
		this.weightDiffHigh = weightDiffHigh;
	}

	public int getWeightDiffLow() {
		return weightDiffLow;
	}
	public void setWeightDiffLow(int weightDiffLow) {
		this.weightDiffLow = weightDiffLow;
	}

	public String getShowButtonJrm() {
		return this.uiShowButtonJrm;
	}
	public void setUiShowButtonJrm(String uiShowButtonJrm) {
		this.uiShowButtonJrm = uiShowButtonJrm;
	}

	public String getShowPicklistJrm() {
		return this.uiShowPicklistJrm;
	}
	public void setUiShowPicklistJrm(String uiShowPicklistJrm) {
		this.uiShowPicklistJrm = uiShowPicklistJrm;
	}

	public int getDisplaySeqNr() {
		return uiDisplaySeqNr;
	}
	public void setUiDisplaySeqNumber(int uiDisplaySeqNr) {
		this.uiDisplaySeqNr = uiDisplaySeqNr;
	}
	
	public BooleanEnum getUiAllowAdditions() {
		BooleanEnum eNum = BooleanEnum.findByCode(this.uiAllowAdditions);
		if (eNum == null) {
			throw new RuntimeException("No BooleanEnum for '"+this.uiAllowAdditions+"'");
		}
		return eNum;
	}
	public void setUiAllowAdditions(BooleanEnum uiAllowAdditions) {
		this.uiAllowAdditions = uiAllowAdditions.getCode();
	}
	
	public BooleanEnum getAllowAdvance() {
		BooleanEnum eNum = BooleanEnum.findByCode(this.uiAllowAdvance);
		if (eNum == null) {
			throw new RuntimeException("No BooleanEnum for '"+this.uiAllowAdvance+"'");
		}
		return eNum;
	}
	public void setUiAllowAdvance(BooleanEnum uiAllowAdvance) {
		this.uiAllowAdditions = uiAllowAdvance.getCode();
	}
	
	public String getConfirmCrateBarcode() {
		return uiConfirmCrateBarcode;
	}
	public void setUiConfirmCrateBarcode(String uiConfirmCrateBarcode) {
		this.uiConfirmCrateBarcode = uiConfirmCrateBarcode;
	}

	/*
	public List<FfOrderPart> getFfOrderParts() {
		return this.ffOrderParts;
	}
	public void setFfOrderParts(List<FfOrderPart> ffOrderParts) {
		this.ffOrderParts = ffOrderParts;
	}
	*/

	public PicklistCompositionType getCompositionType() {
        return compositionType;
    }
    public void setCompositionType(PicklistCompositionType compositionType) {
        this.compositionType = compositionType;
    }
	
	public int getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(int groupCode) {
		this.groupCode = groupCode;
	}
	
	public String getUiShowSettingsJrm() {
		return uiShowSettingsJrm;
	}
	public void setUiShowSettingsJrm(String uiShowSettingsJrm) {
		this.uiShowSettingsJrm = uiShowSettingsJrm;
	}
	
	public String getUiRgbColor() {
		return uiRgbColor;
	}
	public void setUiRgbColor(String uiRgbColor) {
		this.uiRgbColor = uiRgbColor;
	}
	
	public BooleanEnum getPuScanning() {
		BooleanEnum eNum = BooleanEnum.findByCode(this.puScanning);
		if (eNum == null) {
			throw new RuntimeException("No BooleanEnum for '"+this.puScanning+"'");
		}
		return eNum;
	}
	public void setPuScanning(BooleanEnum puScanning) {
		this.puScanning = puScanning.getCode();
	}
	
	public DeliveryUnitScanningEnum getDuScanning() {
		DeliveryUnitScanningEnum eNum = DeliveryUnitScanningEnum.findByCode(this.duScanning);
		if (eNum == null) {
			throw new RuntimeException("No DeliveryUnitScanningEnum for '"+this.duScanning+"'");
		}
		return eNum;
	}
	public void setDuScanning(DeliveryUnitScanningEnum duScanning) {
		this.duScanning = duScanning.getCode();
	}
	
	/*
	public List<WrsPicklist> getWrsPicklists() {
		return this.wrsPicklists;
	}
	public void setWrsPicklists(List<WrsPicklist> wrsPicklists) {
		this.wrsPicklists = wrsPicklists;
	}
	*/
	
	//------------------------------------
	//-- ADDITIONAL getters and setters --
	//------------------------------------

//	/* For COMPOSITION to PicklistKindLang  (add = via this setter;  remove via remove on List; modify via setters for child in list) */
//	public void addPicklistKindLang(PicklistKindName inPicklistKindLang) {
// 		/* check if parentkey already known (might be missing in case of sequence,....) */
// 		if (   this.getKind()  == null) {
// 			throw new RuntimeException("Parent key 'kind' has no value yet! Create parent in DB before adding childs!");
// 		}
//
//
//		/* Set parent to current parent */
//		inPicklistKindLang.setPicklistKind(this);
//		inPicklistKindLang.getId().setPicklistKind(this.getKind());
//
//		/* add WrsPicklistLine in the list */
//		if (this.picklistKindNames == null) {
//			this.picklistKindNames = new TreeMap<String, PicklistKindName>();
//		}
//
//
//		this.picklistKindNames.put(inPicklistKindLang.getIsoLanguage().getIsoLangCode(), inPicklistKindLang);
//	}

//	// For easy retrieval of 'description' in a given language
//	public String getDescriptionForIsoLangCode(String inIsoLangCode) {
//		PicklistKindName currentPicklistKindName = this.picklistKindNames.get(inIsoLangCode);
//
//		if (currentPicklistKindName != null) {
//			return currentPicklistKindName.getName();
//
//		} else {
//			return null;
//		}
//	}

//	// For easy retrieval of 'name' in a given language
//	public String getNameForIsoLangCode(String inIsoLangCode) {
//		PicklistKindName currentPicklistKindLang = this.picklistKindNames.get(inIsoLangCode);
//
//		if (currentPicklistKindLang != null) {
//			return currentPicklistKindLang.getName();
//
//		} else {
//			return null;
//		}
//
//	}
	
}