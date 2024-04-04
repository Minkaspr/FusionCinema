import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LanguageService {

  private _currentLanguage = new BehaviorSubject<string>('en');
  currentLanguage = this._currentLanguage.asObservable();

  constructor() { }

  changeLanguage(language: string) {
    this._currentLanguage.next(language);
  }
}
