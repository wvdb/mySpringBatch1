package be.colruyt.e2e.ordermanagement.mySpringBatch1;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Customer;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MySpringBatch1Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(value = {"classpath:application.properties", "classpath:application-unit-test.properties"})
public class SDRTest {
    @Autowired
    private TestRestTemplate template;

    private static String CUSTOMER_ENDPOINT = "/customers";
    private static String ITEM_ENDPOINT = "/items/";

    private static String ADDRESS_ENDPOINT = "http://localhost:8188/addresses/";
    private static String LIBRARY_ENDPOINT = "http://localhost:8188/libraries/";

    @Test
    public void whenPersistCustomer_thenCorrect() {
        String testFirstName = "Donald";
        String testLastName = "Test Trump1 " + UUID.randomUUID();

        Customer customer = new Customer(testFirstName, testLastName);
        template.postForEntity(CUSTOMER_ENDPOINT, customer, Customer.class);

        Map<String, Object> uriVariables = new LinkedHashMap<>();
        uriVariables.put("firstName", testFirstName);
        uriVariables.put("lastName", testLastName);

        String uri = CUSTOMER_ENDPOINT + "/search/findByFirstNameAndLastName?firstName={firstName}&lastName={lastName}";

        ResponseEntity<Customer> retrievedCustomer  = template.getForEntity(uri, Customer.class, uriVariables);
        assertEquals(Objects.requireNonNull(retrievedCustomer.getBody()).getFirstName(), testFirstName);
    }

    @Test
    public void whenPersistCustomerAndPersistItemAndAssociateItemWithCustomer_thenCorrect() throws JSONException {
        // part 1 - create parent (customer)

        String testFirstName = "Donald";
        String testLastName = "Test Trump1 " + UUID.randomUUID();
        Customer customer = new Customer(testFirstName, testLastName);

        Map<String, Object> customerUriVariables = new LinkedHashMap<>();
        customerUriVariables.put("firstName", testFirstName);
        customerUriVariables.put("lastName", testLastName);
        template.postForEntity(CUSTOMER_ENDPOINT, customer, Customer.class);
        String retrievedCustomerAsJson  = template.getForObject(CUSTOMER_ENDPOINT + "/search/findByFirstNameAndLastName?firstName={firstName}&lastName={lastName}", String.class, customerUriVariables);
        String customerHref = (String) new JSONObject(retrievedCustomerAsJson).getJSONObject("_links").getJSONObject("self").get("href");

        // part 2 - create child (item -> no parent/customer)
        String testItemName = "item " + UUID.randomUUID();
        Item item = new Item(testItemName);

        Map<String, Object> itemUriVariables = new LinkedHashMap<>();
        itemUriVariables.put("name", testItemName);
        template.postForEntity(ITEM_ENDPOINT, item, Item.class);
        String retrievedItemAsJson1  = template.getForObject(ITEM_ENDPOINT + "/search/findByName?name={name}", String.class, itemUriVariables);
        String itemHref = (String) new JSONObject(retrievedItemAsJson1).getJSONObject("_links").getJSONObject("self").get("href");

        // part 3 - associate child with parent
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-type", "text/uri-list");
        HttpEntity<String> httpEntity = new HttpEntity<>(customerHref, requestHeaders);
        ResponseEntity<String> retrievedItemAfterAssociationAsJson = template.exchange(itemHref + "/customer", HttpMethod.PUT, httpEntity, String.class);

        // and validate whether our customer has the correct item and only ONE item
        String retrievedCustomerWithItemsAsJson  = template.getForObject(customerHref + "/items", String.class);
        JSONArray itemsOfCustomer = new JSONObject(retrievedCustomerWithItemsAsJson).getJSONObject("_embedded").getJSONArray("items");
        String itemName = (String) itemsOfCustomer.getJSONObject(0).get("name");
        assertEquals(testItemName, itemName);
        assertThrows(JSONException.class, () -> {
            itemsOfCustomer.getJSONObject(1);
        });
    }

}
