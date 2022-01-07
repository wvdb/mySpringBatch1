package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PicklistShort implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Long picklistId;
    protected Integer ffLocation;
}