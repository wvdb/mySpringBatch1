package be.colruyt.e2e.ordermanagement.mySpringBatch1.repo.domain;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt.PickListWithCarrierType;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt.Picklist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Wim Van den Brande on 25/04/2018.
 *
 */
@RepositoryRestResource(collectionResourceRel = "picklists", path = "picklists")
public interface PickListRepository extends CrudRepository<Picklist,Long> {
    List<Picklist> findByCarrierTypeAndCollectPointId(@Param("carrierType") String carrierType, @Param("collectPointId") Integer collectPointId);

    @Query(value = "SELECT * FROM Picklist p WHERE p.PICKLIST_ID = ?1",
            nativeQuery = true)
    Picklist findPickListById(long picklistId);

    @Query("SELECT new be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt.PickListWithCarrierType(c.carrierType, c.minSlotsAdvance, p.picklistId, p.ffLocation, p.picklistKind) "
            + " FROM Picklist p, CarrierType c "
            + " WHERE p.carrierType = c.carrierType"
            + " AND p.picklistId = ?1")
    PickListWithCarrierType findPickListWithCarrierType(long picklistId);
}
