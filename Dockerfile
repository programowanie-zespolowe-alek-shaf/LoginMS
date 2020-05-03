FROM maven:3.6.3-openjdk-11
WORKDIR app
COPY l-application ./l-application

ENV MYSQL_DB_HOST name
ENV MYSQL_DB_PORT 3306
ENV MYSQL_DATABASE customer
ENV MYSQL_DB_USERNAME customer
ENV MYSQL_DB_PASSWORD customer
ENV EUREKA_HOST name
ENV EUREKA_PORT 8761

COPY pom.xml ./
RUN mvn clean install -DskipTests -P prod
RUN mv l-application/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","app.jar"]
