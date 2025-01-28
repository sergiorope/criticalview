PeliculaController - API REST para la gestión de películas
Este controlador proporciona una API para gestionar las películas en la aplicación. Utiliza Spring WebFlux para la programación reactiva, permitiendo una comunicación eficiente y no bloqueante.

Endpoints
1. Obtener lista de todas las películas
GET /peliculas/list
Devuelve una lista de todas las películas disponibles.

2. Obtener las mejores películas (Top List)
GET /peliculas/toplist
Devuelve las películas con las mejores puntuaciones o popularidad.

3. Obtener una película por ID
GET /peliculas/{id}
Obtiene una película mediante su identificador único.
Respuesta:
200 OK si se encuentra la película.
404 Not Found si no se encuentra.

4. Obtener películas por media de puntuación
GET /peliculas/media?media={media}
Filtra las películas con una media de puntuación mayor o igual a la proporcionada en el parámetro media.

5. Alta de nueva película
POST /peliculas/alta
Cuerpo:
JSON con los detalles de la nueva película.
Respuesta:
200 OK si la película es creada correctamente.

6. Actualizar película existente
PUT /peliculas/actualizar/{id}
Cuerpo:
JSON con los nuevos datos de la película.
Respuesta:
200 OK si la película es actualizada.
404 Not Found si no se encuentra la película.

7. Eliminar película
DELETE /peliculas/eliminar/{id}
Elimina una película mediante su identificador único.
Respuesta:
200 OK si la película es eliminada.
404 Not Found si no se encuentra la película.

Notas
Todos los endpoints devuelven respuestas utilizando Mono o Flux de la librería Reactor, proporcionando un enfoque no bloqueante y reactivo para manejar solicitudes y respuestas.

Requisitos
Spring Boot 2.5 o superior.
Reactor Core para el soporte reactivo.



ValoracionController - API REST para la gestión de valoraciones
Este controlador proporciona una API para gestionar las valoraciones de películas. Utiliza Spring WebFlux para la programación reactiva, lo que permite una comunicación eficiente y no bloqueante.

Endpoints
1. Obtener lista de todas las valoraciones
GET /valoraciones/list
Devuelve una lista de todas las valoraciones de películas disponibles.

2. Obtener una valoración por ID
GET /valoraciones/{id}
Obtiene una valoración mediante su identificador único.
Respuesta:
200 OK si se encuentra la valoración.
404 Not Found si no se encuentra.

3. Obtener valoraciones de un usuario específico
GET /valoraciones/usuario/{id}
Devuelve todas las valoraciones realizadas por un usuario con el ID proporcionado.
Respuesta:
200 OK si se encuentran valoraciones.
404 Not Found si no se encuentra ninguna.

4. Obtener valoraciones de una película específica
GET /valoraciones/pelicula/{id}
Devuelve todas las valoraciones asociadas a una película con el ID proporcionado.
Respuesta:
200 OK si se encuentran valoraciones.
404 Not Found si no se encuentra ninguna.

5. Alta de nueva valoración
POST /valoraciones/alta
Cuerpo:
JSON con los detalles de la nueva valoración.
Respuesta:
200 OK si la valoración es creada correctamente.

6. Actualizar valoración existente
PUT /valoraciones/actualizar/{id}
Cuerpo:
JSON con los nuevos datos de la valoración.
Respuesta:
200 OK si la valoración es actualizada.
404 Not Found si no se encuentra la valoración.

7. Eliminar valoración
DELETE /valoraciones/eliminar/{id}
Elimina una valoración mediante su identificador único.
Respuesta:
200 OK si la valoración es eliminada.
404 Not Found si no se encuentra la valoración.

8. Obtener usuario asociado a una valoración
GET /valoraciones/{valoracionId}/usuario
Obtiene el usuario que realizó una valoración con el ID proporcionado.
Respuesta:
200 OK si se encuentra el usuario.
404 Not Found si no se encuentra.

9. Obtener película asociada a una valoración
GET /valoraciones/{valoracionId}/pelicula
Obtiene la película asociada a una valoración con el ID proporcionado.
Respuesta:
200 OK si se encuentra la película.
404 Not Found si no se encuentra.

10. Obtener el número de valoraciones de una película
GET /valoraciones/count/{peliculaId}
Obtiene el número total de valoraciones para una película con el ID proporcionado.
Respuesta:
200 OK si se encuentra el número de valoraciones.
404 Not Found si no se encuentra ninguna.

Notas
Todos los endpoints devuelven respuestas utilizando Mono o Flux de la librería Reactor, proporcionando un enfoque no bloqueante y reactivo para manejar solicitudes y respuestas.

Requisitos
Spring Boot 2.5 o superior.
Reactor Core para el soporte reactivo.



UsuarioController - API REST para la gestión de usuarios
Este controlador proporciona una API para gestionar los usuarios en la aplicación. Utiliza Spring WebFlux para la programación reactiva, permitiendo una comunicación eficiente y no bloqueante.

Endpoints
1. Obtener lista de todos los usuarios
GET /usuarios/list
Devuelve una lista de todos los usuarios disponibles.

2. Obtener un usuario por ID
GET /usuarios/{id}
Obtiene un usuario mediante su identificador único.
Respuesta:
200 OK si se encuentra el usuario.
404 Not Found si no se encuentra.

3. Alta de nuevo usuario
POST /usuarios/alta
Cuerpo:
JSON con los detalles del nuevo usuario.
Respuesta:
200 OK si el usuario es creado correctamente.

4. Actualizar usuario existente
PUT /usuarios/actualizar/{id}
Cuerpo:
JSON con los nuevos datos del usuario.
Respuesta:
200 OK si el usuario es actualizado.
404 Not Found si no se encuentra el usuario.

5. Eliminar usuario
DELETE /usuarios/eliminar/{id}
Elimina un usuario mediante su identificador único.
Respuesta:
200 OK si el usuario es eliminado.
404 Not Found si no se encuentra el usuario.

Notas
Todos los endpoints devuelven respuestas utilizando Mono o Flux de la librería Reactor, proporcionando un enfoque no bloqueante y reactivo para manejar solicitudes y respuestas.

Requisitos
Spring Boot 2.5 o superior.
Reactor Core para el soporte reactivo.
