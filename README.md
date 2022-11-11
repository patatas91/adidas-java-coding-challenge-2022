
# What is this?

This project is implemented with    
- Maven 3.8.4    
- JDK11    
- Docker desktop    
- Kafka    
- Swagger    

The objetive of the project is to create a subscription service that allows users to subscribe to a shopping and provide a winner.         

We have 4 microservices:
- **AdiClub-Service:** This service return the information related to the member.
- **PrioritySale-Service:** This service request the user information to the AdiClub-Service, and have a kafka producer to send the information to the queue
- **Email-service:** This service have the kafka listeners to get all messages with the subscriptors information. In order to get the winner it provides an endpoint to get the winner member acording with the winner requirements
- **Public-service:** This service makes the subscription

# Steps:
1) Call to public service with an email to make a subscription:
![image](https://user-images.githubusercontent.com/16426967/201339047-b4902d25-7248-4dad-8b7d-637c9117bc0a.png)

2) A request to PrioritySale service is received and makes a call to AdiClub service to get user data
![image](https://user-images.githubusercontent.com/16426967/201339278-ee1b36cd-0922-4150-a6e3-1583aad894b2.png)

3) The request to AdiClub service is getted and it returns the user data:
![image](https://user-images.githubusercontent.com/16426967/201339447-89dd2f35-5ef9-46ac-b504-e10d2c727755.png)

4) Send the user data to the kafka queue, if is member to the member topic and if not to the non member topic:
![image](https://user-images.githubusercontent.com/16426967/201340045-9c7a1114-d9bf-438c-97f7-13374438afd5.png)

5) Email service with the listeners get this messages and stores it
![image](https://user-images.githubusercontent.com/16426967/201340291-96d16780-acf2-4f29-8811-e3e058340a85.png)

6) when we request the winner with the email service winner endpoint we obtain the lucky user:
![image](https://user-images.githubusercontent.com/16426967/201340474-63700fba-3c2f-4e97-822c-ff92b7263ae1.png)
![image](https://user-images.githubusercontent.com/16426967/201340562-0b92bb67-95be-4e97-9bbd-d437639f5d13.png)

# My decisions:

- In order to simulate non members, when we request to this service the user, if the points are an odd number we will return that is not a member (empty point and registration date). 
- When a user subscribe to the service, we recover the information about this user and sended it to kafka queue (I decide to create 2 topics, members and non members) and on Email-service we have 2 listeners, one for each topic.
- In order to store all subscriptor I created 2 list, but if we have to store the subscriptors, we have to do in a database for example.

# Remaining parts:
Due lack of time, these parts are not implemented:
- **Security** for non-public services
- Unit **testing** for meaningful code (business logic / services).​

