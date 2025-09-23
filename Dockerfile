# Use a imagem oficial do OpenJDK 17
FROM eclipse-temurin:17-jdk-jammy

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR do build para dentro do container
COPY build/libs/HeroSheets-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que o Spring Boot irá rodar
EXPOSE 80

# Define variáveis de ambiente para o Spring (opcional se já estiver no application-prod.properties)
ENV SPRING_PROFILES_ACTIVE=prod

# Comando para rodar o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
