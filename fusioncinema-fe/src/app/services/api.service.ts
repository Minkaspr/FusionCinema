import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { IMovies, Movie } from '../models/movie.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private _http = inject(HttpClient);
  private urlBase: string  = 'http://localhost:8080/api/FusionCinema';

  getMovies(): Observable<IMovies>{
    return this._http.get<IMovies>(`${this.urlBase}/getAll`);
  }

  getAllMoviesPage(page: number, size: number): Observable<IMovies> {
    return this._http.get<IMovies>(`${this.urlBase}/getAllMovies?page=${page}&size=${size}`);
  }

  getMovie(id: number): Observable<IMovies>{
    return this._http.get<IMovies>(`${this.urlBase}/getMovie/${id}`);
  }

  saveMovie(movie: Movie): Observable<IMovies>{
    return this._http.post<IMovies>(`${this.urlBase}/saveMovie`, movie);
  }

  updateMovie(id: number, movie: Movie): Observable<IMovies>{
    return this._http.put<IMovies>(`${this.urlBase}/updateMovie/${id}`, movie);
  }

  deleteMovie(id: number): Observable<IMovies>{
    return this._http.delete<IMovies>(`${this.urlBase}/deleteMovie/${id}`);
  }

  getByTitleIc(title: string): Observable<Movie>{
    return this._http.get<Movie>(`${this.urlBase}/getByTitleIc/${title}`);
  }
}
