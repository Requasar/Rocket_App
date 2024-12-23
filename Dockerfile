# Java 17 tabanlı bir base image kullanıyoruz
FROM eclipse-temurin:17-jre

# Çalışma dizinini belirleyin
WORKDIR /app

# JAR dosyasını container içine kopyalayın
COPY target/Rocket-0.0.1-SNAPSHOT.jar /app/rocket.jar

# Spring Boot uygulamasını başlatın
CMD ["java", "-jar", "/app/rocket.jar"]
