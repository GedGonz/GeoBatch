# Proyecto batch para el proceso masivo de apoyos

Este proyecto es una aplicación de gestión de apoyos geoespaciales utilizando Spring Batch. Permite almacenar y gestionar información sobre diferentes apoyos, incluyendo sus coordenadas geográficas.

## Tecnologías Implementadas

- **Java 17**: Lenguaje de programación principal.
- **Spring Boot 3.4.2**: Framework para el desarrollo de aplicaciones basadas en Java.
- **Spring Batch**: Framework para el procesamiento por lotes.
- **Spring Data JPA**: Para la persistencia de datos.
- **Hibernate Spatial**: Extensión de Hibernate para trabajar con datos geoespaciales.
- **PostgreSQL**: Base de datos relacional utilizada.
- **PostGIS**: Extensión de PostgreSQL para datos geoespaciales.
- **MapStruct**: Generador de mapeos de Java Bean.
- **Lombok**: Biblioteca para reducir el código boilerplate.
- **Gradle**: Herramienta de automatización de compilación.

## Requisitos Previos

- Java 17
- Gradle
- PostgreSQL
- PostGIS

## Configuración de la Base de Datos

1. Crear una base de datos en PostgreSQL.
2. Instalar la extensión PostGIS en la base de datos.
3. Configurar las credenciales de la base de datos en el archivo `application.properties` o `application.yml`.

## Levantar el Proyecto

1. Clonar el repositorio:
    ```sh
    git clone <URL_DEL_REPOSITORIO>
    cd <NOMBRE_DEL_PROYECTO>
    ```

2. Compilar el proyecto:
    ```sh
    ./gradlew build
    ```

3. Ejecutar la aplicación:
    ```sh
    ./gradlew bootRun
    ```