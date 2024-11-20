import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { OtpService } from '../../services/otp.service'; // Importez le service OTP
import { User } from '../../models/user';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;
  otpForm: FormGroup; // Formulaire pour la vérification de l'OTP
  isLoading = false;
  isOtpSent = false; // Pour gérer l'état d'envoi de l'OTP
  verifToken: string = ''; // Token reçu pour la vérification de l'OTP
  errorMessage: string = '';

  constructor(
    private userService: UserService,
    private otpService: OtpService, // Injectez le service OTP
    private fb: FormBuilder,
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      number: ['', [Validators.required, Validators.pattern('^[234]{1}[0-9]{7}$')]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });

    this.otpForm = this.fb.group({
      otp: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(6)]]
    });
  }

  // Étape 1 : Envoyer l'OTP
  sendOtp() {
    if (this.registerForm.get('number')?.valid) {
      const phoneNumber = this.registerForm.get('number')?.value;
      this.isLoading = true;

      this.otpService.sendOtp(phoneNumber, 'MyApp').subscribe({
        next: (response) => {
          console.log('OTP envoyé:', response);
          this.isOtpSent = true;
          this.verifToken = response.verifToken; // Stocker le token pour la vérification
        },
        error: (error) => {
          console.error('Erreur lors de l\'envoi de l\'OTP:', error);
          this.errorMessage = 'Erreur lors de l\'envoi de l\'OTP. Veuillez réessayer.';
        },
        complete: () => {
          this.isLoading = false;
        }
      });
    }
  }

  // Étape 2 : Vérifier l'OTP
  verifyOtpAndRegister() {
    if (this.otpForm.valid) {
      const otp = this.otpForm.get('otp')?.value;

      this.otpService.verifyOtp(otp, this.verifToken).subscribe({
        next: (response) => {
          console.log('OTP vérifié:', response);
          if (response.success) {
            this.registerUser(); // Procéder à l'inscription
          } else {
            this.errorMessage = 'OTP invalide ou expiré.';
          }
        },
        error: (error) => {
          console.error('Erreur lors de la vérification de l\'OTP:', error);
          this.errorMessage = 'Erreur lors de la vérification de l\'OTP.';
        }
      });
    }
  }

  // Étape 3 : Inscrire l'utilisateur
  private registerUser() {
    const user: User = this.registerForm.value;
    this.isLoading = true;

    this.userService.registerUser(user).subscribe({
      next: (response) => {
        console.log('Utilisateur inscrit:', response);
        this.router.navigate(['login']);
      },
      error: (error) => {
        console.error('Erreur lors de l\'inscription:', error);
        this.errorMessage = 'Erreur lors de l\'inscription. Veuillez réessayer.';
      },
      complete: () => {
        this.isLoading = false;
      }
    });
  }
}
