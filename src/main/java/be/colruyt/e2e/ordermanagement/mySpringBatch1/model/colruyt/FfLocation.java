package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="X_FF_LOCATION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FfLocation implements Serializable {
	@Id
	@Column(name="FF_LOCATION")
	private int ffLocation;

}