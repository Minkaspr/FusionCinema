
<details>
<summary>  🛠️ CONFIGURACIÓN DEL PROYECTO </summary>

### Entorno de desarrollo
- IDE: IntelliJ IDEA
- Tipo: Web Application / Spring Boot
- Servidor: Apache Tomcat Versión: 10
- Versión de Spring Boot: 3.2.2
- Java: 17 | Empaquetado: Jar
- Gestor de base de datos: XAMPP
- Tipo de Base de Datos: MySQL

### Dependencias
- Spring Web
- Spring Data JPA
- Lombok
- MySQL Driver
- Validation
</details>

## Modelo de Entidad: Movie

La entidad `Movie` representa una película en la base de datos. Cada instancia de `Movie` contiene los siguientes campos:

| Name          | Type      | Description                                                 | Validación                                                      |
|---------------|-----------|-------------------------------------------------------------|-----------------------------------------------------------------| 
| `id`          | Long      | Identificador único de la película.                         | Autogenerado                                                    | 
| `title`       | String    | El título de la película.                                   | No puede estar vacío, longitud entre 4 y 255 caracteres, único. | 
| `genre`       | String    | El género de la película.                                   | No puede estar vacío                                            | 
| `releaseYear` | LocalDate | El año de lanzamiento de la película.                       | No puede estar vacío, debe ser una fecha pasada                 | 
| `rating`      | Double    | La calificación de la película en una escala de `0 a 5`.    | No puede estar vacío, valor mínimo 0, valor máximo 5            | 
| `synopsis`    | String    | Una breve descripción o resumen de la trama de la película. | No puede estar vacío                                            | 
| `director`    | String    | El director de la película.                                 | No puede estar vacío                                            | 
| `minimumAge`  | Integer   | La edad mínima recomendada para ver la película.            | No puede estar vacío, valor mínimo 0                            |

# Endpoints

## Obtener todas las películas

```http
GET /api/FusionCinema/getAll
```
### Respuesta de éxito
Código: `200 OK`
Contenido: 
```json
{ 
  "status": 200, 
  "message": "Operación exitosa", 
  "data": [ 
    // ... 
  ], 
  "errors": null
}
```
### Respuesta de error
- Cuando ocurre un error interno:
  - Código: `500 INTERNAL SERVER ERROR`
  - Contenido: `{ "status": 500, "message": "Error al obtener las películas", "data": null, "error": "Mensaje de error" }`

## Obtener todas las películas con paginación

```http
GET /api/FusionCinema/getAllMovies
```
### Parámetros de URL
- `page`: Número de la página que se desea obtener.
- `size`: Cantidad de películas por página.

### Respuesta de éxito
Código: `200 OK`
Contenido:
```json
{ 
  "status": 200, 
  "message": "Operación exitosa", 
  "data": { 
    "content": [ 
      ... 
    ], 
    "totalElements": ...,
    "totalPages": ...,
    "last": ..., 
    "size": ..., 
    "number": ..., 
    "sort": ...,
    "numberOfElements": ...,
    "first": ..., 
    "empty": ...
  }, 
  "errors": null
}
```

### Respuesta de error
- Cuando ocurre un error interno:
  - Código: `500 INTERNAL SERVER ERROR`
  - Contenido: `{ "status": 500, "message": "Error al obtener las películas", "data": null, "error": "Mensaje de error" }`


## Obtener una película

```http
GET /api/FusionCinema/getMovie/{id}
```
### Parámetros de URL
- `id`: ID de la película que se desea obtener.

### Respuesta de éxito
Código: `200 OK`
Contenido: 
```json
{ 
  "status": 200, 
  "message": "Operación exitosa", 
  "data": { 
    //... 
  }, 
  "errors": null
}
```

### Respuesta de error
- Cuando la película no se encuentra:
    - Código: `404 NOT FOUND`
    - Contenido: `{ "status": 404, "message": "Mensaje de error", "data": null, "error": null }`

- Cuando ocurre un error interno:
    - Código: `500 INTERNAL SERVER ERROR`
    - Contenido: `{ "status": 500, "message": "Error al obtener la película", "data": null, "error": "Mensaje de error" }`


## Guardar una nueva película

```http
POST /api/FusionCinema/saveMovie
```
### Parámetros del cuerpo
- `movie`: Objeto de la película que se desea crear. Debe ser válido y estar en el formato correcto.

### Respuesta de éxito
Código: `201 CREATED`
Contenido: `{ "status": 201, "message": "Pelicula creada con éxito", "data": { ... }, "error": null }`
```json
{ 
  "status": 201, 
  "message": "Pelicula creada con éxito", 
  "data": { 
    ... 
  }, 
  "errors": null
}
```

### Respuesta de error
- Cuando el título de la película ya existe:
  - Código: `400 BAD REQUEST`
  - Contenido: `{ "status": 400, "message": "El título '...' ya existe. Por favor, intenta con un valor diferente.", "data": null, "error": "Mensaje de error" }`
- Cuando ocurre un error interno:
  - Código: `500 INTERNAL SERVER ERROR`
  - Contenido: `{ "status": 500, "message": "Error al crear la película", "data": null, "error": "Mensaje de error" }`


## Actualizar una película existente

```http
PUT /api/FusionCinema/updateMovie/{id}
```
### Parámetros de URL
- `id`: ID de la película que se desea actualizar.

### Parámetros del cuerpo
- `movie`: Objeto de la película con los nuevos detalles. Debe ser válido y estar en el formato correcto.

### Respuesta de éxito
Código: `200 OK`
Contenido: 
```json
{ 
  "status": 200, 
  "message": "Pelicula actualizada con éxito", 
  "data": { 
    ... 
  }, 
  "errors": null
}
```

### Respuesta de error
- Cuando la película no se encuentra:
  - Código: `404 NOT FOUND`
  - Contenido: `{ "status": 404, "message": "Mensaje de error", "data": null, "error": null }`
- Cuando ocurre un error interno:
  - Código: `500 INTERNAL SERVER ERROR`
  - Contenido: `{ "status": 500, "message": "Error al actualizar la película", "data": null, "error": "Mensaje de error" }`

## Eliminar una película existente

```http
DELETE /api/FusionCinema/deleteMovie/{id}
```
### Parámetros de URL
- `id`: ID de la película que se desea eliminar.

### Respuesta de éxito
Código: `200 OK`
Contenido:
```json
{ 
  "status": 200, 
  "message": "Pelicula eliminada con éxito", 
  "data": null, 
  "errors": null
}
```

### Respuesta de error
- Cuando la película no se encuentra:
  - Código: `404 NOT FOUND`
  - Contenido: `{ "status": 404, "message": "Mensaje de error", "data": null, "error": null }`
- Cuando ocurre un error interno:
  - Código: `500 INTERNAL SERVER ERROR`
  - Contenido: `{ "status": 500, "message": "Error al eliminar la película", "data": null, "error": "Mensaje de error" }`

## Obtener una película por título

```http
GET /api/FusionCinema/getMovieByTitle/{title}
```
### Parámetros de URL
- `title`: Título de la película que se desea obtener.

### Respuesta de éxito
Código: `200 OK`
Contenido: `{ ... }` (Objeto de la película) o `{}` (Objeto vacío si no se encuentra la película)

> Consulta hecha con `JPQL`

## Obtener una película por título

```http
GET /api/FusionCinema/getByTitle/{title}
```
### Parámetros de URL
- `title`: Título de la película que se desea obtener.

### Respuesta de éxito
Código: `200 OK`
Contenido: `{ ... }` (Objeto de la película)

### Respuesta de error
- Cuando la película no se encuentra:
  - Código: `404 NOT FOUND`

> Consulta hecha con `Consulta con Inversión de Control`

## Obtener una película por título (ignorando mayúsculas y minúsculas)

```http
GET /api/FusionCinema/getByTitleIc/{title}
```
### Parámetros de URL
- `title`: Título de la película que se desea obtener.

### Respuesta de éxito
Código: `200 OK`
Contenido: `{ ... }` (Objeto de la película) o `{}` (Objeto vacío si no se encuentra la película)

## Ten en cuenta lo siguiente
>[!WARNING]
>Asegúrate de manejar correctamente los errores 404 y 500 en tu cliente. No hacerlo puede resultar en una mala experiencia para el usuario final.

>[!NOTE]
>El objeto de datos en la respuesta de éxito contendrá los detalles de la película. Esto puede incluir información como el título, el director, el año de lanzamiento, etc.

>[!TIP]
>Considera usar los parámetros opcionales en tus endpoints para permitir a los usuarios personalizar sus consultas, como filtrar películas por género o año de lanzamiento.

>[!IMPORTANT]
>Asegúrate de validar todos los datos de entrada en tus endpoints para evitar problemas de seguridad y garantizar la integridad de los datos.

>[!CAUTION]
>Al eliminar películas con el endpoint DELETE /api/FusionCinema/deleteMovie/{id}, ten en cuenta que esta acción es irreversible.

> Optional: Al actualizar una película con el endpoint PUT /api/FusionCinema/updateMovie/{id}, no todos los campos son obligatorios. Puedes enviar solo los campos que deseas actualizar.
