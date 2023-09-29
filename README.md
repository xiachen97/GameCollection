#Version 1.0 
Create Login page and Login Credentials - By Xia Chen

In our project, I created three .java files namely loginInterface.java, SeondPage.java and UserCredentials.java. The loginInterface.java file is the login interface. You need to enter the user's email address and password in this interface, and then connect to sql to determine whether the email address and password are entered correctly. The SeondPage.java file successfully accesses the second page from the login page, which is a blank page, and we will add this function in subsequent versions. If we want to add more user sql attributes such as user phone or records, we may add some code UserCredentials.java file.



#Version 1.1 
Create Register page and renew Login page and Login Credentials - By Xia Chen

In this version, I change SeondPage.java to SuccessLoginPage.java; 
I also add more user attribute in UserCredentials.java(it's not necessary).
For new users， I add a new java file called RegisterPage.java. When this file is run, it will show a new dashboard to help new users register, their input information such as email, password, firstname, lastname, username, phone will be connected and uploaded to the sql database. Then log in with the newly created data Email and password on the login page.