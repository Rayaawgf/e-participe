import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ContentService } from '../../services/content.service';
import { CommentService } from '../../services/comment.service';

@Component({
  selector: 'app-content-details',
  templateUrl: './content-details.component.html',
  styleUrls: ['./content-details.component.css']
})
export class ContentDetailsComponent implements OnInit {
  content: any = null; // Détails du contenu
  comments: any[] = []; // Liste des commentaires

  constructor(
    private route: ActivatedRoute,
    private contentService: ContentService,
    private commentService: CommentService
  ) {}

  ngOnInit(): void {
    // Récupérez l'ID du contenu depuis l'URL
    const contentId = Number(this.route.snapshot.paramMap.get('id'));

    // Chargez les détails du contenu
    this.contentService.getContentById(contentId).subscribe(
      (data) => {
        this.content = data;
      },
      (error) => {
        console.error('Erreur lors du chargement des détails du contenu:', error);
      }
    );

    // Chargez les commentaires associés au contenu
    this.commentService.getCommentsByContentId(contentId).subscribe(
      (data) => {
        this.comments = data;
      },
      (error) => {
        console.error('Erreur lors du chargement des commentaires:', error);
      }
    );
  }
}
