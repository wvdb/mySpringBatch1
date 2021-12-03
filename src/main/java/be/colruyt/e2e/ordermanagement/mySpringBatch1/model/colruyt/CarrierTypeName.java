package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the CARRIER_TYPE_NAME database table.
 * 
 */
@Entity
@Table(name="CARRIER_TYPE_NAME")
public class CarrierTypeName implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CarrierTypeNamePK id;
    
    @Column(name="NAME")
    private String name;
    
    //ASSOCIATION relation to parent IsoLanguage
    @ManyToOne(optional=true,
               fetch   =FetchType.LAZY)
    @JoinColumn(name                ="ISO_LANG_CODE",  // column in child table CARRIER_TYPE_NAME
                referencedColumnName="ISO_LANG_CODE")  // column in parent table REF_ISO_LANGUAGE
    private IsoLanguage isoLanguage;
    
    
    //-------------------------
    //-- constructors        --
    //-------------------------
    public CarrierTypeName() {
    }


    //-------------------------
    //-- getters and setters --
    //-------------------------
    public CarrierTypeNamePK getId() {
        return id;
    }
    public void setId(CarrierTypeNamePK id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public IsoLanguage getIsoLanguage() {
        return isoLanguage;
    }    public void setIsoLanguage(IsoLanguage isoLanguage) {
        this.isoLanguage = isoLanguage;
    }
    
}
