## Используем базовый образ Java
#FROM openjdk:17-jdk-slim
#
## Указываем рабочую директорию
#WORKDIR /app
#
## Копируем jar-файл в контейнер
#COPY target/*.jar app.jar
#
## Открываем порт (по умолчанию Spring Boot использует 8080)
#EXPOSE 8080
#
## Команда запуска приложения
#ENTRYPOINT ["java", "-jar", "app.jar"]
#


# Стадия сборки
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Стадия запуска
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
