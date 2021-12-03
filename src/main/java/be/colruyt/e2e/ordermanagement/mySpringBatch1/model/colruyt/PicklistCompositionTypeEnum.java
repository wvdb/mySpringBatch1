package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

public enum PicklistCompositionTypeEnum {
	
	COMP_1("COMP_1"),
	COMP_2("COMP_2"),
	COMP_3("COMP_3"),
	COMP_4("COMP_4");
	
	private String code;

	//-------------------------
	//-- constructors        --
	//-------------------------
	private PicklistCompositionTypeEnum(String inCode) {
		this.code = inCode;
	}
	
	//-------------------------
	//-- getters and setters --
	//-------------------------
	public String getCode() {
		return this.code;
	}
	
	public static PicklistCompositionTypeEnum findByCode(String inCode) {
		
		for (PicklistCompositionTypeEnum pickListSourceEnum : PicklistCompositionTypeEnum.values()) {
			
			if (pickListSourceEnum.getCode().equals(inCode)) {
				return pickListSourceEnum;
			}
		}
		return null;
	}

}
