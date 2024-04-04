// Representa una película en la aplicación.
export interface Movie {
  id?: number; // El ID único de la película. Es opcional porque no se necesita al crear una nueva película.
  title: string; // El título de la película.
  genre: string; // El género de la película.
  releaseYear: Date;   // El año de lanzamiento de la película.
  rating: number; // La calificación de la película.
  synopsis: string; // La sinopsis de la película.
  director: string; // El director de la película.
  minimumAge: number; // La edad mínima recomendada para ver la película.
}

// Representar los errores de validación que pueden ocurrir al procesar los datos de la película.
export interface ValidationError {
  [key: string]: string; // Un objeto que mapea los nombres de los campos a los mensajes de error de validación.
}

// Representar la respuesta de la API al realizar operaciones en las películas.
export interface IMovies {
  status: number; // El código de estado HTTP de la respuesta.
  message: string; // Un mensaje descriptivo sobre la respuesta.
  data: Movie | Movie[] | PagedResponse | null; // Los datos de la película o películas que se devuelven en la respuesta, si los hay.
  errors: string | ValidationError | null; // Cualquier error que ocurrió durante el procesamiento de la solicitud.
}

// Representa la respuesta paginada de la API al obtener una lista de películas.
export interface PagedResponse {
  content: Movie[]; // La lista de películas en la página actual.
  pageable: Pageable; // Los detalles de la paginación para la página actual.
  last: boolean; // Un indicador de si la página actual es la última.
  totalElements: number; // El número total de películas en todas las páginas.
  totalPages: number; // El número total de páginas.
  first: boolean; // Un indicador de si la página actual es la primera.
  numberOfElements: number; // El número de películas en la página actual.
  size: number; // El tamaño de la página (es decir, el número máximo de películas por página).
  number: number; // El número de la página actual.
  sort: Sort; // Los detalles de cómo se ordenan las películas.
  empty: boolean; // Un indicador de si la página actual está vacía.
}

// Representa los detalles de la paginación para una página de películas.
export interface Pageable {
  pageNumber: number; // El número de la página actual.
  pageSize: number; // El tamaño de la página (es decir, el número máximo de películas por página).
  sort: Sort; // Los detalles de cómo se ordenan las películas.
  offset: number; // El desplazamiento de la página actual (es decir, el número de películas que se han omitido).
  paged: boolean; // Un indicador de si la respuesta está paginada.
  unpaged: boolean; // Un indicador de si la respuesta no está paginada.
}

// Representa los detalles de cómo se ordenan las películas en una página.
export interface Sort {
  empty: boolean; // Un indicador de si no hay ningún criterio de ordenación.
  sorted: boolean; // Un indicador de si las películas están ordenadas.
  unsorted: boolean; // Un indicador de si las películas no están ordenadas.
}