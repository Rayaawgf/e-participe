import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PropositionService } from '../../services/proposition.service';
import { AuthService } from '../../services/auth.service'; // Service d'authentification
import { Proposition } from '../../models/proposition';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-proposition-create',
  templateUrl: './proposition-create.component.html',
  styleUrls: ['./proposition-create.component.css']
})
export class PropositionCreateComponent implements OnInit {
  title: string = '';
  file: File | null = null;
  userId: number | null = null;

  constructor(
    private propositionService: PropositionService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Récupérer l'ID de l'utilisateur connecté
    this.userId = this.userService.getCurrentUserId();
    if (!this.userId) {
      console.error('Utilisateur non authentifié.');
      this.router.navigate(['/login']); // Rediriger vers la page de connexion si non connecté
    }
  }

  onFileChange(event: any): void {
    this.file = event.target.files[0];
  }

  createProposition(): void {
    if (!this.title || !this.file || !this.userId) {
      alert('Veuillez remplir tous les champs.');
      return;
    }

    this.propositionService.createProposition(this.title, this.userId, this.file).subscribe({
      next: (response: Proposition) => {
        console.log('Proposition créée avec succès :', response);
        this.router.navigate(['/propositions']); // Redirection après création
      },
      error: (err) => {
        console.error('Erreur lors de la création de la proposition :', err);
      }
    });
  }
}
