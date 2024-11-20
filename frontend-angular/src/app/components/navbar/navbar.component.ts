import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { Category } from 'src/app/models/content';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  categories = Object.values(Category); // ['LAW', 'ANNOUNCEMENT', 'SURVEY']
  isAuthenticated: boolean = false;

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    // Vérifiez si l'utilisateur est authentifié
    this.isAuthenticated = this.userService.isAuthenticated();
  }

  logout(): void {
    this.userService.logout();
    this.isAuthenticated = false;
    this.router.navigate(['/']); // Redirigez vers la page d'accueil après la déconnexion
  }
}
