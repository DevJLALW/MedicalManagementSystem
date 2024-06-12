Project Setup Instructions
1. Clone the GitHub Repository: https://github.com/DevJLALW/MedicalManagementSystem


2. Database Setup
Run the DDL and DML statements from the files provided in the GitHub repository to set up the database(MySQL):
https://github.com/DevJLALW/MedicalManagementSystem/blob/main/SQL/Final_DDL
https://github.com/DevJLALW/MedicalManagementSystem/blob/main/SQL/Final_DML


3. Run the Application in an IDE
Open the project in your preferred IDE (e.g., IntelliJ IDEA).

4. In Intellij, install lombok plugin with following steps:
File-> settings->plugin->search 'lombok' ->Install


5. Update application.properties file with username and password of SQL data source:
https://github.com/DevJLALW/MedicalManagementSystem/blob/main/MedicalManagementSystem/src/main/resources/application.properties

spring.datasource.username=MYSQL_USERNAME
spring.datasource.password=MYSQL_PASSWORD


6. Application Setup and Employee Registration
Open your browser and navigate to:

http://localhost:8080/index

Click on the "Register Employee" link.
Enter the required details in the registration form.
Click on "Create Employee."

Note down the EmployeeID displayed on the next page (registerEmployee). This EmployeeID will be used as your User ID for logging in.

7. Login to the Application
Click on the "Go to login page" link.

Use the EmployeeID as the User ID and the password you set during registration to log in.

8. Testing Application Functionalities
After logging in, you can test the rest of the functionalities provided by the application. Explore the different features and ensure everything works as expected.
