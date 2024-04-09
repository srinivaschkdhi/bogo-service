#### Clone the repository

git clone https://github.com/yourusername/bogo-spring-boot.git


#### Navigate to the project directory
cd bogo-spring-boot

#### Build the project
mvn clean install

#### Run the application
mvn spring-boot:run

#### Using the API

To use the API, send a GET request to the /calculate endpoint with the product prices and the rule number:

curl "http://localhost:8080/api/bogo/calculate?prices=10,20,30,40,50,60&rule=1"

#### Response:

{
  "discountedItems": [50, 30, 10],
  "payableItems": [60, 40, 20]
}

