package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the CARRIER_TYPE_NAME database table.
 * 
 */
@Embeddable
public class CarrierTypeNamePK implements Serializable {
    private static final long serialVersionUID = 1L;

   
    @Column(name="CARRIER_TYPE")
    private String carrierType;
   
    @Column(name="ISO_LANG_CODE")
    private String isoLangCode;
    
    //-------------------------
    //-- constructors        --
    //-------------------------
    public CarrierTypeNamePK() {
    }

    
    //-------------------------
    //-- getters and setters --
    //-------------------------
    public String getCarrierType() {
        return carrierType;
    }
    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
    }

    public String getIsoLangCode() {
        return isoLangCode;
    }
    public void setIsoLangCode(String isoLangCode) {
        this.isoLangCode = isoLangCode;
    }

    
    //-------------------------
    //-- overrides           --
    //-------------------------
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((carrierType == null) ? 0 : carrierType.hashCode());
        result = prime * result
                + ((isoLangCode == null) ? 0 : isoLangCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CarrierTypeNamePK other = (CarrierTypeNamePK) obj;
        if (carrierType == null) {
            if (other.carrierType != null)
                return false;
        } else if (!carrierType.equals(other.carrierType))
            return false;
        if (isoLangCode == null) {
            if (other.isoLangCode != null)
                return false;
        } else if (!isoLangCode.equals(other.isoLangCode))
            return false;
        return true;
    }



    
    
}
