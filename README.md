# Kotlin + Spring Boot + AWS Dynamodb


## Pull Dynamodb Docker Image
    
    $ docker pull amazon/dynamodb-local


## Run Dynamodb locally

    $ docker run -p 8081:8000 amazon/dynamodb-local

## Build Project

    $ mvn clean install -DskipTests

## Run Project

    $ java -jar target/kotldemo-0.0.1-SNAPSHOT.jar
  
## APIs
### Store customer Information

    $ POST http://localhost:8080/customer
    
   Sample Payload
    
    ```
    {
    	"customerCode": "1",
    	"customerKey": "name",
    	"customerValue": "John Doe"
    }
    
    ```
### Retrieve customer information

    $ GET http://localhost:8080/customer/{code}
    
   Sample Output
        
       
        [
            {
                "id": {
                    "customerCode": "1",
                    "customerKey": "name"
                },
                "customerValue": "John Doe",
                "customerCode": "1",
                "customerKey": "name"
            },
            {
                "id": {
                    "customerCode": "1",
                    "customerKey": "location"
                },
                "customerValue": "Luna",
                "customerCode": "1",
                "customerKey": "location"
            }
        ]
        
    
    
   Both `customerCode` and `customerKey` are used as composite primary keys