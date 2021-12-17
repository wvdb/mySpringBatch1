package be.colruyt.e2e.ordermanagement.mySpringBatch1.controller;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.colruyt.PickListWithCarrierType;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.poc.PickListWithPickListKind;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.wim.Customer;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.repo.domain.PickListRepository;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.repo.poc.PickListWithPickListKindRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MyController {
    @Autowired
    private Customer customer;

    @Autowired
    private PickListRepository pickListRepository;

    @Autowired
    private PickListWithPickListKindRepository pickListWithPickListKindRepository;

    @GetMapping(value="/dummy",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> dummy() {
        log.info("Created customer = " + customer);
        return ResponseEntity.ok(customer);
    }

    @GetMapping(value= "/getPickListWithCarrierType",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PickListWithCarrierType> getPickListWithCarrierType(@RequestParam("pickListId") Long pickListId) {
        return ResponseEntity.ok(pickListRepository.findPickListWithCarrierType(pickListId));
    }

    @GetMapping(value= "/getPickListWithPickListKind",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PickListWithPickListKind> getPickListWithPickListKind(@RequestParam("pickListId") Long pickListId) {
        return ResponseEntity.ok(pickListWithPickListKindRepository.findPickListWithPickListKind(pickListId));
    }
}
