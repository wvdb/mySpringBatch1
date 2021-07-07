# mySpringBatch1

## Difference between Spring Data Rest entity and JPA entity

`{
     "customerId": 378,
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
             "href": "http://localhost:8188/customers/378"
         },
         "customer": {
             "href": "http://localhost:8188/customers/378"
         },
         "items": {
             "href": "http://localhost:8188/customers/378/items"
         }
     }
 }`

### Running application locally
* mvn spring-boot:run 
* mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8188
 

