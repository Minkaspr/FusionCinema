# FusioncinemaFe

Este proyecto fue generado con [Angular CLI](https://github.com/angular/angular-cli) versión 17.2.3. <> `ng new fusioncinema-fe`

## Servidor de desarrollo

Ejecuta `ng serve` para un servidor de desarrollo. Navega a `http://localhost:4200/`. La aplicación se recargará automáticamente si cambias alguno de los archivos fuente.

## Andamiaje de código

Ejecuta `ng generate component component-name` | `ng g c component-name` para generar un nuevo componente. También puedes usar `ng generate directive|pipe|service|class|guard|interface|enum|module` → `ng g s service-name`.

## Construcción

Ejecuta `ng build` para construir el proyecto. Los artefactos de construcción se almacenarán en el directorio `dist/`.

## Ejecución de pruebas unitarias

Ejecuta `ng test` para ejecutar las pruebas unitarias a través de [Karma](https://karma-runner.github.io).

## Ejecución de pruebas de extremo a extremo

Ejecuta `ng e2e` para ejecutar las pruebas de extremo a extremo a través de una plataforma de tu elección. Para usar este comando, primero necesitas agregar un paquete que implemente capacidades de pruebas de extremo a extremo.

## Ayuda adicional

Para obtener más ayuda sobre Angular CLI, usa `ng help` o consulta la página de [Resumen y Referencia de Comandos de Angular CLI](https://angular.io/cli).

## Bootstrap

#### Instalación

> [!TIP]
> ```bash
>  npm i bootstrap@5.3.3
> ```

#### Configuración

```http
  "assets": [
    "src/favicon.ico",
    "src/assets"
  ],
  "styles": [
    "src/styles.css",
    "node_modules/bootstrap/dist/css/bootstrap.min.css"
  ],
  "scripts": [
    "node_modules/@popperjs/core/dist/umd/popper.min.js",
    "node_modules/bootstrap/dist/js/bootstrap.min.js"
  ]
```

## Referencia de API

#### Obtener todas las películas

```http
GET /api/FusionCinema/getAll
```

#### Obtener todas las películas con paginación

```http
GET /api/FusionCinema/getAllMovies?page={page}&size={size}
```

| Parámetro | Tipo     | Descripción                |
| :-------- | :------- | :------------------------- |
| `page` | `number	` | **Requerido**. Número de la página a obtener |
| `size` | `number	` | **Requerido**. Cantidad de elementos por página |

#### Obtener una película

```http
GET /api/FusionCinema/getMovie/{id}
```

| Parámetro | Tipo     | Descripción                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `number` | **Requerido**. Id de la película a obtener |


#### Guardar una película

```http
POST /api/FusionCinema/saveMovie
```

| Parámetro | Tipo     | Descripción                       |
| :-------- | :------- | :-------------------------------- |
| `movie`      | `object` | **Requerido**. Objeto de película a guardar |

#### Actualizar una película

```http
PUT /api/FusionCinema/updateMovie/{id}
```

| Parámetro | Tipo     | Descripción                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `number` | **Requerido**. Id de la película a actualizar |
| `movie`      | `object` | **Requerido**. Objeto de película con los datos actualizados |

#### Eliminar una película

```http
DELETE /api/FusionCinema/deleteMovie/{id}
```

| Parámetro | Tipo     | Descripción                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `number` | **Requerido**. Id de la película a eliminar |

#### Obtener una película por título (ignorando mayúsculas y minúsculas)

```http
GET /api/FusionCinema/getByTitleIc/{title}
```

| Parámetro | Tipo     | Descripción                       |
| :-------- | :------- | :-------------------------------- |
| `title`      | `string` | **Requerido**. Título de la película a obtener |