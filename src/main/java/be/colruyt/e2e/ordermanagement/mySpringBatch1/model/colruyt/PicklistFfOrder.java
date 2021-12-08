package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PicklistFfOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="FF_ORDER_ID")
	private Long ffOrderId;

	private String custId;

	@Column(name="NR_SLOTS")
	private Long numberOfSlots;
}