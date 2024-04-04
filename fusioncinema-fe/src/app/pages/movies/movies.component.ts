import { CommonModule, DatePipe } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { LanguageService } from '../../services/language.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { IMovies, Movie, PagedResponse } from '../../models/movie.model';
import { FormGroup, FormControl, Validators, FormBuilder, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-movies',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './movies.component.html',
  styleUrl: './movies.component.css'
})

export class MoviesComponent implements OnInit {

  language!: string;
  translations: any = {};
  moviesList: Movie[] = [];
  selectedMovie: Movie | null = null;
  currentPage: number = 0;
  pageSize: number = 8;
  totalPages: number = 0;
  visiblePages: number[] = [];
  searchTerm: string = '';
  searchForm: FormGroup;
  canRefresh: boolean = false;

  private _apiService = inject(ApiService);
  private _router = inject(Router);
  private datePipe = new DatePipe('en-Us')

  constructor(private FormBuilder: FormBuilder, private languageService: LanguageService, private http: HttpClient) {
    this.searchForm = this.createValidatorSearchForm();
    this.languageService.currentLanguage.subscribe(language => {
      this.language = language;
      this.loadTranslations(language); // Cargar las traducciones cuando cambia el idioma
    })
  }

  ngOnInit(): void {
    this.getAllMoviesPage();
  }

  getMovies(): void {
    this._apiService.getMovies().subscribe((response: IMovies) => {
      if (response.data instanceof Array) {
        this.moviesList = response.data;
        //console.log(response.data)
      }
    });
  }

  getAllMoviesPage(): void {
    this._apiService.getAllMoviesPage(this.currentPage, this.pageSize).subscribe((response: IMovies) => {
      if (response.data && 'content' in response.data) {
        this.moviesList = (response.data as PagedResponse).content;
        this.totalPages = response.data.totalPages;
        this.updateVisiblePages();
        //console.log(response.data)
      }
    });
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.getAllMoviesPage();
    }
  }

  updateVisiblePages(): void {
    this.visiblePages = [];
    for (let i = Math.max(0, this.currentPage - 1); i < Math.min(this.currentPage + 2, this.totalPages); i++) {
      this.visiblePages.push(i);
    }
  }

  goToPage(page: number): void {
    this.currentPage = page;
    this.getAllMoviesPage();
    this.updateVisiblePages();
  }

  previousPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.getAllMoviesPage();
    }
  }
  
  navigateToEdit(id: number): void {
    this._router.navigate(['/movie/edit', id]);
  }

  navigateToNew(): void {
    this._router.navigate(['/movie/new']);
  }

  navigateToDetail(id: number): void {
    this._router.navigate(['/movie/detail', id]);
  }

  formatDate(date: Date): string {
    const formattedDate = this.datePipe.transform(date, 'mediumDate');
    return formattedDate ? formattedDate : '';
  }

  loadTranslations(language: string) {
    this.http.get(`/assets/languages/${language}.json`).subscribe(data => {
      this.translations = data;
    });
  }

  setSelectedMovie(movie: Movie) {
    this.selectedMovie = movie;
  }

  deleteSelectedMovie(id: number | undefined): void {
    if (id !== undefined) {
      this._apiService.deleteMovie(id).subscribe((response: IMovies) => {
        if (response.status === 200) {
          // Obtén una referencia al botón de cierre del modal
          const closeButton = document.querySelector('#deleteMovie .btn-close') as HTMLElement;
          // Verifica que el botón de cierre no es null
          if (closeButton) {
            // Simula un clic en el botón de cierre
            closeButton.click();
          }
          // Recarga las películas
          this.getMovies();
        } else {

          console.error(response.errors);
        }
      });
    }
  }

  /*searchMovies(): void {
    if (this.searchForm.valid) {
      this.searchTerm = this.searchForm.value.searchTerm;
      this._apiService.getByTitleIc(this.searchTerm).subscribe((response: Movie) => {
        if (response) {
          this.moviesList = [response]; 
        } else {
          console.log('No movie found'); 
        }
      }, error => {
        console.error('Error occurred:', error); 
      });
    } else {
      console.log('Search form is not valid'); 
    }
  }*/

  searchMovies(): void {
    if (this.searchForm.valid) {
      this.searchTerm = this.searchForm.value.searchTerm;
      this._apiService.getByTitleIc(this.searchTerm).subscribe({
        next: (response: Movie) => {
          if (response) {
            this.moviesList = [response]; // Asigna la respuesta a la lista de películas
            console.log('Datos de la película:', this.moviesList);
            this.canRefresh = true;
          } else {
            console.log('No se encontró ninguna película');
          }
        },
        error: (error: any) => {
          console.error('Ocurrió un error:', error);
        }
      });
    } else {
      console.log('El formulario de búsqueda no es válido');
    }
  }

  private createValidatorSearchForm(): FormGroup {
    return this.FormBuilder.group({
      searchTerm: ['', Validators.required]
    });
  }

  refreshMovies(): void {
    if (this.canRefresh) {
      this.getMovies();
      this.canRefresh = false; 
    }
  }
}
