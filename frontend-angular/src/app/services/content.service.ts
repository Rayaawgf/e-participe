// src/app/services/content.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Content } from '../models/content';

@Injectable({
  providedIn: 'root'
})
export class ContentService {
  private apiUrl = 'http://localhost:8080/api/contents';

  constructor(private http: HttpClient) { }

  getAllContents(): Observable<Content[]> {
    return this.http.get<Content[]>(this.apiUrl);
  }

  getContentById(id: number): Observable<Content> {
    return this.http.get<Content>(`${this.apiUrl}/${id}`);
  }

  createContent(content: Content): Observable<Content> {
    return this.http.post<Content>(this.apiUrl, content);
  }

  updateContent(id: number, content: Content): Observable<Content> {
    return this.http.put<Content>(`${this.apiUrl}/${id}`, content);
  }

  deleteContent(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
