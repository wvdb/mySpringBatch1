package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.poc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dummy1 implements Serializable {
    private static final String SEQ_DUMMY1_ID = "Dummy1.sequence.DUMMY1_SEQ";

    @Id
    @SequenceGenerator(name = SEQ_DUMMY1_ID,
            sequenceName  = "DUMMY1_SEQ",
            allocationSize= 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator= SEQ_DUMMY1_ID)
    private Long id;

    private String name;

    public Dummy1(String name) {
        this.name = name;
    }
}
