//package be.colruyt.e2e.ordermanagement.mySpringBatch1;
//
//import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Customer;
//import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.Item;
//import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.ItemBis;
//import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.PurchaseItemComplex;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.ZonedDateTime;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThrows;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MySpringBatch1Application.class,
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(value = {"classpath:application.properties", "classpath:application-unit-test.properties"})
//public class SDRTest {
//    @Autowired
//    private TestRestTemplate template;
//
//    private static String CUSTOMER_ENDPOINT = "/customers";
//    private static String ITEM_ENDPOINT = "/items/";
//    private static String ITEM_BIS_ENDPOINT = "/itemsBis/";
//
//    private static String ADDRESS_ENDPOINT = "http://localhost:8188/addresses/";
//    private static String LIBRARY_ENDPOINT = "http://localhost:8188/libraries/";
//
//    @Test
//    public void whenPersistBasicCustomer_thenCorrect() {
//        String testFirstName = "Donald";
//        String testLastName = "Test Trump1 " + UUID.randomUUID();
//
//        Customer customer = new Customer(testFirstName, testLastName);
//        template.postForEntity(CUSTOMER_ENDPOINT, customer, Customer.class);
//
//        Map<String, Object> uriVariables = new LinkedHashMap<>();
//        uriVariables.put("firstName", testFirstName);
//        uriVariables.put("lastName", testLastName);
//
//        String uri = CUSTOMER_ENDPOINT + "/search/findByFirstNameAndLastName?firstName={firstName}&lastName={lastName}";
//
//        ResponseEntity<Customer> retrievedCustomer  = template.getForEntity(uri, Customer.class, uriVariables);
//        assertEquals(Objects.requireNonNull(retrievedCustomer.getBody()).getFirstName(), testFirstName);
//    }
//
//    @Test
//    public void whenPersistCustomerWithComplexPurchases_thenCorrect() throws JSONException {
//        // part 1 - create parent (customer)
//        String testFirstName = "with Purchase";
//        String testLastName = "testCustomer " + UUID.randomUUID();
//
//        Customer customer = new Customer(testFirstName, testLastName);
//
//        Map<String, Object> customerUriVariables = new LinkedHashMap<>();
//        customerUriVariables.put("firstName", testFirstName);
//        customerUriVariables.put("lastName", testLastName);
//        template.postForEntity(CUSTOMER_ENDPOINT, customer, Customer.class);
//        String retrievedCustomerAsJson  = template.getForObject(CUSTOMER_ENDPOINT + "/search/findByFirstNameAndLastName?firstName={firstName}&lastName={lastName}", String.class, customerUriVariables);
//        String customerHref = (String) new JSONObject(retrievedCustomerAsJson).getJSONObject("_links").getJSONObject("self").get("href");
//
//        // part 2 - create parent (itemBis)
//        String testItemName = "item " + UUID.randomUUID();
//
//        ItemBis itemBis = new ItemBis(testItemName);
//        Map<String, Object> itemUriVariables = new LinkedHashMap<>();
//        itemUriVariables.put("name", testItemName);
//        template.postForEntity(ITEM_BIS_ENDPOINT, itemBis, ItemBis.class);
//        String retrievedItemAsJson1  = template.getForObject(ITEM_BIS_ENDPOINT + "/search/findByName?name={name}", String.class, itemUriVariables);
//        String itemHref = (String) new JSONObject(retrievedItemAsJson1).getJSONObject("_links").getJSONObject("self").get("href");
//
//        // part 3 - update customer with purchases
//        int customerId = resolveId(customerHref);
//        int itemId = resolveId(itemHref);
////        PurchaseItemComplex.PurchaseItemComplexId purchaseItemComplexId = new PurchaseItemComplex.PurchaseItemComplexId(customerId, itemId);
//        PurchaseItemComplex purchaseItemComplex = new PurchaseItemComplex();
//        purchaseItemComplex.setItemId(itemId);
//        purchaseItemComplex.setPurchaseAmount(100.00F);
//        purchaseItemComplex.setPurchaseDate(ZonedDateTime.now());
//
//        customer.setPurchasedItems(Collections.singleton(purchaseItemComplex));
//        HttpEntity<Customer> requestUpdate = new HttpEntity<>(customer);
//        template.exchange(CUSTOMER_ENDPOINT + "/" + customerId, HttpMethod.PUT, requestUpdate, Void.class);
//    }
//
//    @Test
//    public void whenPersistCustomerAndPersistItemAndAssociateItemWithCustomer_thenCorrect() throws JSONException {
//        // part 1 - create parent (customer)
//
//        String testFirstName = "Donald";
//        String testLastName = "Test Trump1 " + UUID.randomUUID();
//        Customer customer = new Customer(testFirstName, testLastName);
//
//        Map<String, Object> customerUriVariables = new LinkedHashMap<>();
//        customerUriVariables.put("firstName", testFirstName);
//        customerUriVariables.put("lastName", testLastName);
//        template.postForEntity(CUSTOMER_ENDPOINT, customer, Customer.class);
//        String retrievedCustomerAsJson  = template.getForObject(CUSTOMER_ENDPOINT + "/search/findByFirstNameAndLastName?firstName={firstName}&lastName={lastName}", String.class, customerUriVariables);
//        String customerHref = (String) new JSONObject(retrievedCustomerAsJson).getJSONObject("_links").getJSONObject("self").get("href");
//
//        // part 2 - create child (item -> no parent/customer)
//        String testItemName = "item " + UUID.randomUUID();
//        Item item = new Item(testItemName);
//
//        Map<String, Object> itemUriVariables = new LinkedHashMap<>();
//        itemUriVariables.put("name", testItemName);
//        template.postForEntity(ITEM_ENDPOINT, item, Item.class);
//        String retrievedItemAsJson1  = template.getForObject(ITEM_ENDPOINT + "/search/findByName?name={name}", String.class, itemUriVariables);
//        String itemHref = (String) new JSONObject(retrievedItemAsJson1).getJSONObject("_links").getJSONObject("self").get("href");
//
//        // part 3 - associate child with parent
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.add("Content-type", "text/uri-list");
//        HttpEntity<String> httpEntity = new HttpEntity<>(customerHref, requestHeaders);
//        ResponseEntity<String> retrievedItemAfterAssociationAsJson = template.exchange(itemHref + "/customer", HttpMethod.PUT, httpEntity, String.class);
//
//        // and validate whether our customer has the correct item and only ONE item
//        String retrievedCustomerWithItemsAsJson  = template.getForObject(customerHref + "/items", String.class);
//        JSONArray itemsOfCustomer = new JSONObject(retrievedCustomerWithItemsAsJson).getJSONObject("_embedded").getJSONArray("items");
//        String itemName = (String) itemsOfCustomer.getJSONObject(0).get("name");
//        assertEquals(testItemName, itemName);
//        assertThrows(JSONException.class, () -> {
//            itemsOfCustomer.getJSONObject(1);
//        });
//    }
//
//    private static Integer resolveId(String href) {
//        Pattern lastDigitPattern = Pattern.compile("([0-9]+)$");
//        Matcher matcher = lastDigitPattern.matcher(href);
//        if (matcher.find()) {
//            return Integer.parseInt(matcher.group(1));
//        }
//
//        return null;
//    }
//}
