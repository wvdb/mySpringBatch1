package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

public enum TreatmentEnum {
	
	NORMAL("NORMAL"),
	ADDITION("ADDITION");
	
	private String code;

	//-------------------------
	//-- constructors        --
	//-------------------------
	private TreatmentEnum(String inCode) {
		this.code = inCode;
	}
	
	//-------------------------
	//-- getters and setters --
	//-------------------------
	public String getCode() {
		return this.code;
	}
	
	public static TreatmentEnum findByCode(String inCode) {
		
		for (TreatmentEnum treatmentEnum : TreatmentEnum.values()) {
			
			if (treatmentEnum.getCode().equals(inCode)) {
				
				return treatmentEnum;
			}
		}
		
		
		
		return null;
	}

}
