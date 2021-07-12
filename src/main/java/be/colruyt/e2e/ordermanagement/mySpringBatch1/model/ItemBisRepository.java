package be.colruyt.e2e.ordermanagement.mySpringBatch1.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Wim Van den Brande on 25/04/2018.
 *
 */
@RepositoryRestResource(collectionResourceRel = "itemsBis", path = "itemsBis")
public interface ItemBisRepository extends CrudRepository<ItemBis,Integer> {
    ItemBis findByName(@Param("name") String name);
}
