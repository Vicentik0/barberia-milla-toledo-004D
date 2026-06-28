# Barbería Milla Toledo

## Integrantes

* Vicente Milla
* Vicente Toledo

## Bibliotecas utilizadas

El proyecto fue desarrollado utilizando las siguientes bibliotecas y dependencias:

* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* Spring Cloud Gateway
* Spring WebFlux
* Spring Validation
* Springdoc OpenAPI / Swagger UI
* JJWT para autenticación mediante token JWT
* MySQL Connector
* Lombok
* JUnit 5
* Mockito

## Herramientas de instalación

Para ejecutar el proyecto se requiere instalar:

* JDK 17 o superior.
* Maven.
* MySQL Server.
* IntelliJ IDEA, Visual Studio Code u otro IDE compatible con Spring Boot.
* Postman para probar las rutas REST.
* Git para clonar y administrar el repositorio.

También se deben ejecutar los archivos SQL incluidos en el proyecto:

```text
db_barberos.sql
db_citas.sql
db_clientes.sql
db_inventario.sql
db_pagos.sql
db_seguridad.sql
db_servicios.sql
db_sucursales.sql
```

## Microservicios y puertos

| Microservicio | Puerto |
| ------------- | -----: |
| service-auth  |   8090 |
| inventario    |   9081 |
| clientes      |   9082 |
| barbero       |   8083 |
| sucursal      |   8084 |
| cita          |   8085 |
| servicios     |   8094 |
| pago          |   8095 |
| horario       |   8096 |
| promocion     |   8097 |
| resena        |   8098 |
| gateaway      |   8099 |

## Ejemplos de rutas para ejecución de API REST

### Autenticación

```http
POST http://localhost:8090/auth/registrar
POST http://localhost:8090/auth/login
```

### Barberos

```http
GET http://localhost:8083/barberos
GET http://localhost:8083/barberos/{id}
POST http://localhost:8083/barberos
PUT http://localhost:8083/barberos/{id}
DELETE http://localhost:8083/barberos/{id}
```

### Citas

```http
GET http://localhost:8085/citas
GET http://localhost:8085/citas/{id}
POST http://localhost:8085/citas
PUT http://localhost:8085/citas/{id}
DELETE http://localhost:8085/citas/{id}
```

### Clientes

```http
GET http://localhost:9082/cliente
GET http://localhost:9082/cliente/{id}
POST http://localhost:9082/cliente
PUT http://localhost:9082/cliente/{id}
DELETE http://localhost:9082/cliente/{id}
```

### Inventario

```http
GET http://localhost:9081/productos
GET http://localhost:9081/productos/{id}
POST http://localhost:9081/productos
PUT http://localhost:9081/productos/{id}
DELETE http://localhost:9081/productos/{id}
```

### Servicios

```http
GET http://localhost:8094/servicios
GET http://localhost:8094/servicios/{id}
POST http://localhost:8094/servicios
PUT http://localhost:8094/servicios/{id}
DELETE http://localhost:8094/servicios/{id}
```

### Sucursales

```http
GET http://localhost:8084/sucursales
GET http://localhost:8084/sucursales/{id}
POST http://localhost:8084/sucursales
PUT http://localhost:8084/sucursales/{id}
DELETE http://localhost:8084/sucursales/{id}
```

### API Gateway

```http
http://localhost:8099
```

## Ejemplos de rutas para ejecución de Swagger

```text
http://localhost:8090/swagger-ui/index.html
http://localhost:9081/swagger-ui/index.html
http://localhost:9082/swagger-ui/index.html
http://localhost:8083/swagger-ui/index.html
http://localhost:8084/swagger-ui/index.html
http://localhost:8085/swagger-ui/index.html
http://localhost:8094/swagger-ui/index.html
http://localhost:8095/swagger-ui/index.html
http://localhost:8096/swagger-ui/index.html
http://localhost:8097/swagger-ui/index.html
http://localhost:8098/swagger-ui/index.html
```
