# 🎬 FusionCinema

Bienvenido a FusionCinema, una plataforma completa para descubrir, gestionar y disfrutar de tus películas favoritas en un solo lugar. ¡Experimenta el cine como nunca antes!

## 🌟 Características

- **Variedad de películas**: Disfruta de una amplia variedad de películas de todos los géneros y épocas. Siempre hay algo nuevo para descubrir en FusionCinema.
- **Gestión personalizada**: Crea tu propia lista de películas favoritas, califica las películas que has visto y recibe recomendaciones personalizadas basadas en tus gustos.
- **Experiencia de usuario excepcional**: Nuestra plataforma es fácil de usar y está diseñada para ofrecerte la mejor experiencia de usuario. Disfruta del cine sin complicaciones.

## 🛠️ Tecnologías

FusionCinema es una aplicación de tipo "separada" (también conocida como "desacoplada"), lo que significa que el backend y el frontend están separados y se comunican a través de una API. Esto proporciona una gran flexibilidad y permite que cada parte de la aplicación se desarrolle y escale de manera independiente.

- **Backend**: Desarrollado con Java y Spring, nuestra API RESTful se encarga de gestionar y persistir los datos en una base de datos MySQL.
- **Frontend**: Construido con Angular y Bootstrap, nuestro frontend proporciona una interfaz de usuario rica e interactiva.

## 📚 CRUD y más

Con FusionCinema, puedes realizar todas las operaciones básicas de gestión de datos, conocidas como operaciones CRUD (Crear, Leer, Actualizar, Eliminar), en las películas.

 - **Crear**: Puedes agregar una nueva película a la base de datos.
 - **Leer**: Puedes visualizar los datos de las películas existentes. Esto se facilita con un tablero con paginación y opciones de búsqueda.
 - **Actualizar**: Puedes editar los detalles de una película existente.
 - **Eliminar**: Puedes eliminar una película de la base de datos.
   
Además, tanto para la operación de agregar (Crear) como para la de modificar (Actualizar), implementamos validaciones en dos niveles:

- **Frontend**: Antes de enviar los datos al servidor, realizamos algunas comprobaciones básicas en el lado del cliente (como verificar que todos los campos obligatorios estén llenos, etc.). Esto ayuda a evitar solicitudes innecesarias al servidor.
- **Backend**: Una vez que los datos llegan al servidor, realizamos validaciones adicionales (como verificar la unicidad de ciertos campos, etc.). Esto asegura la integridad y la consistencia de los datos en nuestra base de datos.


## 🌓 Modo oscuro y claro

FusionCinema soporta tanto el modo oscuro como el claro, para que puedas elegir el que más te guste y sea más cómodo para tus ojos.

## 🌐 Multilenguaje

FusionCinema soporta varios idiomas, incluyendo inglés y español, para que puedas disfrutar de tus películas favoritas en el idioma que prefieras.

## 🚀 Empieza ahora

Descubre más sobre FusionCinema y experimenta el cine como nunca antes. ¡Empieza ahora!
```bash
https://github.com/Minkaspr/FusionCinema.git
```
