FROM openjdk:17-jdk

COPY target/RestaurantManagement-0.0.1-SNAPSHOT.jar RestaurantManagement-0.0.1-SNAPSHOT.jar

EXPOSE 3306

CMD ["java", "-jar", "RestaurantManagement-0.0.1-SNAPSHOT.jar"]

#FROM openjdk:17-jdk
#
## set the working directory
#WORKDIR /app
#
## copy the application jar file
#COPY target/RestaurantManagement-0.0.1-SNAPSHOT.jar RestaurantManagement-0.0.1-SNAPSHOT.jar
#
## set environment variables for MySQL connection
#ENV DB_URL jdbc:mysql://localhost:3306/restaurantmanagement
#ENV DB_USERNAME root
#ENV DB_PASSWORD 12345678
#
## expose port 8080
#EXPOSE 8080
#
## run the application
#ENTRYPOINT ["java","-Dspring.datasource.url=${DB_URL}","-Dspring.datasource.username=${DB_USERNAME}","-Dspring.datasource.password=${DB_PASSWORD}","-jar","RestaurantManagement-0.0.1-SNAPSHOT.jar"]
