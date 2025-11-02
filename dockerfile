# Imagen base con Java 17
FROM eclipse-temurin:17-jdk-alpine

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el jar generado
COPY target/app.jar /app.jar

# Copiar el script de espera
COPY wait-for-it.sh /wait-for-it.sh

# Dar permisos de ejecución al script
RUN chmod +x /wait-for-it.sh

# Comando que espera a que SQL Server esté listo antes de arrancar la app
CMD ["/wait-for-it.sh", "sqlserver", "1433", "--", "java", "-jar", "/app.jar"]
