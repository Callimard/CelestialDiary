import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from './login/login.component';
import {ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {AuthenticationService} from "../service/authentication/authentication.service";
import {ApplicationComponent} from './application/application.component';
import {environment} from "../environments/environment";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import {LangageSelectionComponent} from './langage-selection/langage-selection.component';
import {EmployeeManagementComponent} from './application/employee-management/employee-management.component';
import {EstablishmentManagementComponent} from './application/establishment-management/establishment-management.component';
import {SearchBarComponent} from './libairy/search-bar/search-bar.component';
import {InformationTicketComponent} from './libairy/information-ticket/information-ticket.component';
import {HttpAuthenticationInterceptor} from "../service/authentication/http-authentication.interceptor";
import { UnwrapArrowComponent } from './libairy/unwrap-arrow/unwrap-arrow.component';
import { EmployeeInformationUpdaterComponent } from './application/employee-management/employee-information-updater/employee-information-updater.component';
import { EmployeeCreatorComponent } from './application/employee-management/employee-creator/employee-creator.component';

const appRoutes: Routes = [
  {path: environment.frontLoginPage, component: LoginComponent},
  {
    path: environment.frontApplicationPage, component: ApplicationComponent, children: [
      {path: environment.employeeManagement, component: EmployeeManagementComponent},
      {path: environment.establishmentManagement, component: EstablishmentManagementComponent}
    ]
  },
  {path: '', redirectTo: environment.frontLoginPage, pathMatch: 'full'}];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ApplicationComponent,
    LangageSelectionComponent,
    EmployeeManagementComponent,
    EstablishmentManagementComponent,
    SearchBarComponent,
    InformationTicketComponent,
    UnwrapArrowComponent,
    EmployeeInformationUpdaterComponent,
    EmployeeCreatorComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    ReactiveFormsModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  exports: [RouterModule],
  providers: [AuthenticationService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpAuthenticationInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule {
}

export function HttpLoaderFactory(http: HttpClient): TranslateHttpLoader {
  return new TranslateHttpLoader(http);
}
