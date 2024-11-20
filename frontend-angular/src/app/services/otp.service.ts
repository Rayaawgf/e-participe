import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class OtpService {
  private baseUrl = 'http://apipd.digital.gov.mr';
  private authHeader = 'Basic ' + btoa('temp:te@mp!24'); // Encodage des credentials

  constructor(private http: HttpClient) {}

  sendOtp(phoneNumber: string, appName: string): Observable<any> {
    const url = `${this.baseUrl}/send-msg-otp`;
    const headers = new HttpHeaders({
      Authorization: this.authHeader,
      'Content-Type': 'application/json',
    });

    const body = { phoneNumber, appName, lang: 'en' };
    return this.http.post(url, body, { headers });
  }

  verifyOtp(otp: string, verifToken: string): Observable<any> {
    const url = `${this.baseUrl}/verify-msg-otp`;
    const headers = new HttpHeaders({
      Authorization: this.authHeader,
      'Content-Type': 'application/json',
    });

    const body = { otp, verifToken };
    return this.http.post(url, body, { headers });
  }
}
