import { Component, OnInit } from '@angular/core';
import { ContentService } from '../../services/content.service';
import { Content } from '../../models/content';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-content',
  templateUrl: './create-content.component.html',
  styleUrls: ['./create-content.component.css']
})
export class CreateContentComponent implements OnInit {
  contents: Content[] = [];
  selectedContent: Content | null = null;

  constructor(private contentService: ContentService, private router: Router) {}

  ngOnInit(): void {
    this.fetchContents();
  }

  fetchContents(): void {
    this.contentService.getAllContents().subscribe(data => {
      this.contents = data;
    });
  }

  viewContent(content: Content): void {
    this.selectedContent = content;
  }

  createContent(content: Content): void {
    this.contentService.createContent(content).subscribe((newContent) => {
      this.contents.push(newContent); // Ajoute le nouveau contenu à la liste
      this.selectedContent = null; // Réinitialise le contenu sélectionné
  
      // Redirection vers la page des contenus
      this.router.navigate(['/contents/:category']);
    });
  }

  updateContent(content: Content): void {
    if (content.id) {
      this.contentService.updateContent(content.id, content).subscribe(updatedContent => {
        const index = this.contents.findIndex(c => c.id === updatedContent.id);
        if (index > -1) this.contents[index] = updatedContent;
        this.selectedContent = null;
      });
    }
  }

  deleteContent(id: number): void {
    this.contentService.deleteContent(id).subscribe(() => {
      this.contents = this.contents.filter(content => content.id !== id);
      this.selectedContent = null;
    });
  }
}
