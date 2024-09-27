# Use uma imagem base do OpenJDK
FROM openjdk:11-jdk-slim

# Diretório de trabalho no container
WORKDIR /backend

# Copie o arquivo JAR do projeto para o diretório do container
# O comando usa um wildcard para encontrar qualquer JAR na pasta target
COPY target/*.jar backend.jar

# Expor a porta que o Spring Boot vai rodar
EXPOSE 8080

# Comando para executar o JAR
ENTRYPOINT ["java", "-jar", "/backend/backend.jar"]
