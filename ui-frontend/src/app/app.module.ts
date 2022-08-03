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
import {SearchBarComponent} from './libairy/listing/search-bar/search-bar.component';
import {InformationTicketComponent} from './libairy/informative/information-ticket/information-ticket.component';
import {HttpAuthenticationInterceptor} from "../service/authentication/http-authentication.interceptor";
import {EmployeeFormUpdaterComponent} from './application/employee-management/employee-information/employee-information-updater/employee-form-updater.component';
import {EmployeeFormCreatorComponent} from './application/employee-management/employee-creation/employee-form-creator/employee-form-creator.component';
import {EstablishmentFormCreatorComponent} from './application/establishment-management/establishment-creation/establishment-form-creator/establishment-form-creator.component';
import {EstablishmentFormUpdaterComponent} from './application/establishment-management/establishment-information/establishment-information-updater/establishment-form-updater.component';
import {TimeIntervalFormComponent} from './libairy/form/time-interval-form/time-interval-form.component';
import {ProductSelectionComponent} from './application/product-management/product-selection/product-selection.component';
import {TwoSideComponent} from './libairy/container/two-side/two-side.component';
import {ProductCreationComponent} from './application/product-management/product-creation/product-creation.component';
import {ProductInformationComponent} from './application/product-management/product-information/product-information.component';
import {ElementListComponent} from './libairy/listing/element-list/element-list.component';
import {SearchingListComponent} from './libairy/listing/searching-list/searching-list.component';
import {PrestationSelectionComponent} from './application/prestation-management/prestation-selection/prestation-selection.component';
import {PrestationInformationComponent} from './application/prestation-management/prestation-information/prestation-information.component';
import {PrestationCreationComponent} from './application/prestation-management/prestation-creation/prestation-creation.component';
import {BundleSelectionComponent} from './application/bundle-management/bundle-selection/bundle-selection.component';
import {BundleInformationComponent} from './application/bundle-management/bundle-information/bundle-information.component';
import {BundleCreationComponent} from './application/bundle-management/bundle-creation/bundle-creation.component';
import {BasicTitleComponent} from './libairy/informative/title/basic-title/basic-title.component';
import {ProductFormCreatorComponent} from './application/product-management/product-creation/product-form-creator/product-form-creator.component';
import {BasicInputFormComponent} from './libairy/form/basic-input-form/basic-input-form.component';
import {AdvancedTitleComponent} from './libairy/informative/title/advanced-title/advanced-title.component';
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
import {RoleSelectionComponent} from './application/role-management/role-selection/role-selection.component';
import {RoleInformationComponent} from './application/role-management/role-information/role-information.component';
import {RoleCreationComponent} from './application/role-management/role-creation/role-creation.component';
import {RoleFormCreatorComponent} from './application/role-management/role-creation/role-form-creator/role-form-creator.component';
import {PrivilegeInputLineComponent} from './application/role-management/utils/privilege-input-line/privilege-input-line.component';
import {RoleFormUpdaterComponent} from './application/role-management/role-information/role-form-updater/role-form-updater.component';
import {RoleFormContentComponent} from './application/role-management/utils/role-form-content/role-form-content.component';
import {FloatingCardButtonComponent} from './libairy/button/floating-card-button/floating-card-button.component';
import {EmployeeFormContentComponent} from './application/employee-management/utils/employee-form-content/employee-form-content.component';
import {EmployeeRoleSelectionComponent} from './application/employee-management/utils/employee-role-selection/employee-role-selection.component';
import {EmployeeRoleUpdaterComponent} from './application/employee-management/employee-information/employee-role-updater/employee-role-updater.component';
import {AdvancedBasicInputFormComponent} from './libairy/form/advanced-basic-input-form/advanced-basic-input-form.component';
import {BundleFormContentComponent} from './application/bundle-management/utils/bundle-form-content/bundle-form-content.component';
import {EquipmentSelectionComponent} from './application/equipment-management/equipment-selection/equipment-selection.component';
import {EquipmentInformationComponent} from './application/equipment-management/equipment-information/equipment-information.component';
import {EquipmentCreationComponent} from './application/equipment-management/equipment-creation/equipment-creation.component';
import { EquipmentFormCreatorComponent } from './application/equipment-management/equipment-creation/equipment-form-creator/equipment-form-creator.component';
import { EquipmentFormContentComponent } from './application/equipment-management/utils/equipment-form-content/equipment-form-content.component';
import { EquipmentFormUpdaterComponent } from './application/equipment-management/equipment-information/equipment-form-updater/equipment-form-updater.component';
import { PrestationFormContentComponent } from './application/prestation-management/utils/prestation-form-content/prestation-form-content.component';
import { EmployeePrestationSelectionComponent } from './application/employee-management/utils/employee-prestation-selection/employee-prestation-selection.component';
import { EmployeeEstablishmentAssignationComponent } from './application/employee-management/employee-information/employee-establishment-assignation/employee-establishment-assignation.component';
import { CheckBoxDetailsSummaryComponent } from './libairy/form/check-box-details-summary/check-box-details-summary.component';

const appRoutes: Routes = [
  {path: frontend.login, component: LoginComponent},
  {
    path: frontend.application, component: ApplicationComponent, children: [
      {
        path: frontend.companyManagementPrivileges, children: [
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
          },
          {
            path: frontend.roles, component: TwoSideComponent, children: [
              {path: '', component: RoleSelectionComponent},
              {path: frontend.roleInformation, component: RoleInformationComponent, outlet: 'right'},
              {path: frontend.create, component: RoleCreationComponent, outlet: 'right'}
            ]
          },
          {
            path: frontend.equipments, component: TwoSideComponent, children: [
              {path: '', component: EquipmentSelectionComponent},
              {path: frontend.equipmentInformation, component: EquipmentInformationComponent, outlet: 'right'},
              {path: frontend.create, component: EquipmentCreationComponent, outlet: 'right'}
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
    EmployeeFormUpdaterComponent,
    EmployeeFormCreatorComponent,
    EstablishmentFormCreatorComponent,
    EstablishmentFormUpdaterComponent,
    TimeIntervalFormComponent,
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
    BasicTitleComponent,
    ProductFormCreatorComponent,
    BasicInputFormComponent,
    AdvancedTitleComponent,
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
    EmployeeInformationComponent,
    RoleSelectionComponent,
    RoleInformationComponent,
    RoleCreationComponent,
    RoleFormCreatorComponent,
    PrivilegeInputLineComponent,
    RoleFormUpdaterComponent,
    RoleFormContentComponent,
    FloatingCardButtonComponent,
    EmployeeFormContentComponent,
    EmployeeRoleSelectionComponent,
    EmployeeRoleUpdaterComponent,
    AdvancedBasicInputFormComponent,
    BundleFormContentComponent,
    EquipmentSelectionComponent,
    EquipmentInformationComponent,
    EquipmentCreationComponent,
    EquipmentFormCreatorComponent,
    EquipmentFormContentComponent,
    EquipmentFormUpdaterComponent,
    PrestationFormContentComponent,
    EmployeePrestationSelectionComponent,
    EmployeeEstablishmentAssignationComponent,
    CheckBoxDetailsSummaryComponent
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
