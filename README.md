#Java Banking Application

##Instructions to run the app
1. Import in intellij as maven project.
2. Run BankingAppApplication.java as java app.
3. Go to http://localhost:8080/swagger-ui.html#/ to use swaqgger api editor tool or use postman collection attached. (SwirePay.postman_collection)



###Description
Create a Spring Boot Rest backend for a neo banking application. The use cases are:

A user should be able to create an account with the bank with his/her basic information. When a user creates an account, a username and password is returned to the user with his/her account id.
The user can use basic authentication and call the following with REST apis. Please add appropriate validations wherever applicable. (For example when you withdraw money, balance can’t go below zero)
Add money to his/her account
Withdraw money from his/her account
View his/her balance
Add another account as a contact
Transfer money to a contact.

Please implement the above as a Spring Boot application with H2 or MySQL database.

Basic Authentication format:

https://www.loginradius.com/blog/async/everything-you-want-to-know-about-authorization-headers/


We expecting at least four endpoints:
POST Endpoint - Account
Creates an Account in the database with minimum schema of:
username
password
first_name
last_name
created_at
updated_at
GET Endpoint - Balance
A balance is automatically created for every account. Balance’s database schema should at least contain:
account_id
balance_amount
created_at
Updated_at
This endpoint should be authenticated using basic authentication of username and password in the header based on the format presented above.
POST Endpoint - Charge
Add to balance an amount. Should accept the charge amount in the request body.
This endpoint should be authenticated using basic authentication of username and password in the header based on the format presented above.
POST Endpoint - Refund
Remove from balance an amount. Should accept the refund amount in the request body.
This endpoint should be authenticated using basic authentication of username and password in the header based on the format presented above.

Please ensure URLs follow REST API format. https://stackoverflow.blog/2020/03/02/best-practices-for-rest-api-design/

Please leverage Hibernate and JPA. Some links are below to help you guide.
JPA: https://www.baeldung.com/the-persistence-layer-with-spring-and-jpa
Hibernate: https://www.baeldung.com/spring-boot-hibernate

Code Submission
Your submission should involve the following besides the application.
Instructions to run the application in the README file
Code should be documented with javadoc and comments wherever applicable.
A Postman collection which showcases the different endpoints in the application

Please push your Sprint Boot code to a github repository and share the github link to careers@swirepay.com along with subject Sprint Boot - Java Banking Application.
