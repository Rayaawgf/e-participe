import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Proposition } from '../models/proposition'; // Assurez-vous que cette interface est définie

@Injectable({
  providedIn: 'root'
})
export class PropositionService {

  private apiUrl = 'http://localhost:8080/api/propositions'; 

  constructor(private http: HttpClient) { }

  // Créer une proposition avec un fichier
  createProposition(title: string, userId: number, file: File): Observable<Proposition> {
    const formData = new FormData();
    formData.append('title', title);
    formData.append('userId', userId.toString());
    formData.append('file', file);

    return this.http.post<Proposition>(this.apiUrl, formData);
  }

  // Récupérer toutes les propositions
  getAllPropositions(): Observable<Proposition[]> {
    return this.http.get<Proposition[]>(this.apiUrl);
  }

  // Récupérer une proposition par son ID
  getPropositionById(id: number): Observable<Proposition> {
    return this.http.get<Proposition>(`${this.apiUrl}/${id}`);
  }

  // Mettre à jour une proposition
  updateProposition(id: number, propositionDTO: Proposition): Observable<Proposition> {
    return this.http.put<Proposition>(`${this.apiUrl}/${id}`, propositionDTO);
  }

  // Supprimer une proposition
  deleteProposition(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
