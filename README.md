🎬 Peliculas API - Sistema Reactivo de Gestión de Películas, Valoraciones y Usuarios
Esta API REST permite gestionar películas, valoraciones y usuarios utilizando Spring WebFlux para un enfoque reactivo y no bloqueante. Ideal para aplicaciones modernas con alto rendimiento y concurrencia.

🎞️ PeliculaController - Gestión de Películas
Controlador para manejar operaciones relacionadas con películas usando programación reactiva con WebFlux.

Endpoints
📄 Obtener lista de todas las películas
GET /peliculas/list
Devuelve una lista de todas las películas disponibles.

⭐ Obtener las mejores películas (Top List)
GET /peliculas/toplist
Devuelve las películas con mejores puntuaciones o mayor popularidad.

🔍 Obtener película por ID
GET /peliculas/{id}
Busca una película por su identificador.
Respuestas:

200 OK: Película encontrada

404 Not Found: Película no encontrada

🎯 Obtener películas por media de puntuación
GET /peliculas/media?media={media}
Filtra películas con media de puntuación mayor o igual al valor especificado.

➕ Alta de nueva película
POST /peliculas/alta
Crea una nueva película.
Cuerpo: JSON con los detalles de la película
Respuesta:

200 OK: Película creada exitosamente

♻️ Actualizar película existente
PUT /peliculas/actualizar/{id}
Actualiza una película existente.
Cuerpo: JSON con los nuevos datos
Respuestas:

200 OK: Película actualizada

404 Not Found: Película no encontrada

❌ Eliminar película
DELETE /peliculas/eliminar/{id}
Elimina una película por su ID.
Respuestas:

200 OK: Película eliminada

404 Not Found: Película no encontrada

📝 ValoracionController - Gestión de Valoraciones
Controlador para manejar valoraciones de películas por parte de usuarios.

Endpoints
📄 Obtener todas las valoraciones
GET /valoraciones/list
Devuelve una lista de todas las valoraciones registradas.

🔍 Obtener valoración por ID
GET /valoraciones/{id}
Busca una valoración por su identificador.
Respuestas:

200 OK: Valoración encontrada

404 Not Found: No se encuentra la valoración

👤 Obtener valoraciones de un usuario
GET /valoraciones/usuario/{id}
Devuelve todas las valoraciones realizadas por un usuario.
Respuestas:

200 OK: Valoraciones encontradas

404 Not Found: No se encontraron valoraciones

🎞️ Obtener valoraciones de una película
GET /valoraciones/pelicula/{id}
Devuelve todas las valoraciones de una película específica.
Respuestas:

200 OK: Valoraciones encontradas

404 Not Found: No se encontraron valoraciones

➕ Alta de nueva valoración
POST /valoraciones/alta
Crea una nueva valoración.
Cuerpo: JSON con los detalles
Respuesta:

200 OK: Valoración creada exitosamente

♻️ Actualizar valoración existente
PUT /valoraciones/actualizar/{id}
Actualiza una valoración.
Cuerpo: JSON con los nuevos datos
Respuestas:

200 OK: Valoración actualizada

404 Not Found: Valoración no encontrada

❌ Eliminar valoración
DELETE /valoraciones/eliminar/{id}
Elimina una valoración por su ID.
Respuestas:

200 OK: Valoración eliminada

404 Not Found: Valoración no encontrada

👥 Obtener usuario de una valoración
GET /valoraciones/{valoracionId}/usuario
Obtiene el usuario asociado a una valoración.
Respuestas:

200 OK: Usuario encontrado

404 Not Found: Usuario no encontrado

🎬 Obtener película de una valoración
GET /valoraciones/{valoracionId}/pelicula
Obtiene la película asociada a una valoración.
Respuestas:

200 OK: Película encontrada

404 Not Found: Película no encontrada

🔢 Obtener número de valoraciones de una película
GET /valoraciones/count/{peliculaId}
Devuelve el total de valoraciones de una película.
Respuestas:

200 OK: Conteo devuelto

404 Not Found: No se encontraron valoraciones

👤 UsuarioController - Gestión de Usuarios
Controlador para operaciones CRUD de usuarios.

Endpoints
📄 Obtener lista de usuarios
GET /usuarios/list
Devuelve todos los usuarios registrados.

🔍 Obtener usuario por ID
GET /usuarios/{id}
Obtiene un usuario por su identificador.
Respuestas:

200 OK: Usuario encontrado

404 Not Found: Usuario no encontrado

➕ Alta de nuevo usuario
POST /usuarios/alta
Registra un nuevo usuario.
Cuerpo: JSON con los datos del usuario
Respuesta:

200 OK: Usuario creado exitosamente

♻️ Actualizar usuario existente
PUT /usuarios/actualizar/{id}
Actualiza los datos de un usuario.
Cuerpo: JSON con nuevos datos
Respuestas:

200 OK: Usuario actualizado

404 Not Found: Usuario no encontrado

❌ Eliminar usuario
DELETE /usuarios/eliminar/{id}
Elimina un usuario por su ID.
Respuestas:

200 OK: Usuario eliminado

404 Not Found: Usuario no encontrado

⚙️ Notas Técnicas
Todos los endpoints utilizan Mono y Flux de Reactor Core para una programación reactiva y no bloqueante.

Ideal para sistemas con alta concurrencia y rendimiento.

🧱 Tecnologías y Requisitos
☕ Java 17+

⚡ Spring Boot 2.5+

🌊 Spring WebFlux

⚙️ Reactor Core

🐳 Docker (opcional para despliegue)

🍃 MongoDB
