# Educational Platform / Plataforma Educacional

## Used Technologies / Tecnologias Utilizadas

<p class="technologies">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=java,spring,hibernate,maven,postman,idea,mysql,git" />
  </a>
</p>

- Java, JDK 21 (Programming Language, Version)
  - JavaDoc (method's documentation)
- Spring Boot (Framework and Dependencies)
    - Validation
    - MySQL Driver
    - Spring DevTools
    - Spring Data JPA (Hibernate + JPA)
    - MySQL Driver
    - Spring Web
    - Springdoc (API endpoint documentation with Swagger)
    - MapStruct (for DTOs conversion)
    - Spring Security (with roles, permissions and users)
      - JWT implementation
- Maven (Dependencies)
- Postman (Test API)
- XAMPP (Development Environment, Apache, phpmyadmin)
- IntelliJ Idea (IDE)
- Git + GitHub

---

### [Go to documentation in English](#ENG)

<br>

### [Ir a documentación en Español](#ESP)

---

## ENG

---

This personal project is about a system for an educational platform that includes entities such as: STUDENTS who are enrolled in COURSES, and those courses have TEACHERS. The goal of the project is to apply Spring Boot knowledge from the [previous project](https://github.com/mateofiorotto/bazar_api), improve certain aspects, and introduce security through Spring Security and JWT.

### Models
- Student
- Teacher
- Course
- UserSec (Spring Security)
- Role (Spring Security)
- Permission (Spring Security)

### Mandatory Requirements
- CRUD operations for all entities
- Spring Security implementation
  - Security Filter Chain
  - Authentication Manager
  - Authentication Provider
  - Password Encoder
  - User Details Service
  - Endpoint Authorization
- JWT implementation
  - JwtUtils and JwtTokenValidator
- DTOs and Mappers (Convert models to DTOs and vice versa, hide user data)
- Validations (@Valid, annotations in models)
- Custom exceptions (ApiException, GlobalExceptionHandler), custom error messages
- Use of ResponseEntity<?> and proper use of HTTP methods in endpoints
- Javadoc and Swagger (with Springdoc)

### How to Use It

1. Clone the repository

  ```
  git clone https://github.com/mateofiorotto/educational_platform
  cd educational_platform
  ```

2. Check dependencies
- JDK 21
- Maven
- IDE of your choice (I used IntelliJ IDEA)

3. Database Configuration
- Open XAMPP and start Apache and MySQL
- Create a database with your preferred name; by default in application.properties, it's set to "educational_platform"

4. application.properties

In your IDE, configure the local environment variables and assign them the corresponding values:

```
# General configuration
spring.application.name=educational_platform

# Database configuration
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

# Spring Security configuration
spring.security.user.name=${SS_USER}
spring.security.user.password=${SS_PASSWORD}

# JWT configuration
security.jwt.private.key=${PRIVATE_KEY}
security.jwt.user.generator=${USER_GENERATOR}
```


- `${DB_URL}` = Database connection URL
- `${DB_USER}` = Database username
- `${DB_PASSWORD}` = Database password
- `${SS_USER}` = Spring Security username
- `${SS_PASSWORD}` = Spring Security password
- `${PRIVATE_KEY}` = Private key used to generate JWT tokens
- `${USER_GENERATOR}` = User in charge of generating the JWT token (e.g., MATEO-SEC)

**NOTE**: SS_USER and SS_PASSWORD are used for HTTP BASIC authentication in Spring Security. This app doesn't use them, but the configuration is left in the code in case you clone the repo and want to experiment with it.

**NOTE 2**: The private key must be hashed using the SHA256 algorithm. I used [this page](https://tools.keycdn.com/sha256-online-generator)

5. Run the App

Run `./mvnw spring-boot:run` or click the run button in your IDE while inside the "PlataformaEducativaApplication" class (main class annotated with @SpringBootApplication)

6. Test with Postman or any other tool

By default, Spring Boot runs on port 8080.

Try the endpoints here: `http://localhost:8080/swagger-ui/index.html#/`

## ESP

---

Este proyecto personal trata acerca de un sistema sobre una plataforma educativa en la que hay entidades como: ESTUDIANTES los cuales tienen CURSOS y esos cursos tienen PROFESORES. La idea del proyecto es implementar los conocimientos de Spring Boot del [proyecto anterior](https://github.com/mateofiorotto/bazar_api), mejorar algunas cosas e introducir seguridad a traves de Spring Security y JWT.

### Modelos
- Student (Estudiante)
- Teacher (Profesor)
- Course (Curso)
- UserSec (Spring Security)
- Role (Spring Security)
- Permission (Spring Security)

### Requerimientos Obligatorios
- CRUD para todas las entidades
- Implementacion de Spring Security
  - Security Filter Chain
  - Authentication Manager
  - Authentication Provider
  - Password Encoder
  - User Details Service
  - Autorizacion en Endpoints
- Implementacion de JWT
  - JwtUtils y JwtTokenValidator
- DTOs y Mappers (Convertir modelos a DTOs y viceversa, ocultar datos del usuario)
- Validaciones (@Valid, Anotaciones en modelos)
- Excepciones personalizadas (ApiException, GlobalExceptionHandler), mensajes de error personalizados
- Usar ResponseEntity<?> y correcto uso de metodos HTTP en los endpoints.
- Javadoc y Swagger (con Springdoc)

### Como usarlo
1. Clonar repositorio

  ```
  git clone https://github.com/mateofiorotto/educational_platform
  cd educational_platform
  ```

2. Revisar dependencias
- JDK 21
- Maven
- IDE de tu preferencia (yo usé IntelliJ IDEA)

3. Configuración de la base de datos
- Abre XAMPP e inicia Apache y MySQL
- Crea una base de datos con el nombre de tu preferencia, por defecto en application.properties es "educational_platform"

4. application.properties

Cambia en tu IDE las variables de entorno local y asignales el valor correspondiente

```
#Configuraciones varias
spring.application.name=educational_platform

#Configuraciones de BD
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

#Configuraciones de seguridad
spring.security.user.name=${SS_USER}
spring.security.user.password=${SS_PASSWORD}

#Config de JWT
security.jwt.private.key=${PRIVATE_KEY}
security.jwt.user.generator=${USER_GENERATOR}
```

- ${DB_URL} = Direccion a tu base de datos
- ${DB_USER} = Usuario de la base de datos
- ${DB_PASSWORD} = Contraseña de la base de datos
- ${SS_USER} = Usuario de SPRING SECURITY
- ${SS_PASSWORD} = Contraseña de SPRING SECURITY
- ${PRIVATE_KEY} = Clave privada para generar el token JWT
- ${USER_GENERATOR} = Usuario que se encarga de la generacion de token JWT. EJ: MATEO-SEC

**NOTA**: SS_USER y SS_PASSWORD son para usar con HTTP BASIC en Spring security. Esta app no la usa pero dejo estas opciones en el codigo por si clonas el repo y queres experimentar con ello.

**NOTA 2**: La clave privada debe ir hasheada en el algoritmo SHA256, yo utilize [esta página](https://tools.keycdn.com/sha256-online-generator)

5. Ejecutar la app

Ejecuta ```./mvnw spring-boot:run``` o presiona el boton de iniciar en tu IDE mientras estas dentro de la clase "PlataformaEducativaApplication". (Clase principal con la anotación @SpringBootApplication)

6. Probar con Postman u otro

Por defecto en Spring Boot, el localhost esta en el puerto 8080.

Proba los endpoints acá: ```http://localhost:8080/swagger-ui/index.html#/```