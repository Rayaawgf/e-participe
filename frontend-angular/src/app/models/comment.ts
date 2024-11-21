export interface Comment {
    id?: number;        // ID est optionnel pour les nouveaux commentaires
    text: string;       // Texte du commentaire
    userId: number;     // ID de l'utilisateur qui a écrit le commentaire
    contentId: number;
    
  }
  