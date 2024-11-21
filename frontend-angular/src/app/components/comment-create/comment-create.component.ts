import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommentService } from '../../services/comment.service';  // Assurez-vous que le service existe et est bien importé
import { Comment } from '../../models/comment';  // Votre modèle Comment avec id, text, userId, et contentId

@Component({
  selector: 'app-comment-create',
  templateUrl: './comment-create.component.html',
  styleUrls: ['./comment-create.component.css']
})
export class CommentCreateComponent {

  contentId: number;  // ID du contenu auquel le commentaire est lié
  userId: number = 1;  // Vous pouvez obtenir l'ID utilisateur de la session ou du service d'authentification
  text: string = '';   // Le texte du commentaire
  isSubmitting: boolean = false;  // Indique si le formulaire est en cours de soumission

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private commentService: CommentService
  ) { 
    // Récupère l'ID du contenu depuis l'URL
    this.contentId = +this.route.snapshot.paramMap.get('contentId')!;
  }

  // Méthode pour créer un commentaire
  createComment(): void {
    if (this.text.trim() === '') {
      alert('Le commentaire ne peut pas être vide');
      return;
    }

    this.isSubmitting = true;

    // Crée le commentaire avec userId et contentId
    const comment: Comment = { 
      text: this.text, 
      userId: this.userId,  // Par exemple, obtenir l'ID utilisateur de la session
      contentId: this.contentId 
    };

    this.commentService.createComment(comment).subscribe(
      () => {
        this.isSubmitting = false;
        this.router.navigate([`/contents/:category`]);  // Redirige vers la page du contenu après la création du commentaire
      },
      (error) => {
        console.error('Error creating comment:', error);
        this.isSubmitting = false;
      }
    );
  }
}
