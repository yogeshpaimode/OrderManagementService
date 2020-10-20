# Order Management Service
 
Secure Order Management Service : The core function of the module is to implement RESTful Java based backend application for a Order Management Service.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a system.

### Prerequisites

Things you need to get the project up and running on a local machine:

* Java 8
* Maven 3.3.x or later
* Docker latest version
* docker-compose latest version
* Postman [for testing Rest services]

### Building and Running the application

* Execute the docker-compose command to run the application: docker-compose up --build

###  Built With:
* Framework - SpringBoot
* Dependency Management - Maven
* Version Control - Git
* Java - 8
* Building images and running containers - Docker
* OAuth 2.0 - Authentication & Authorization 
* Swagger 2
* H2 in Memory Database

### Installing
Clone the git repository in a local directory using git clone<repository_name> command.

### How to Use
Swagger UI URL: http://localhost:8080/swagger-ui.html#/

As this application is secured with OAuth 2.0 mechanism, thus all Rest services while using from Postman application needs to be authenticated and authorized. Please find below detailed steps on how to access application services with authentication :

step 1 : Request Type: POST || URL : http://localhost:8080/oauth/token?grant_type=password&username=eriks&password=secret
		 
		 Please find more details below : 
		 Authorization (Below details for Authorization server login with Username and Password to get Token for accessing other resources / rest services):
		 	Type : Basic Auth
		 	UserName : eriks_client
		 	Password : password
		 In URL, we have provided below details which are belongs to User for authentication purpose :
		 	grant_type = password
		 	username = eriks
		 	password = secret
		 	
		 Response :
		 	{
			    "access_token": "bbe0b780-a121-4b06-abe2-b637d5ec20e0",
			    "token_type": "bearer",
			    "refresh_token": "0fd3294b-5a57-4049-9b9c-d7f760ab539a",
			    "expires_in": 43199,
			    "scope": "read write"
			}
		 
step 2 : Copy access_token received from step 1 and provide this token every time for accessing resources in URL.
		 
		 e.g. 
		 Return a list of Orders :: Request Type : GET || URL : http://localhost:8080/api/orderservice/getAllOrders?access_token=bbe0b780-a121-4b06-abe2-b637d5ec20e0
		 Return a Order Details :: Request Type : GET || URL : http://localhost:8080/api/orderservice/orders/4?access_token=bbe0b780-a121-4b06-abe2-b637d5ec20e0

#### REST services

* REST services can be accessed on http://localhost:8080/api/orderservice/...
	Details about all Rest services are available on Swagger UI.

### Stopping the application and cleaning-up
```
docker-compose down -v --rmi all --remove-orphans
```
