package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the WRS_PICKLIST_LINE database table.
 * 
 */
@Entity
@Table(name="PICKLIST_LINE")
public class PicklistLine implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String SEL_ALL_PICKLISTLINE = "PicklistLine.query.selAll";
	static final String QUERY_SEL_ALL_PICKLISTLINE = "SELECT p"
			                                        +" FROM PicklistLine pl"
			                                        +" WHERE p.picklistId = :inPicklistId";



	@EmbeddedId
	private PicklistLinePK id;

	@Column(name="SELLING_PARTNER_ID")
	private String sellingPartnerId;

	@Column(name="COMMENT_TEXT")
	private String commentText;

	@Column(name="SHELF_NR")
	private Integer shelfNr;

	@Column(name="TEMP_SKIPPED")
	private String tempSkipped;

	/* not needed as auto filled by db
	@Column(name="TS_INSERT")
	private Timestamp tsInsert;
	*/

	/* not needed as auto filled by db
	@Column(name="TS_LAST_UPD")
	private Timestamp tsLastUpd;
	*/

	/* Not needed because relation is ASSOCIATION  
	private List<WrsPickingTr> wrsPickingTrs;
	*/

	//ASSOCIATION relation to parent FfOrderPartLine
//	@ManyToOne(optional=true,
//			   fetch   =FetchType.EAGER)
//	@JoinColumns({
//		@JoinColumn(name                ="FF_ORDER_ID",   // column in child  table PICKLIST_LINE
//				    referencedColumnName="FF_ORDER_ID"),  // column in parent table FF_ORDER_PART_LINE
//		@JoinColumn(name                ="FF_ORDER_LINE_NR",  // column in child  table PICKLIST_LINE
//				    referencedColumnName="FF_ORDER_LINE_NR"), // column in parent table FF_ORDER_PART_LINE
//		@JoinColumn(name                ="FFO_PART_NUM", // column in child  table PICKLIST_LINE
//                    referencedColumnName="PART_NUM")     // column in parent table FF_ORDER_PART_LINE
//		})
//	private FfOrderPartLine ffOrderPartLine;

	//ASSOCIATION relation to parent PickingResult
//	@ManyToOne(optional=true,
//			   fetch   =FetchType.LAZY)
//	@JoinColumn(name                ="PICKING_RESULT",  // column in child  table PICKLIST_LINE
//		    	referencedColumnName="RESULT")          // column in parent table PICKING_RESULT
////    @ReadOnly(UpdateAction.RESTRICT)
//	private PickingResult pickingResult;

	//ASSOCIATION relation to parent PickingResult ("Duplicate" parent columns in child to
	//                                              allow attaching to parent without reading the parent)
	@Column(name="PICKING_RESULT",
			nullable=false)
	private String pickingResultCode;

	//COMPOSITION relation to parent Picklist
//	@ManyToOne(optional=false,
//			   fetch   =FetchType.LAZY)
//	@JoinColumn(name                ="PICKLIST_ID",  // column in child  table PICKLIST_LINE
//	            referencedColumnName="PICKLIST_ID")  // column in parent table PICKLIST
//	private Picklist picklist;

	//ASSOCIATION relation to parent PicklistFfOrder
	//TODO review EAGER, was LAZY but this gave a delete from wrs_picklist_ff_order while doing an update of the picking_result
//    @ManyToOne(optional=true,
//    		   fetch   =FetchType.EAGER)
//	@JoinColumns({
//		@JoinColumn(name                ="FF_ORDER_ID",  // column in child  table PICKLIST_LINE
//					referencedColumnName="FF_ORDER_ID"), // column in parent table PICKLIST_FF_ORDER
//		@JoinColumn(name                ="PICKLIST_ID",  // column in child  table PICKLIST_LINE
//					referencedColumnName="PICKLIST_ID")  // column in parent table PICKLIST_FF_ORDER
//		})
//	private PicklistFfOrder picklistFfOrder;

    //ASSOCIATION relation to parent Parent ("Duplicate" parent columns in child to
    //  allow attaching to parent without reading the parent)
    @Column(name="FF_ORDER_ID",
            nullable=false)
    private long picklistFfOrderCode;


	//-------------------------
    //-- constructors        --
    //-------------------------
    public PicklistLine() {
    }

    //-------------------------
    //-- getters and setters --
    //-------------------------
    public PicklistLinePK getId() {
		return this.id;
	}
	public void setId(PicklistLinePK id) {
		this.id = id;
	}

	public String getSellingPartnerId() {
		return sellingPartnerId;
	}
	public void setSellingPartnerId(String sellingPartnerId) {
		this.sellingPartnerId = sellingPartnerId;
	}

	public String getCommentText() {
		return this.commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Integer getRayon() {
		return this.shelfNr;
	}
	public void setRayon(Integer shelfNr) {
		this.shelfNr = shelfNr;
	}

	public BooleanEnum getTempSkipped() {
		BooleanEnum eNum = BooleanEnum.findByCode(this.tempSkipped);
		if (eNum == null) {
			throw new RuntimeException("No BooleanEnum for '"+this.tempSkipped+"'");
		}
		return eNum;
	}

	public void setTempSkipped(BooleanEnum tempSkipped) {
		this.tempSkipped = tempSkipped.getCode();
	}

	/*
	public Timestamp getTsInsert() {
		return this.tsInsert;
	}
	public void setTsInsert(Timestamp tsInsert) {
		this.tsInsert = tsInsert;
	}
	*/

	/*
	public Timestamp getTsLastUpd() {
		return this.tsLastUpd;
	}
	public void setTsLastUpd(Timestamp tsLastUpd) {
		this.tsLastUpd = tsLastUpd;
	}
	*/

	/*
	public List<WrsPickingTr> getWrsPickingTrs() {
		return this.wrsPickingTrs;
	}
	public void setWrsPickingTrs(List<WrsPickingTr> wrsPickingTrs) {
		this.wrsPickingTrs = wrsPickingTrs;
	}
	*/

}