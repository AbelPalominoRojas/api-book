# API BOOK
API REST para la gestión de libros utilizando Spring Boot y SQL Server.

# Ejecución local

Crea el archivo `application-local.yml` en `src/main/resources/` antes de arrancar la aplicación. A continuación hay una plantilla mínima; reemplaza los valores entre `< >` por los tuyos.

Contenido mínimo de `src/main/resources/application-local.yml`:

```yaml
    # Variables
    database-server: <DB_HOST> # Dirección del servidor de la base de datos
    database-port: <DB_PORT>  # Puerto del servidor de la base de datos
    database-name: <DB_NAME> # Nombre de la base de datos
    database-username: <DB_USER> # Usuario de la base de datos
    database-password: <DB_PASSWORD> # Contraseña de la base de datos

    spring:
      datasource:
        url: jdbc:sqlserver://${database-server}:${database-port};databaseName=${database-name};encrypt=true;trustServerCertificate=true
        username: ${database-username}
        password: ${database-password}
        driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver
      jpa:
        hibernate:
          ddl-auto: none
        show-sql: true
        properties:
          hibernate:
            dialect: org.hibernate.dialect.SQLServerDialect
            format_sql: true

    app:
      profile-default: <NUM> # Número de perfil por defecto

    jwt:
      secret-key: <YOUR_JWT_SECRET> # Clave secreta para firmar los tokens JWT
      expiration-minutes: <EXPIRATION_MINUTES> # Tiempo de expiración del token en minutos
```

Notas rápidas:
- No dejes valores reales (contraseñas o claves) en repositorio público.
- Asegúrate de que la base de datos esté accesible desde `database-server` y que el puerto y credenciales sean correctos.

Cómo ejecutar localmente:
- Con Maven y perfil:
    - `mvn spring-boot:run -Dspring-boot.run.profiles=local`
- O exportando la variable de entorno:
    - `SPRING_PROFILES_ACTIVE=local mvn spring-boot:run`
