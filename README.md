# Read Me First
Developed RestAPI with following endpoints:
* http://localhost:8080/loan/create : This endpoint is used for loan creation as per LOAN command.
* http://localhost:8080/loan/payment: This endpoint used for loan repayment as per PAYMENT command.
* http://localhost:8080/loan/balance: This endpoint used to retrieve balance emi and amount paid as per BALANCE command.

#Component details:
   * Swagger:
     * Used swagger documentation for understanding the function of endpoints.
     * http://localhost:8080/swagger-ui.html
   * H2-Database:
     * Used h2 in-memory databased to store loan and payment data.
     * h2-console: http://localhost:8080/h2-console
# Getting Started
## Steps to run:
Application can be run in **two** ways as follows:
### One - Using APi-Client (Postman/Talend Api/Ready API) 
* Run Spring-boot application with main class.
    * Hit http://localhost:8080/loan/create with given payload _src/main/resources/loan.json_
    * Hit http://localhost:8080/loan/payment with given payload _src/main/resources/payment.json_
    * Hit http://localhost:8080/loan/balance with given payload _src/main/resources/balance.json_
* _Input for with different different combination can be provided using above payload files._

### Two - Using input.txt file 
* Run Spring-boot application with main class.
* Provide the input from text file given in resource folder _src/main/resources/input.txt_
* Hit http://localhost:8080/setup - It will load data from input.txt
* Hit http://localhost:8080/balance 
    * It will show the result in browser and in console as well.
 ### Three - Using CMD
 * Provide input.txt file through cmd.
 * it will read and produce the result 
