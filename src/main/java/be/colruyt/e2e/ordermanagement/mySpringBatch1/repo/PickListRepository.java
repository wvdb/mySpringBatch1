package be.colruyt.e2e.ordermanagement.mySpringBatch1.repo;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Customer;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt.Picklist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Wim Van den Brande on 25/04/2018.
 *
 */
@RepositoryRestResource(collectionResourceRel = "picklists", path = "picklists")
public interface PickListRepository extends CrudRepository<Picklist,Long> {
//    Customer findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
