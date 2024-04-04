import { Component, HostListener } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { ThemeService } from './services/theme.service';
import { LanguageService } from './services/language.service';
import { CommonModule } from '@angular/common';
import { Router, NavigationEnd } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'fusioncinema-fe';

  temaOscuro: boolean = false;
  temaActual: string = 'auto';
  language: string = 'en';
  translations: any = {};

  lightThemeIcon = 'assets/film-light.ico';
  darkThemeIcon = 'assets/film-dark.ico';

  menuOption: string = '';

  constructor(private router: Router, private http: HttpClient, private themeService: ThemeService, private languageService: LanguageService) {
    // Recuperar la preferencia guardada al cargar la página
    this.detectarTemaNavegador();
    this.detectBrowserLanguage();

    // Suscríbete a los eventos de navegación del router.
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        // Selecciona la opción del menú basándose en la URL actual.
        if (this.router.url.includes('movies')) {
          this.onOption('movies');
        } else if (this.router.url.includes('movie/new') || this.router.url.includes('movie/edit') || this.router.url.includes('movie/detail')) {
          this.onOption('');
        } else {
          this.onOption('home');
        }
      }
    });
  }

  @HostListener('window:load')
  detectarTemaNavegador() {
    // Verifica si el tema del navegador está configurado como oscuro (true o false)
    const temaNavegador = window.matchMedia('(prefers-color-scheme: dark)').matches;
    // Recupera la preferencia de tema guardada en el almacenamiento local (true o null)
    const temaActualGuardado = localStorage.getItem('temaActual');

    if (temaActualGuardado !== null) {
      this.temaActual = temaActualGuardado;
      if (this.temaActual === 'auto') {
        this.temaOscuro = temaNavegador;
      } else {
        this.temaOscuro = this.temaActual === 'dark';
      }
    } else {
      this.temaOscuro = temaNavegador;
      this.temaActual = 'auto';
    };

    // Cambiar el favicon en función del tema del navegador
    if (temaNavegador) {
      this.setFavicon(this.lightThemeIcon); // Si el tema del navegador es oscuro → favicon light
    } else {
      this.setFavicon(this.darkThemeIcon); // Si el tema del navegador es claro → favicon dark
    };

    // Aplicar el tema al cargar la página
    document.documentElement.setAttribute('data-bs-theme', this.temaOscuro ? 'dark' : 'light');
    // Actualizar el estado del tema oscuro en ThemeService
    this.themeService.setDarkTheme(this.temaOscuro);
  }

  detectBrowserLanguage() {
    // Recupera la preferencia de idioma guardada en el almacenamiento local (es, en o null)
    const languageGuardado = localStorage.getItem('language');

    if (languageGuardado !== null) {
      this.language = languageGuardado;
    } else {
      const browserLanguage = navigator.language || navigator.languages[0];
      this.language = browserLanguage.includes('es') ? 'es' : 'en';
    }

    // Carga las traducciones para el idioma seleccionado
    this.loadTranslations(this.language);
    // Actualiza el idioma en LanguageService
    this.languageService.changeLanguage(this.language);
  }  

  // Función para cambiar el favicon
  setFavicon(icon: string): void {
    const link: HTMLLinkElement = document.querySelector("link[rel*='icon']") || document.createElement('link');
    link.type = 'image/x-icon';
    link.rel = 'shortcut icon';
    link.href = icon;
    document.getElementsByTagName('head')[0].appendChild(link);
  }

  cambiarTema(tema: string) {
    switch (tema) {
      case 'light':
        this.temaOscuro = false;
        break;
      case 'dark':
        this.temaOscuro = true;
        break;
      case 'auto':
        this.temaOscuro = window.matchMedia('(prefers-color-scheme: dark)').matches;
        break;
      case 'toggle':
        this.temaOscuro = !this.temaOscuro;
        tema = this.temaOscuro ? 'dark' : 'light';
        break;
    }
    this.temaActual = tema;
    localStorage.setItem('temaActual', this.temaActual);
    document.documentElement.setAttribute('data-bs-theme', this.temaOscuro ? 'dark' : 'light');
    this.themeService.setDarkTheme(this.temaOscuro); // Actualizar el estado del tema oscuro en ThemeService
  }

  changeLanguage(event: Event, language: string) {
    event.preventDefault();
    this.language = language;
    localStorage.setItem('language', language);
    this.loadTranslations(language);
    this.languageService.changeLanguage(language);
  }

  loadTranslations(language: string) {
    this.http.get(`/assets/languages/${language}.json`).subscribe(data => {
      this.translations = data;
    });
  }

  onOption(menuOption: string){
    this.menuOption = menuOption;
  }
}

