# Étape 1 : Compilation (Build) avec Maven et JDK 25
FROM maven:3.9.6-eclipse-temurin-25-alpine AS build 
# Note : Maven 3.9.6 peut compiler du Java 25 si configuré
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Exécution (Runtime) avec l'image OpenJDK 25
FROM openjdk:25-slim-bookworm
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Sécurité : On définit le port dynamique de Render
EXPOSE 8080

# Commande de lancement
ENTRYPOINT ["java", "-Xmx400m", "-Dserver.port=${PORT}", "-jar", "app.jar"]
# Le -Xmx400m dit à Java : "N'utilise pas plus de 400 Mo de RAM", ce qui laisse un peu de place au système pour respirer.