import { Component } from '@angular/core';
import { PropositionService } from '../../services/proposition.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-proposition-create',
  templateUrl: './proposition-create.component.html',
  styleUrls: ['./proposition-create.component.css']
})
export class PropositionCreateComponent {

  title: string = '';
  userId: number = 1; // Exemple d'ID utilisateur
  file: File | null = null;

  constructor(private propositionService: PropositionService, private router: Router) {}

  onFileChange(event: any): void {
    this.file = event.target.files[0];
  }

  createProposition(): void {
    if (this.file && this.title) {
      this.propositionService.createProposition(this.title, this.userId, this.file).subscribe(
        (data) => {
          console.log('Proposition created successfully', data);
          this.router.navigate(['/propositions']); // Rediriger après la création
        },
        (error) => {
          console.error('Error creating proposition', error);
        }
      );
    } else {
      console.error('Title and file are required');
    }
  }
}
