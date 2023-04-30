## Assumtion
1. Considering the application is a simple CRUD application, the data is stored in memory with H2 database.
2. Using YAGNI principle, the application will not use Service layer, but directly use the repository layer.
3. For same reason the application will not use DTO, but directly use the entity layer.
4. Considering there is no Business Logic, the testing will only cover integration test.
5. Using Spring Boot 3, JDK 17. 

## Build 
```
mvn clean install
```
## Start
```java
java -jar target/coding-task-1.0.jar
```
## Usage example
### Create a quotation
```bash
curl -X POST -H "Content-Type: application/json" -d '{
        "beginningOfInsurance": "2023-05-01",
        "insuredAmount": 1000000.00,
        "dateOfSigningMortgage": "2023-04-26",
        "customer": {
            "id" : 1,
            "firstName": "Matteo",
            "lastName": "Pelliccia",
            "middleName": "MP",
            "email": "test@example.com",
            "phoneNumber": "12345678",
            "birthDate": "1990-01-01"
        }}' http://localhost:8080/quotations
```

