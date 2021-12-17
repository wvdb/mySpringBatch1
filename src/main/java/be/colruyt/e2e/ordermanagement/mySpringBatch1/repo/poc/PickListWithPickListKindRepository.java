package be.colruyt.e2e.ordermanagement.mySpringBatch1.repo.poc;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.poc.PickListWithPickListKind;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.repo.domain.PickListRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Wim Van den Brande on 25/04/2018.
 *
 */
public interface PickListWithPickListKindRepository extends PickListRepository {
    @Query("SELECT new be.colruyt.e2e.ordermanagement.mySpringBatch1.model.poc.PickListWithPickListKind(plk.kind, plk.crateType, p.picklistId, p.ffLocation) "
            + " FROM Picklist p, PicklistKind plk "
            + " WHERE p.picklistKind = plk.kind"
            + " AND p.picklistId = ?1")
    PickListWithPickListKind findPickListWithPickListKind(long picklistId);
}
