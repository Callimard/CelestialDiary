import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from './login/login.component';
import {ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {AuthenticationService} from "../service/authentication/authentication.service";
import {ApplicationComponent} from './application/application.component';
import {environment, frontend} from "../environments/environment";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import {LangageSelectionComponent} from './langage-selection/langage-selection.component';
import {EmployeeManagementComponent} from './application/employee-management/employee-management.component';
import {EstablishmentManagementComponent} from './application/establishment-management/establishment-management.component';
import {SearchBarComponent} from './libairy/search-bar/search-bar.component';
import {InformationTicketComponent} from './libairy/information-ticket/information-ticket.component';
import {HttpAuthenticationInterceptor} from "../service/authentication/http-authentication.interceptor";
import {UnwrapArrowComponent} from './libairy/unwrap-arrow/unwrap-arrow.component';
import {EmployeeInformationUpdaterComponent} from './application/employee-management/employee-information-updater/employee-information-updater.component';
import {EmployeeCreatorComponent} from './application/employee-management/employee-creator/employee-creator.component';
import {EstablishmentSearchingListComponent} from './application/establishment-management/establishment-searching-list/establishment-searching-list.component';
import {EstablishmentCreatorComponent} from './application/establishment-management/establishment-creator/establishment-creator.component';
import {EstablishmentInformationUpdaterComponent} from './application/establishment-management/establishment-information-updater/establishment-information-updater.component';
import {TimeIntervalFormComponent} from './libairy/form/time-interval-form/time-interval-form.component';
import {SelectorComponent} from './libairy/selector/selector.component';
import {ProductSelectionComponent} from './application/product-management/product-selection/product-selection.component';
import {TwoSideComponent} from './libairy/two-side/two-side.component';
import {ProductCreationComponent} from './application/product-management/product-creation/product-creation.component';
import {ProductInformationComponent} from './application/product-management/product-information/product-information.component';
import {ElementListComponent} from './libairy/element-list/element-list.component';
import {SearchingListComponent} from './libairy/searching-list/searching-list.component';
import {PrestationSelectionComponent} from './application/prestation-management/prestation-selection/prestation-selection.component';
import {PrestationInformationComponent} from './application/prestation-management/prestation-information/prestation-information.component';
import {PrestationCreationComponent} from './application/prestation-management/prestation-creation/prestation-creation.component';
import {BundleSelectionComponent} from './application/bundle-management/bundle-selection/bundle-selection.component';
import {BundleInformationComponent} from './application/bundle-management/bundle-information/bundle-information.component';
import {BundleCreationComponent} from './application/bundle-management/bundle-creation/bundle-creation.component';
import { TitleCardComponent } from './libairy/title-card/title-card.component';
import { ProductFormCreatorComponent } from './application/product-management/product-creation/product-form-creator/product-form-creator.component';
import { BasicInputFormComponent } from './libairy/form/basic-input-form/basic-input-form.component';
import { AdvancedTitleCardComponent } from './libairy/advanced-title-card/advanced-title-card.component';
import { ProductFormUpdaterComponent } from './application/product-management/product-information/product-form-updater/product-form-updater.component';
import { PrestationFormCreatorComponent } from './application/prestation-management/prestation-creation/prestation-form-creator/prestation-form-creator.component';
import { PrestationFormUpdaterComponent } from './application/prestation-management/prestation-information/prestation-form-updater/prestation-form-updater.component';
import { BundleFormCreatorComponent } from './application/bundle-management/bundle-creation/bundle-form-creator/bundle-form-creator.component';
import { BundleFormUpdaterComponent } from './application/bundle-management/bundle-information/bundle-form-updater/bundle-form-updater.component';

const appRoutes: Routes = [
  {path: frontend.login, component: LoginComponent},
  {
    path: frontend.application, component: ApplicationComponent, children: [
      {path: environment.employeeManagement, component: EmployeeManagementComponent},
      {path: environment.establishmentManagement, component: EstablishmentManagementComponent},
      {
        path: frontend.companyManagement, children: [
          {
            path: frontend.products, component: TwoSideComponent, children: [
              {path: '', component: ProductSelectionComponent},
              {path: frontend.productInformation, component: ProductInformationComponent, outlet: 'right'},
              {path: frontend.create, component: ProductCreationComponent, outlet: 'right'}
            ],
          },
          {
            path: frontend.prestations, component: TwoSideComponent, children: [
              {path: '', component: PrestationSelectionComponent},
              {path: frontend.prestationInformation, component: PrestationInformationComponent, outlet: 'right'},
              {path: frontend.create, component: PrestationCreationComponent, outlet: 'right'}
            ]
          }, {
            path: frontend.bundles, component: TwoSideComponent, children: [
              {path: '', component: BundleSelectionComponent},
              {path: frontend.bundleInformation, component: BundleInformationComponent, outlet: 'right'},
              {path: frontend.create, component: BundleCreationComponent, outlet: 'right'}
            ]
          }
        ]
      }
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
    EmployeeCreatorComponent,
    EstablishmentSearchingListComponent,
    EstablishmentCreatorComponent,
    EstablishmentInformationUpdaterComponent,
    TimeIntervalFormComponent,
    SelectorComponent,
    ProductSelectionComponent,
    TwoSideComponent,
    ProductCreationComponent,
    ProductInformationComponent,
    ElementListComponent,
    SearchingListComponent,
    PrestationSelectionComponent,
    PrestationInformationComponent,
    PrestationCreationComponent,
    BundleSelectionComponent,
    BundleInformationComponent,
    BundleCreationComponent,
    TitleCardComponent,
    ProductFormCreatorComponent,
    BasicInputFormComponent,
    AdvancedTitleCardComponent,
    ProductFormUpdaterComponent,
    PrestationFormCreatorComponent,
    PrestationFormUpdaterComponent,
    BundleFormCreatorComponent,
    BundleFormUpdaterComponent
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
