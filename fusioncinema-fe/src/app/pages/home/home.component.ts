import { Component } from '@angular/core';
import { ThemeService } from '../../services/theme.service';
import { LanguageService } from '../../services/language.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  temaOscuro!: boolean;
  language!: string;
  translations: any = {};

  constructor(private themeService: ThemeService, private languageService: LanguageService, private http: HttpClient) {
    this.themeService.isDarkTheme.subscribe(dark => { this.temaOscuro = dark; }); // Suscribirse a isDarkTheme
    this.languageService.currentLanguage.subscribe(language => { 
      this.language = language; 
      this.loadTranslations(language); // Cargar las traducciones cuando cambia el idioma
    }) // Suscribirse a currentLanguage
  }

  loadTranslations(language: string) {
    this.http.get(`/assets/languages/${language}.json`).subscribe(data => {
      this.translations = data;
    });
  }
}
