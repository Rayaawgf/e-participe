// src/app/models/content.model.ts
export enum Category {
  LAW = 'LAW',
  ANNOUNCEMENT = 'ANNOUNCEMENT',
  SURVEY = 'SURVEY',
}

export interface Content {
  id?: number;
  title: string;
  body: string;
  createdDate: string;
  category: Category;

  comments?: Comment[];
  questions?: Question[];
}

export interface Comment {
  id?: number;
  content: string;
}

export interface Question {
  id?: number;
  question: string;
}
