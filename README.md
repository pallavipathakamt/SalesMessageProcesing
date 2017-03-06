System Requirements:
- Java 8
- Maven

Assumtions:
- All messesges will be received in json String format
- For any new message received, MessageProcessingService.processMessage will be called

Sample Message notifications:

  {
  	"messageType" : "TYPE1",
    "productType":"apple",
    "value":10
  }
  
  {
    "messageType" : "TYPE2",
    "productType":"apple",
    "value": 10,
    "occurrences": 20
  }
  
  {
  	"messageType" : "TYPE3",
    "productType":"apple",
    "value": 10,
    "adjustment": 20 ,
    "operationType" : "ADD"
  }

Run Steps:
- Install all dependencies in pom.xml
- Run the server as a normal JAVA application
- Output is logged on the console
