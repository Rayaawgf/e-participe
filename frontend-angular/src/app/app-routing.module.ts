// app-routing.module.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { ContentListComponent } from './components/content-list/content-list.component';
import { HomeComponent } from './components/home/home.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { CommentComponent } from './components/comment/comment.component';
import { CommentsComponent } from './components/comments/comments.component';
import { ContentDetailsComponent } from './components/content-details/content-details.component';
import { PropositionListComponent } from './components/proposition-list/proposition-list.component';
import { PropositionCreateComponent } from './components/proposition-create/proposition-create.component';
import { CreateContentComponent } from './components/create-content/create-content.component';
import { CommentCreateComponent } from './components/comment-create/comment-create.component';


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'comment', component: CommentComponent },
  { path: 'users', component: UserListComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'contents/:category', component: ContentListComponent },
  { path: 'create', component: CreateContentComponent },
  { path: 'comments', component: CommentsComponent },
  { path: 'create-comment/:contentId', component: CommentCreateComponent },
  { path: 'contents/:id', component: ContentDetailsComponent },
  { path: 'propositions', component: PropositionListComponent },
  { path: 'propositions/create', component: PropositionCreateComponent },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }