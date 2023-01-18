# Java 17
FROM openjdk:17-oracle

# Refer to Maven build -> finalName
ARG JAR_FILE=target/gestiBank.jar

# cd /app
WORKDIR /app

# cp target/amsDataMI.jar /app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]