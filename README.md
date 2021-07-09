# mySpringBatch1

## Difference between Spring Data Rest entity and JPA entity

```
http://localhost:8188/customers/744

{
     "customerId": 744,
     "firstName": "Donald",
     "lastName": "Customer 1",
     "customerCreationDate": "2021-07-07T11:45:14.374+02:00",
     "purchases": [
         {
             "name": "purchase3"
         },
         {
             "name": "purchase2"
         },
         {
             "name": "purchase1"
         }
     ],
     "_links": {
         "self": {
             "href": "http://localhost:8188/customers/744"
         },
         "customer": {
             "href": "http://localhost:8188/customers/744"
         },
         "items": {
             "href": "http://localhost:8188/customers/744/items"
         }
     }
}
```

```
http://localhost:8188/customers/744/items

{
    "_embedded": {
        "items": [
            {
                "itemId": 38,
                "name": "item e4ae1f26-b278-42d8-bc3c-2f3329c07ba8",
                "_links": {
                    "self": {
                        "href": "http://localhost:8188/items/38"
                    },
                    "item": {
                        "href": "http://localhost:8188/items/38"
                    },
                    "customer": {
                        "href": "http://localhost:8188/items/38/customer"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8188/customers/744/items"
        }
    }
}

```

| | C | R | U entity | U association | D | 
| --- | --- | --- | --- | --- | --- |
| Customer | &#9728; (but no items) | &#9728; | &#9728; | ----- | &#9728; (orphanRemoval = true) | 
| Item | &#9728; (use "Content-Type:text/uri-list") | &#9728; | &#9728; | &#9729; | &#9728; | 
 
### Running application locally
* mvn spring-boot:run 
* mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8188

### Websites of interest
* https://www.baeldung.com/spring-data-rest-relationships
* https://www.markdownguide.org/extended-syntax/
* https://howtodoinjava.com/junit5/
 

