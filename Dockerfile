# Используем базовый образ Java
FROM openjdk:17-jdk-slim

# Указываем рабочую директорию
WORKDIR /app

# Копируем jar-файл в контейнер
COPY target/*.jar app.jar

# Открываем порт (по умолчанию Spring Boot использует 8080)
EXPOSE 8080

# Команда запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
