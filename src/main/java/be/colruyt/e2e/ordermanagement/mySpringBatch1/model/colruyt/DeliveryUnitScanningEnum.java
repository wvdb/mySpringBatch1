package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

public enum DeliveryUnitScanningEnum {
	
	None("NONE"),
	EachArticle("EACH_ARTICLE"),
	DuringPicking("DURING_PICKING");      
	
	private String code;


	//-------------------------
	//-- constructors        --
	//-------------------------
	private DeliveryUnitScanningEnum(String inCode) {
		this.code = inCode;
	}
	
	//-------------------------
	//-- getters and setters --
	//-------------------------
	public String getCode() {
		return this.code;
	}
	
	public static DeliveryUnitScanningEnum findByCode(String inCode) {
		
		for (DeliveryUnitScanningEnum deliveryUnitScanningEnum : DeliveryUnitScanningEnum.values()) {
			
			if (deliveryUnitScanningEnum.getCode().equals(inCode)) {
				return deliveryUnitScanningEnum;
			}
		}
		return null;
	}

}
