package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="WRS_COLLECTPOINT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectPoint implements Serializable {

	@Id
	@Column(name="WRS_COLLECTPOINT_ID")
	private int wrsCollectpointId;
}