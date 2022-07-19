import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthenticationService} from "./authentication.service";

@Injectable()
export class HttpAuthenticationInterceptor implements HttpInterceptor {

  constructor(private authService: AuthenticationService) {
    // Nothing
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const jwt: string | null = AuthenticationService.getCurrentJwtToken();
    if (AuthenticationService.isAuthenticated() && jwt != null) {
      request = request.clone({
        withCredentials: true,
        headers: request.headers.set('Authorization', 'Bearer ' + jwt)
      });
    }

    return next.handle(request);
  }
}
