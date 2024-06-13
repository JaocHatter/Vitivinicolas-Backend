FROM maven:3.8.4-openjdk-17-slim AS builder

# Copia los archivos del proyecto al contenedor
COPY . .
# Compila la aplicación usando Maven
RUN mvn clean package -DskipTests

FROM openjdk:17-slim

COPY --from=builder /target/demo-0.0.1-SNAPSHOT.jar demo.jar

EXPOSE 8083
CMD ["java", "-jar", "demo.jar"]