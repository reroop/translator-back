**_Translator app_**

Before first run, you need to get necessary dependencies by running build.gradle.

The application can be started by running TranslatorApplication.main(). Application will start on port :8080.
To successfully run tests, see the description in TranslatorApplicationTests.

H2 console can be accessed with http://localhost:8080/api/h2-console/ and using following login settings:

-driver class: org.h2.Driver

-JDBC URL: jdbc:h2:./h2/translator

-user name: sa

-password: sa