package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * The persistent class for the PICKLIST database table.
 * 
 */
//@NamedQueries ({
//	@NamedQuery(name  = Picklist.SEL_PICKLIST_BY_ID,
//		        query = Picklist.QUERY_SEL_PICKLIST_BY_ID)
//})
//@FetchGroups({
//    @FetchGroup(name      = Picklist.FETCHGROUP_PICKLISTLINES,
//    		    attributes={@FetchAttribute(name="picklistLines")}
//               )
//})
@Entity
@Table(name="PICKLIST")
public class Picklist implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FETCHGROUP_PICKLISTLINES = "Picklist.fetchgroup.picklistLines";

	public static final String SEL_PICKLIST_BY_ID = "Picklist.query.selPicklistById";
	static final String QUERY_SEL_PICKLIST_BY_ID = "SELECT pl" 
			                                      +" FROM Picklist pl"
			                                      +" WHERE pl.picklistId = :inPicklistId";
	

	private static final String SEQ_PICKLISTID = "Picklist.sequence.SEQ_PICKLISTID";
	
	@Id
	@SequenceGenerator(name          = Picklist.SEQ_PICKLISTID,
	   		   		   sequenceName  = "SEQ_PICKLISTID", 
	   		   		   allocationSize= 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
		    		generator= Picklist.SEQ_PICKLISTID)
	@Column(name="PICKLIST_ID")
	private long picklistId;

//	//ASSOCIATION relation to parent WrsCollectpoint
//	@ManyToOne(optional=true, fetch=FetchType.LAZY)
//	@JoinColumn(referencedColumnName="WRS_COLLECTPOINT_ID")
//	private WrsCollectpoint wrsCollectpoint;

	//ASSOCIATION relation to parent WrsCollectpoint ("Duplicate" parent columns in child to
	//	                               allow attaching to parent without reading the parent)
	@Column(name="COLLECTPOINT_ID",
			nullable=false)
	private long collectpointId;

	@Column(name="FF_LOCATION")
	private long ffLocation;
	
	@Column(name="REQUESTED_BY")
	private String requestedBy;
	
	@Column(name="REQUEST_SOURCE")
	private String requestSource;

	/* not needed as auto filled by db, but content needed in retrieval */
	@Column(name="TS_INSERT")
//	@ReadOnly(UpdateAction.RESTRICT)
	private Timestamp tsInsert;
	

	/* not needed as auto filled by db
	@Column(name="TS_LAST_UPD")
	private Timestamp tsLastUpd;
	*/

	/* Not needed because relation is ASSOCIATION 
	private List<FfOrderPart> ffOrderParts;
	*/

	/* Not needed because relation is ASSOCIATION 
	private WrsPicking wrsPicking;
	*/

	//ASSOCIATION relation to parent PicklistKind
	@ManyToOne(optional=true, fetch=FetchType.LAZY)
	@JoinColumn(name="PICKLIST_KIND", referencedColumnName="KIND", nullable = false) // column in parent table PICKLIST_KIND
	private PicklistKind picklistKind;

	//ASSOCIATION relation to parent PicklistKind ("Duplicate" parent columns in child to
	//                                              allow attaching to parent without reading the parent)
	@Column(name="PICKLIST_KIND",
			insertable = false,
			updatable = false,
			nullable = false)
	private String picklistKindCode;
	
	//ASSOCIATION relation to parent CarrierType
    @ManyToOne(optional=true, fetch=FetchType.LAZY)
    @JoinColumn(name="CARRIER_TYPE"
			, referencedColumnName="CARRIER_TYPE"
			, nullable = false) // column in parent table CARRIER_TYPE
    private CarrierType carrierType;
    
    //COMPOSITION relation to PicklistLine
//    @OneToMany(targetEntity = PicklistLine.class,
//            fetch        = FetchType.LAZY,   /* !! can be made EAGER for a query by adding fetchgroup Picklist.FETCHGROUP_PICKLISTLINES */
//            mappedBy     = "picklist",       /* Join columns to be found in this attribute of child */
//      	    cascade      = CascadeType.ALL,  /* entrie(s) in list can be added/modified (child must be added/modified) */
//            orphanRemoval= true)             /* entrie(s) in list can be removed, in that case also remove child itself (if no new parent was assigned) */
//    /* No join columns : mappedBy tells us to find join in child */
//    @OrderBy("id.picklistLineNr ASC")        // sort on expected picking order
//    @KeyColumn(name="PICKLIST_LINE_NR")
//	private SortedMap<Long,PicklistLine> picklistLines;
    

    //COMPOSITION relation to PicklistFfOrder
//    @OneToMany(targetEntity = PicklistFfOrder.class,
//            fetch        = FetchType.EAGER,
//            mappedBy     = "picklist",    /* Join columns to be found in this attribute of child */
//      	    cascade      = CascadeType.ALL,  /* entrie(s) in list can be added/modified (child must be added/modified) */
//            orphanRemoval= true)             /* entrie(s) in list can be removed, in that case also remove child itself (if no new parent was assigned) */
//    /* No join columns : mappedBy tells us to find join in child */
//    @OrderBy("id.ffOrderId ASC")        // sort on ff orderid
//    @KeyColumn(name="CUST_ID")
//	private SortedMap<String, PicklistFfOrder> picklistFfOrders;

	
	//-------------------------
	//-- constructors        --
	//-------------------------
	public Picklist() {
    }

	
	//-------------------------
	//-- getters and setters --
	//-------------------------
	public long getPicklistId() {
		return this.picklistId;
	}
	public void setPicklistId(long picklistId) {
		this.picklistId = picklistId;
	}

//	public WrsCollectpoint getWrsCollectpoint() {
//		return wrsCollectpoint;
//	}
//	/* maintaining relation via parent columns, not via object to avoid the need to read it)
//	public void setWrsCollectpoint(WrsCollectpoint wrsCollectpoint) {
//		this.wrsCollectpoint = wrsCollectpoint;
//	}
//	*/

	public long getCollectpointId() {
		return this.collectpointId;
	}
	public void setCollectpointId(long collectpointId) {
		this.collectpointId = collectpointId;
	}

	public long getFfLocation() {
		return this.ffLocation;
	}
	public void setFfLocation(long ffLocation) {
		this.ffLocation = ffLocation;
	}
	
	public String getRequestedBy() {
        return requestedBy;
    }
	public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }
	
	public String getRequestSource() {
        return requestSource;
    }
	public void setRequestSource(String requestSource) {
        this.requestSource = requestSource;
    }

	public Timestamp getTsInsert() {
		return this.tsInsert;
	}
	/*
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

//	public List<FfOrderPart> getFfOrderParts() {
//		return this.ffOrderParts;
//	}
	/* COMPOSITION: list content changed via parent methods => no need for setting "new/updated" list 
	public void setFfOrderParts(List<FfOrderPart> ffOrderParts) {
		this.ffOrderParts = ffOrderParts;
	}
	*/
	
	/*
	public WrsPicking getWrsPicking() {
		return this.wrsPicking;
	}
	public void setWrsPicking(WrsPicking wrsPicking) {
		this.wrsPicking = wrsPicking;
	}
	*/
	
	public PicklistKind getPicklistKind() {
		return this.picklistKind;
	}
	/* maintaining relation via parent columns, not via object to avoid the need to read it)
	public void setPicklistKind(PicklistKind picklistKind) {
		this.picklistKind = picklistKind;
	}
	*/

	public String getPicklistKindCode() {
		return picklistKindCode;
	}
	public void setPicklistKindCode(String picklistKindCode) {
		this.picklistKindCode = picklistKindCode;
	}

//	public Map<Long,PicklistLine> getPicklistLines() {
//		return this.picklistLines;
//	}
//	/* COMPOSITION: list content changed via parent methods => no need for setting "new/updated" list
//	public void setPicklistLines(List<PicklistLine> picklistLines) {
//		this.picklistLines = picklistLines;
//	}
//	*/
//
//	public Map<String, PicklistFfOrder> getPicklistFfOrders() {
//		return this.picklistFfOrders;
//	}
//	public void setPicklistFfOrders(TreeMap<String, PicklistFfOrder> picklistFfOrders) {
//		this.picklistFfOrders = picklistFfOrders;
//	}
	
    public CarrierType getCarrierType() {
        return carrierType;
    }
    public void setCarrierType(CarrierType carrierType) {
        this.carrierType = carrierType;
    }
	
	
	//------------------------------------
	//-- ADDITIONAL getters and setters --
	//------------------------------------
	
    /* For COMPOSITION to PicklistFfOrder  (add = via this setter;  remove via remove on List; modify via setters for child in list) */
//	public void addPicklistFfOrder(PicklistFfOrder inPicklistFfOrder) {
// 		/* check if parentkey already known (might be missing in case of sequence,....) */
// 		if (   this.getPicklistId()  == 0) {
// 			throw new InvalidParentKeyRuntimeException("Parent key 'id.picklistId' has no value yet! Create parent in DB before adding childs!");
// 		}
//
//
//		/* Set parent to current parent */
//		inPicklistFfOrder.setPicklist(this);
//		inPicklistFfOrder.getId().setPicklistId(this.getPicklistId());
//
//		/* add WrsPicklistFfOrder in the list */
//		if (this.picklistFfOrders == null) {
//			this.picklistFfOrders = new TreeMap<String, PicklistFfOrder>();
//		}
//
//		this.picklistFfOrders.put(inPicklistFfOrder.getCustomerIdentyCode(), inPicklistFfOrder);
//	}
//
//	/* For COMPOSITION to PicklistLine  (add = via this setter;  remove via remove on List; modify via setters for child in list) */
//	public void addPicklistLine(PicklistLine inPicklistLine) {
// 		/* check if parentkey already known (might be missing in case of sequence,....) */
// 		if (   this.getPicklistId()  == 0) {
// 			throw new InvalidParentKeyRuntimeException("Parent key 'id.picklistId' has no value yet! Create parent in DB before adding childs!");
// 		}
//
//
//		/* Set parent to current parent */
//		inPicklistLine.setPicklist(this);
//		inPicklistLine.getId().setPicklistId(this.getPicklistId());
//
//		/* add WrsPicklistLine in the list */
//		if (this.picklistLines == null) {
//			this.picklistLines = new TreeMap<Long, PicklistLine>();
//		}
//
//
//		this.picklistLines.put(inPicklistLine.getId().getPicklistLineNr() , inPicklistLine);
//	}

	
}