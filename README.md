# challenge

Requirements: Java 11, PostgreSQL

* In order to run this tool you should create a PostgreSQL database named "challenge". Check application.properties to make sure your database configurations are in match (username, address). No password was set, so make sure your postgresql server is set to trust to accept connections with no password, or simply set a password for the DB and remember to add the password param under application.properties, like "spring.datasource.password=<password>".
* If all your database configurations are ok, just run "./gradlew clean && ./gradlew build" in the root directory to build the jar file. 
* After the build process has ended, look for the jar file under "/build/libs". Inside "build/libs" run "java -jar challenge-0.0.1-SNAPSHOT.jar"
* You can access the endpoints of the application under "localhost:8080"
