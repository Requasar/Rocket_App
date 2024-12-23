Dockerfile needed: 

/*
 # Java 17 tabanlı bir base image kullanıyoruz
FROM eclipse-temurin:17-jre

# Çalışma dizinini belirleyin
WORKDIR /app

# JAR dosyasını container içine kopyalayın
COPY target/Rocket-0.0.1-SNAPSHOT.jar /app/rocket.jar

# Spring Boot uygulamasını başlatın
CMD ["java", "-jar", "rocket.jar"] 
*/ 
code of docker file and our image name is rocket-backend in docker we have rocket.jar name for spring boot

to build docker // docker build -t rocket-backend .
 
 to run and create container // docker run -d --name rocket-backend -p 8080:8080 rocket-backend
 upper code's output gives the id of the container
 

to delete container // docker rm rocket-backend

docker'ı çalıştırdığım kod:
docker run -p 8090:8080 --name rocketcontainer --net networkmysql -e MYSQL_HOST=mysqlcontainer -e MYSQL_PORT=3306 -e MYSQL_DB_NAME=rocket -e MYSQL_USER=root -e MYSQL_PASSWORD=root rocketimage
