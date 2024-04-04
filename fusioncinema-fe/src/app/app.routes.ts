import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { MoviesComponent } from './pages/movies/movies.component';
import { MovieDetailComponent } from './pages/movie-detail/movie-detail.component';
import { MovieFormComponent } from './pages/movie-form/movie-form.component';

export const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'movies', component: MoviesComponent},
    {path: 'movie/new', component: MovieFormComponent},
    {path: 'movie/edit/:id', component: MovieFormComponent},
    {path: 'movie/detail/:id', component: MovieDetailComponent},
    {path: '**', redirectTo: '', pathMatch: 'full'}
];
