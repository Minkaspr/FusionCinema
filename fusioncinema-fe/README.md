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

#### Obtener todos los elementos

```http
  GET /api/items
```

| Parámetro | Tipo     | Descripción                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Requerido**. Tu clave de API |

#### Obtener un elemento

```http
  GET /api/items/${id}
```

| Parámetro | Tipo     | Descripción                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Requerido**. Id del elemento a obtener |


