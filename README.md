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
    
    {
    	"customerId": "1",
    	"customerKey": "name",
    	"customerValue": "John Doe"
    }
    
    
### Retrieve customer information

    $ GET http://localhost:8080/customer/{customerId}
    
   Sample Output
        
       
        [
            {
                "id": {
                    "customerId": "1",
                    "customerKey": "name"
                },
                "customerValue": "John Doe",
                "customerId": "1",
                "customerKey": "name"
            },
            {
                "id": {
                    "customerId": "1",
                    "customerKey": "location"
                },
                "customerValue": "Luna",
                "customerId": "1",
                "customerKey": "location"
            }
        ]
        
    
    
   Both `customerId` and `customerKey` are used as composite primary keys