# Store Management Application
  Store management is an API that can server a frontend or a mobile application to manage products in a store or deposit.

#Technologies:
  1. Springboot
  2. H2 + JPA
  3. Lombok
  4. Open API Swagger


#Installation
  1. Get project
  2. Clean - Install
  3. Run :)

#Swagger

  You can access the swagger here: localhost:8080/swagger-ui.html

#Postman Calls
  To call the endpoint you need to select Basic Auth from Authorization tab and the credentials are:
  
    username: user
    password: password
  To call changePrice endpoint the credentials will be:
  
    username: admin
    password: adminpass
    
  Yes very secure :), but for this POC I think it is ok :D.
  
  Example calls:
  
    1. Get All Products - http://localhost:8080/products/getAllProducts
    2. Get Product By ID - http://localhost:8080/products/getProduct/d290f1ee-6c54-4b01-90e6-d701748f0851
    3. Add Product -  http://localhost:8080/products/addProduct
       JSON:
         {
          "name": "Telefon",
          "price" : "100"
         }
    4. Change Price - http://localhost:8080/products/changePrice/d290f1ee-6c54-4b01-90e6-d701748f0851
       JSON:
         {
          "newPrice" : "120.00"
         }
         
  In all cases the response will be the details about that product, except getAllProducts that will return all products.
  

#Description

  The API Is using spring security to secure the endpoints and also to restrict changePrice to be called only by Admin role. In order to store the information I am using a local database H2 with a simple and single table named Product. To facilitate the interaction      betwen database and application I am using JPA.
  
  The application respects the model-view-controller architectural pattern using the Controller layer to capture the request, the Service layer to implement the logic and the Repository layer to implement the database interaction.
  I am using Lombok to eliminate the boilerplate code from DTOs. I have used Record data type for the Request DTOs because are simple and fast to use, and normal Class for Responses but with Lombok generation and Builder to facilitate faster implementation and construction of the response.
  
  For the exception handling I have constructed a GeneralExceptionHandler annotated with @ControllerAdvice to capture all Exception from the Controller and to deliver them in a best practice way using ProblemDetails object (https://datatracker.ietf.org/doc/html/rfc7807)
  I have created ProductServiceTest class to create the unit test for the service implementation. For now in this stage we do not need component or integration testing because the implementation is very simple. To be added in the future.
  

