import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user: User = { name: '', number: '', password: '' };
  loginForm: FormGroup;
  isLoading = false;
  errorMessage: string = '';

  constructor(
    private userService: UserService,
    private fb: FormBuilder,
    private router: Router
  ) { 
    this.loginForm = this.fb.group({
      number: ['', [Validators.required, Validators.pattern('^[234]{1}[0-9]{7}$')]],
      password: ['', [Validators.required, Validators.minLength(4)]]
    });
  }

  login() {
    if (this.loginForm.valid) {
      this.isLoading = true;
      const user: User = this.loginForm.value;

      this.userService.loginUser(user).subscribe({
        next: (response) => {
          console.log('User logged in:', response);
          // Redirection vers la page d'accueil en cas de succès
          this.router.navigate(['']);
        },
        error: (error) => {
          console.error('Error logging in:', error);
          this.isLoading = false;
          this.errorMessage = 'Erreur lors de la connexion. Veuillez vérifier vos informations et réessayer.';
        },
        complete: () => {
          this.isLoading = false;
        }
      });
    } else {
      console.log("Le formulaire est invalide");
    }
  }
}
