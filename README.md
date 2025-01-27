# API de Gestión de Películas

Este proyecto es una API REST para gestionar películas, incluyendo operaciones CRUD (Crear, Leer, Actualizar, Eliminar) y funcionalidades adicionales para obtener listas de películas basadas en su puntuación (media).

La API está construida con **Spring Boot** y **Reactor**, por lo que devuelve resultados reactivos usando `Flux` y `Mono`.

## Endpoints

### Obtener todas las películas
- **Método**: `GET`
- **URL**: `/peliculas/list`
- **Descripción**: Obtiene una lista de todas las películas.
- **Respuesta**:
  - `200 OK`: Devuelve un flujo de todas las películas en la base de datos.
GET http://localhost:8080/peliculas/list


Obtener películas con la mejor media (Top películas)
Método: GET
URL: /peliculas/toplist
Descripción: Obtiene una lista de películas ordenadas por la mejor puntuación.
Respuesta:
200 OK: Devuelve un flujo de las mejores películas según su puntuación.
GET http://localhost:8080/peliculas/toplist

Obtener una película por ID
Método: GET
URL: /peliculas/{id}
Descripción: Obtiene los detalles de una película específica por su id.
Respuesta:
200 OK: Devuelve los detalles de la película con el id especificado.
404 Not Found: Si no se encuentra una película con el id dado.
GET http://localhost:8080/peliculas/{id}

Obtener películas por media
Método: GET
URL: /peliculas/media
Parámetro: media (double)
Descripción: Obtiene una lista de películas cuya puntuación es mayor o igual a la media especificada.
Respuesta:
200 OK: Devuelve un flujo de películas con la media especificada o superior.
GET http://localhost:8080/peliculas/media?media=8.0


Crear una nueva película
Método: POST
URL: /peliculas/alta
Cuerpo de la solicitud:
{
   "nombre": "Cars",
   "duracion": 90,
   "pais": "Estados Unidos",
   "director": "John Lasseter"
}


Actualizar una película
Método: PUT
URL: /peliculas/actualizar/{id}
Descripción: Actualiza los detalles de una película existente.
Respuesta:
200 OK: Devuelve la película actualizada.
404 Not Found: Si no se encuentra una película con el id dado.
Cuerpo de la solicitud:
{
   "nombre": "Cars",
   "duracion": 120,
   "pais": "Estados Unidos",
   "director": "John Lasseter"
}

Eliminar una película
Método: DELETE
URL: /peliculas/eliminar/{id}
Descripción: Elimina una película de la base de datos por su id.
Respuesta:
200 OK: La película ha sido eliminada correctamente.
404 Not Found: Si no se encuentra una película con el id dado.
DELETE http://localhost:8080/peliculas/eliminar/{id}



