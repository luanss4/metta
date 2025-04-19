import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService, private router: Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    // Skip adding token for auth endpoints (login and register)
    if (request.url.includes('/api/auth/login') || request.url.includes('/api/auth/register')) {
      return next.handle(request);
    }

    const token = this.authService.getToken();

    // Log the request URL and token for debugging
    console.log('Request URL:', request.url);
    console.log('Token exists:', !!token);

    if (token) {
      // Clone the request with the Authorization header
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
      console.log('Token added to request');
    } else {
      console.log('No token available, request will proceed without authorization');
    }

    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          // Unauthorized - token expired or invalid
          this.authService.logout();
          this.router.navigate(['/login']);
        }
        // For 403 Forbidden errors, we don't log out the user
        // as they might just lack permissions for a specific operation
        return throwError(() => error);
      })
    );
  }
}
