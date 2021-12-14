package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.wim;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Wim Van den Brande on 25/04/2018.
 *
 */
@RepositoryRestResource(collectionResourceRel = "items", path = "items")
public interface ItemRepository extends CrudRepository<Item,Integer> {
    Item findByName(@Param("name") String name);
}
