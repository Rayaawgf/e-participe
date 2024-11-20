import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Comment } from '../models/comment'; // Utilisation du modèle Comment

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private apiUrl = 'http://localhost:8080/api/comments'; // Assurez-vous que l'URL correspond à celle de votre API backend

  constructor(private http: HttpClient) { }

  // Récupérer tous les commentaires
  getAllComments(): Observable<Comment[]> {
    return this.http.get<Comment[]>(this.apiUrl);
  }

  // Récupérer un commentaire par ID
  getCommentById(id: number): Observable<Comment> {
    return this.http.get<Comment>(`${this.apiUrl}/${id}`);
  }

  // Récupérer les commentaires par contentId
  getCommentsByContentId(contentId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.apiUrl}/content/${contentId}`);
  }

  // Créer un nouveau commentaire
  createComment(comment: Comment): Observable<Comment> {
    return this.http.post<Comment>(this.apiUrl, comment, {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    });
  }

  // Mettre à jour un commentaire
  updateComment(id: number, comment: Comment): Observable<Comment> {
    return this.http.put<Comment>(`${this.apiUrl}/${id}`, comment, {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    });
  }

  // Supprimer un commentaire
  deleteComment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
