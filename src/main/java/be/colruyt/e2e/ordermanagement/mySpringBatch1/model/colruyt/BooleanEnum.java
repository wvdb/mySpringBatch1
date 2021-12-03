package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

public enum BooleanEnum {
	
	True  ("Y"),
	False ("N");
	
	private String code;
	
	//-------------------------
	//-- constructors        --
	//-------------------------
	private BooleanEnum(String inCode) {
		this.code = inCode;
	}
	
	//-------------------------
	//-- getters and setters --
	//-------------------------
	public String getCode() {
		return this.code;
	}
	
	public static BooleanEnum findByCode (String inCode) {
		
		for (BooleanEnum booleanEnum : BooleanEnum.values()) {
			
			if (booleanEnum.getCode().equals(inCode)) {
				return booleanEnum;
			}
		}
		// enum not found
		return null;
	}
}
