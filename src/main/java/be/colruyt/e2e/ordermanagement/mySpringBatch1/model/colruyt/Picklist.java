package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;


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
@Data
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
	protected Long picklistId;

//	@ManyToOne(optional=true, fetch=FetchType.LAZY)
//	@JoinColumn(name="FF_LOCATION", referencedColumnName="FF_LOCATION")
//	private FfLocation ffLocation;
	protected Integer ffLocation;

//	@ManyToOne(optional=true, fetch=FetchType.LAZY)
//	@JoinColumn(name="COLLECTPOINT_ID", referencedColumnName="WRS_COLLECTPOINT_ID")
//	private CollectPoint collectPoint;
    @Column(name="COLLECTPOINT_ID")
	private Integer collectPointId;

//	@ManyToOne(optional=true, fetch=FetchType.LAZY)
//	@JoinColumn(name="PICKLIST_KIND", referencedColumnName="KIND", nullable = false) // column in parent table PICKLIST_KIND
//	private PicklistKind picklistKind;
    protected String picklistKind;

//	@ManyToOne(optional=true, fetch=FetchType.LAZY)
//	@JoinColumn(name="CARRIER_TYPE", referencedColumnName="CARRIER_TYPE")
//	private CarrierType carrierType;
	private String carrierType;

	@Column(name="REQUESTED_BY")
	private String requestedBy;
	
	@Column(name="REQUEST_SOURCE")
	private String requestSource;

	@Column(name="TS_INSERT")
	private Timestamp tsInsert;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "picklist_line", joinColumns = @JoinColumn(name = "PICKLIST_ID"))
	@OrderBy("picklistLineNr ASC")
	private Set<PicklistLine> picklistLines;

	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "picklist_ff_order", joinColumns = @JoinColumn(name = "PICKLIST_ID"))
	private Set<PicklistFfOrder> picklistFfOrders;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Picklist)) return false;
		Picklist picklist = (Picklist) o;
		return Objects.equals(getPicklistId(), picklist.getPicklistId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPicklistId());
	}
}