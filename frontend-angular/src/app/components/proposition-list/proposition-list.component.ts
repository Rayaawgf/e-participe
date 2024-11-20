import { Component, OnInit } from '@angular/core';
import { PropositionService } from '../../services/proposition.service';
import { Proposition } from '../../models/proposition'; // Utilisation du modèle Proposition
import { Router } from '@angular/router';

@Component({
  selector: 'app-proposition-list',
  templateUrl: './proposition-list.component.html',
  styleUrls: ['./proposition-list.component.css']
})
export class PropositionListComponent implements OnInit {

  propositions: Proposition[] = [];

  constructor(private propositionService: PropositionService, private router: Router) {}

  ngOnInit(): void {
    this.loadPropositions();
  }

  loadPropositions(): void {
    this.propositionService.getAllPropositions().subscribe(
      (data) => {
        this.propositions = data;
      },
      (error) => {
        console.error('Error loading propositions', error);
      }
    );
  }

  // Accéder aux détails d'une proposition
  viewProposition(id: number): void {
    this.router.navigate([`/propositions/${id}`]);
  }

  // Supprimer une proposition
  deleteProposition(id: number): void {
    this.propositionService.deleteProposition(id).subscribe(
      () => {
        this.loadPropositions(); // Recharger la liste après la suppression
      },
      (error) => {
        console.error('Error deleting proposition', error);
      }
    );
  }

  createProposition(): void {
    this.router.navigate(['propositions/create']);
  }
}
