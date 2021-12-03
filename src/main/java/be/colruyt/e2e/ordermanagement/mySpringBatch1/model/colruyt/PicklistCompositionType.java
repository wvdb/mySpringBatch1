package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The persistent class for the PICKLIST_COMPOSITION_TYPE database table.
 * 
 */
@Entity
@Table(name="PICKLIST_COMPOSITION_TYPE")
public class PicklistCompositionType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="COMPOSITION_TYPE")
    private String compositionType;
    
    
    //-------------------------
    //-- constructors        --
    //-------------------------
    public PicklistCompositionType() {
    }

    
    //-------------------------
    //-- getters and setters --
    //-------------------------
    public String getCompositionType() {
        return compositionType;
    }

    public void setCompositionType(String compositionType) {
        this.compositionType = compositionType;
    }
    
    
}
