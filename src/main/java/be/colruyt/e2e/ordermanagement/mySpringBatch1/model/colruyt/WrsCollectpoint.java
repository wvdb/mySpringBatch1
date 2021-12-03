package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;


/**
 * The persistent class for the WRS_COLLECTPOINT database table.
 * 
 */
//@NamedQueries ({
//	@NamedQuery(name  = WrsCollectpoint.SEL_CP_BY_BRANCHNR,
//			    query = WrsCollectpoint.QUERY_SEL_CP_BY_BRANCHNR),
//	@NamedQuery(name  = WrsCollectpoint.SEL_CP_WITH_FFOPART_BY_FFLOC_AND_KIND_AND_COLLECTDATE,
//			    query = WrsCollectpoint.QUERY_SEL_CP_WITH_FFOPART_BY_FFLOC_AND_KIND_AND_COLLECTDATE)
//
//})


@Entity
@Table(name="WRS_COLLECTPOINT")
public class WrsCollectpoint implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String SEL_CP_BY_BRANCHNR = "WrsCollectpoint.query.selByBranchnr";
	static final String QUERY_SEL_CP_BY_BRANCHNR = "SELECT c"
		                                           +" FROM WrsCollectpoint c"
		                                           +" WHERE c.salesBranch = :aBranchId";

	public static final String SEL_CP_WITH_FFOPART_BY_FFLOC_AND_KIND_AND_COLLECTDATE = "WrsCollectpoint.query.selCPWithFfoPartByFfLocAndKindAndCollectDate";
	static final String QUERY_SEL_CP_WITH_FFOPART_BY_FFLOC_AND_KIND_AND_COLLECTDATE = "SELECT DISTINCT cp"
			                                                                          +" FROM    FfOrderPart       ffop"
			                                                                          +"      INNER JOIN"
			                                                                          +"         ffop.ffOrder      ffo"
		                                                                              +"      INNER JOIN"
		                                                                              +"         ffo.wrsCollectpoint cp"
		                                                                              +" WHERE     ffo.logicallyDeleted   = 'N'" 
		                                                                              +"       AND ffo.ffLocation         = :aFfLocation"
                                                                                      +"       AND ffo.collectDate        = :aCollectDate" 
		                                                                              +"       AND ffop.logicallyDeleted  = 'N'"
		                                                                              +"       AND ffop.picklistKind.kind = :aPicklistKind";

	
	@Id
	@Column(name="WRS_COLLECTPOINT_ID")
	private long collectpointId;

	/* Not needed because relation is ASSOCIATION 
	private List<FfOrder> ffOrders;
	*/

    //COMPOSITION relation to WrsCollectpointName
//    @ElementCollection(fetch=FetchType.EAGER,
//    		           targetClass= WrsCollectpointName.class)
//    @CollectionTable(joinColumns=@JoinColumn(referencedColumnName="WRS_COLLECTPOINT_ID",   //in parent XCOLLECTPOINT tabel
//			     			 			     name                ="WRS_COLLECTPOINT_ID"  ) //in child XCOLLECTPOINT_NAME tabel
//    	             )
//    @JoinColumn(referencedColumnName="ISO_LANG_CODE")
////	@ReadOnly(UpdateAction.RESTRICT)
//	private Map<String, WrsCollectpointName> wrsCollectpointNames;

	


	//-------------------------
	//-- constructors        --
	//-------------------------
	public WrsCollectpoint() {
    }

	
	//-------------------------
	//-- getters and setters --
	//-------------------------
	public long getCollectpointId() {
		return this.collectpointId;
	}
	public void setCollectpointId(long collectpointId) {
		this.collectpointId = collectpointId;
	}
	
//	public Map<String, WrsCollectpointName> getWrsCollectpointLangs() {
//		return wrsCollectpointNames;
//	}
//	public void setWrsCollectpointLangs(Map<String, WrsCollectpointName> wrsCollectpointLangs) {
//		this.wrsCollectpointNames = wrsCollectpointLangs;
//	}
//
//	//------------------------------------
//	//-- ADDITIONAL getters and setters --
//	//------------------------------------
//	public String getNameForIsoLangCode(String inIsoLangCode) {
//		WrsCollectpointName currentWrsCollectpointName = this.wrsCollectpointNames.get(inIsoLangCode);
//
//		if (currentWrsCollectpointName != null) {
//			return currentWrsCollectpointName.getName();
//		} else {
//			return null;
//		}
//	}
}