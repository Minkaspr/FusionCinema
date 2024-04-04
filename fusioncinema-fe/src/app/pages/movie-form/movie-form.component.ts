import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { IMovies, Movie, ValidationError } from '../../models/movie.model';
import { NgClass } from '@angular/common';
import { Router } from '@angular/router';
import { LanguageService } from '../../services/language.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-movie-form',
  standalone: true,
  imports: [ReactiveFormsModule, NgClass],
  templateUrl: './movie-form.component.html',
  styleUrl: './movie-form.component.css'
})

export class MovieFormComponent {

  movieForm: FormGroup;
  rating = 0;
  formTitle = '';
  formButton = '';
  movieId: number | null;
  errorMessageTitle: string | null = null;
  errorMessageBody: string | ValidationError | null = null;
  language!: string;
  translations: any = {};
  errorMessageRequired!: string;
  errorMessageFechaInvalida!: string;

  constructor(private route: ActivatedRoute, private apiService: ApiService, private FormBuilder: FormBuilder, private router: Router, private languageService: LanguageService, private http: HttpClient) {
    this.movieForm = this.createValidatorMovieForm();
    this.movieId = null;
    this.languageService.currentLanguage.subscribe(async (language) => { 
      this.language = language; 
      await this.loadTranslations(language); // Espera a que se carguen las traducciones
    })
  }

  initializeForm() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      // Estás editando una película existente.
      this.formTitle = this.translations.movieForm.formTitle.edit;
      this.formButton = this.translations.movieForm.formButton.update;
      this.movieId = Number(id);
      // Carga los datos de la película y rellena el formulario.
      this.apiService.getMovie(this.movieId).subscribe(response => {
        if (response.status === 200 && response.data) {
          this.loadMovieData(response.data as Movie);
        }
      });
    } else {
      // Estás creando una nueva película.
      this.formTitle = this.translations.movieForm.formTitle.add;
      this.formButton = this.translations.movieForm.formButton.register;
      // Inicializa el formulario con valores predeterminados.
    }
    
    this.setErrorMessageVariables();
  }

  onSubmit() {
    const movie: Movie = this.movieForm.value;
    this.errorMessageTitle = null;
    this.errorMessageBody = null;
    movie.minimumAge = Number(movie.minimumAge);
    console.log(movie);

    // Marca todos los campos como "tocados" para mostrar los errores.
    this.movieForm.markAllAsTouched();

    // Verifica si el formulario es válido antes de hacer la petición a la API.
    if (this.movieForm.valid) {
      if (this.movieId !== null) {
        // Actualizando una película existente
        this.apiService.updateMovie(this.movieId, movie).subscribe(response => {
          console.log(response);  // Respuesta del servidor
          if (response.status === 200) {
            this.router.navigate(['/movies']);
          }
        }, error => {
          this.errorMessageTitle = error.error.message;
          this.errorMessageBody = typeof error.error.errors === 'string' ? error.error.errors : JSON.stringify(error.error.errors, null, 2);
        });
      } else {
        // Creando una nueva película
        this.apiService.saveMovie(movie).subscribe(response => {
          console.log(response);  // Respuesta del servidor
          if (response.status === 201) {
            this.router.navigate(['/movies']);
          }
        }, error => {
          // Hubo un error al crear la película.
          this.errorMessageTitle = error.error.message;
          this.errorMessageBody = typeof error.error.errors === 'string' ? error.error.errors : JSON.stringify(error.error.errors, null, 2);
        });
      }
    }
  }

  loadMovieData(movie: Movie) {
    // Convertir la fecha al formato correcto.
    const releaseYear = new Date(movie.releaseYear);
    const releaseYearString = releaseYear.toISOString().split('T')[0];

    this.movieForm.patchValue({
      title: movie.title,
      genre: movie.genre,
      releaseYear: releaseYearString,
      rating: movie.rating,
      synopsis: movie.synopsis,
      director: movie.director,
      minimumAge: movie.minimumAge
    });
    this.rating = movie.rating;
  }

  async loadTranslations(language: string) {
    this.http.get(`/assets/languages/${language}.json`).subscribe(data => {
      this.translations = data;
      this.initializeForm();
    });
  }
  
  setErrorMessageVariables() {
    this.errorMessageRequired = this.translations.movieForm.releaseYear.invalid;
    this.errorMessageFechaInvalida = this.translations.movieForm.releaseYear.invalidFuture;
  }

  private createValidatorMovieForm(): FormGroup {
    return this.FormBuilder.group({
      title: ['', [Validators.required, Validators.maxLength(250)]],
      genre: ['', Validators.required],
      releaseYear: ['', [Validators.required, this.validarFecha]],
      rating: [0, [Validators.required, Validators.min(0), Validators.max(5)]],
      synopsis: ['', Validators.required],
      director: ['', Validators.required],
      minimumAge: [-1, [Validators.required, Validators.min(0)]]
    });
  }

  hasErrors(controlName: string): boolean {
    const control = this.movieForm.get(controlName);
    return control !== null && control.errors !== null && (control.dirty || control.touched);
  }

  private validarFecha(control: FormControl): { [s: string]: boolean } | null {
    const fecha = new Date(control.value);
    if (fecha > new Date()) {
      return { fechaInvalida: true };
    }
    return null;
  }

  getReleaseYearErrorMessage(): string {
    const control = this.movieForm.get('releaseYear');
    if (control?.errors?.["required"]) {
      return this.errorMessageRequired; // Mensaje de error cuando el campo está vacío
    } else if (control?.errors?.['fechaInvalida']) {
      return this.errorMessageFechaInvalida; // Mensaje de error cuando la fecha es futura
    }
    return '';
  }

  updateRating(event: Event): void {
    const target = event.target as HTMLInputElement;
    this.rating = parseFloat(target.value);
  }
}
