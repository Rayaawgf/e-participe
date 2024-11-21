import { Component, OnInit } from '@angular/core';
import { PropositionService } from '../../services/proposition.service';
import { Proposition } from '../../models/proposition';

@Component({
  selector: 'app-proposition-list',
  templateUrl: './proposition-list.component.html',
  styleUrls: ['./proposition-list.component.css']
})
export class PropositionListComponent implements OnInit {
  propositions: Proposition[] = [];

  constructor(private propositionService: PropositionService) {}

  ngOnInit(): void {
    this.loadPropositions();
  }

  loadPropositions(): void {
    this.propositionService.getAllPropositions().subscribe(
      (data) => {
        console.log('Propositions récupérées :', data); // Vérifiez les données reçues ici
        this.propositions = data;
      },
      (error) => {
        console.error('Erreur lors du chargement des propositions :', error);
      }
    );
  }
  

  editProposition(id: number): void {
    console.log('Modifier la proposition avec l’ID :', id);
    // Logique pour la modification
  }

  deleteProposition(id: number): void {
    this.propositionService.deleteProposition(id).subscribe(() => {
      this.propositions = this.propositions.filter((p) => p.id !== id);
    });
  }
}
