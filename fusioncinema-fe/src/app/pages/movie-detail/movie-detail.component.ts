import { Component, inject } from '@angular/core'; 
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { Movie } from '../../models/movie.model';
import { CommonModule } from '@angular/common';
import { ThemeService } from '../../services/theme.service';
import { LanguageService } from '../../services/language.service';
import { HttpClient } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-movie-detail',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './movie-detail.component.html',
  styleUrl: './movie-detail.component.css'
})
export class MovieDetailComponent {
  temaOscuro!: boolean;
  movie: Movie | null = null;
  language!: string;
  translations: any = {};
  private datePipe = new DatePipe('en-US'); // crea una nueva instancia de DatePipe
  private _router = inject(Router);

  constructor(private route: ActivatedRoute, private apiService: ApiService, private themeService: ThemeService, private languageService: LanguageService, private http: HttpClient) {
    
    this.themeService.isDarkTheme.subscribe(dark => { this.temaOscuro = dark; }); 
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.apiService.getMovie(Number(id)).subscribe(response => {
        if (response.status === 200 && response.data) {
          this.movie = response.data as Movie;
        }
      });
    }

    this.languageService.currentLanguage.subscribe(language => { 
      this.language = language; 
      this.loadTranslations(language);
    })
  }

  public  generateRatingSteps(rating: number): number[] {
    const steps = Math.floor(rating);
    const halfStep = rating - steps > 0 ? 1 : 0;
    const remainingSteps = 5 - steps - halfStep;
    return [
      ...Array.from({ length: steps }, () => 1),
      ...Array.from({ length: halfStep }, () => 2),
      ...Array.from({ length: remainingSteps }, () => 0),
    ];
  }

  loadTranslations(language: string) {
    this.http.get(`/assets/languages/${language}.json`).subscribe(data => {
      this.translations = data;
    });
  }

  getFormattedDate(date: Date): string {
    return this.datePipe.transform(date, 'dd/MM/yyyy') || '';
  }

  navigateToMovies(): void {
    this._router.navigate(['/movies']);
  }
}
