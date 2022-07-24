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
import {SearchBarComponent} from './libairy/search-bar/search-bar.component';
import {InformationTicketComponent} from './libairy/information-ticket/information-ticket.component';
import {HttpAuthenticationInterceptor} from "../service/authentication/http-authentication.interceptor";
import {UnwrapArrowComponent} from './libairy/unwrap-arrow/unwrap-arrow.component';
import {EmployeeFormUpdaterComponent} from './application/employee-management/employee-information/employee-information-updater/employee-form-updater.component';
import {EmployeeFormCreatorComponent} from './application/employee-management/employee-creation/employee-form-creator/employee-form-creator.component';
import {EstablishmentFormCreatorComponent} from './application/establishment-management/establishment-creation/establishment-form-creator/establishment-form-creator.component';
import {EstablishmentFormUpdaterComponent} from './application/establishment-management/establishment-information/establishment-information-updater/establishment-form-updater.component';
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
import {TitleCardComponent} from './libairy/title-card/title-card.component';
import {ProductFormCreatorComponent} from './application/product-management/product-creation/product-form-creator/product-form-creator.component';
import {BasicInputFormComponent} from './libairy/form/basic-input-form/basic-input-form.component';
import {AdvancedTitleCardComponent} from './libairy/advanced-title-card/advanced-title-card.component';
import {ProductFormUpdaterComponent} from './application/product-management/product-information/product-form-updater/product-form-updater.component';
import {PrestationFormCreatorComponent} from './application/prestation-management/prestation-creation/prestation-form-creator/prestation-form-creator.component';
import {PrestationFormUpdaterComponent} from './application/prestation-management/prestation-information/prestation-form-updater/prestation-form-updater.component';
import {BundleFormCreatorComponent} from './application/bundle-management/bundle-creation/bundle-form-creator/bundle-form-creator.component';
import {BundleFormUpdaterComponent} from './application/bundle-management/bundle-information/bundle-form-updater/bundle-form-updater.component';
import {EstablishmentSelectionComponent} from './application/establishment-management/establishment-selection/establishment-selection.component';
import {EstablishmentCreationComponent} from './application/establishment-management/establishment-creation/establishment-creation.component';
import {EstablishmentInformationComponent} from './application/establishment-management/establishment-information/establishment-information.component';
import {EmployeeSelectionComponent} from './application/employee-management/employee-selection/employee-selection.component';
import {EmployeeCreationComponent} from './application/employee-management/employee-creation/employee-creation.component';
import {EmployeeInformationComponent} from './application/employee-management/employee-information/employee-information.component';

const appRoutes: Routes = [
  {path: frontend.login, component: LoginComponent},
  {
    path: frontend.application, component: ApplicationComponent, children: [
      {
        path: frontend.companyManagement, children: [
          {
            path: frontend.employees, component: TwoSideComponent, children: [
              {path: '', component: EmployeeSelectionComponent},
              {path: frontend.employeeInformation, component: EmployeeInformationComponent, outlet: 'right'},
              {path: frontend.create, component: EmployeeCreationComponent, outlet: 'right'}
            ]
          },
          {
            path: frontend.establishments, component: TwoSideComponent, children: [
              {path: '', component: EstablishmentSelectionComponent},
              {path: frontend.establishmentInformation, component: EstablishmentInformationComponent, outlet: 'right'},
              {path: frontend.create, component: EstablishmentCreationComponent, outlet: 'right'}
            ]
          },
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
    SearchBarComponent,
    InformationTicketComponent,
    UnwrapArrowComponent,
    EmployeeFormUpdaterComponent,
    EmployeeFormCreatorComponent,
    EstablishmentFormCreatorComponent,
    EstablishmentFormUpdaterComponent,
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
    BundleFormUpdaterComponent,
    EstablishmentSelectionComponent,
    EstablishmentCreationComponent,
    EstablishmentInformationComponent,
    EmployeeSelectionComponent,
    EmployeeCreationComponent,
    EmployeeInformationComponent
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
