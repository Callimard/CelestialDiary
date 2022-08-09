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
import {SearchBarComponent} from './library/listing/search-bar/search-bar.component';
import {HttpAuthenticationInterceptor} from "../service/authentication/http-authentication.interceptor";
import {EmployeeFormUpdaterComponent} from './application/employee-management/employee-information/employee-form-updater/employee-form-updater.component';
import {EmployeeFormCreatorComponent} from './application/employee-management/employee-creation/employee-form-creator/employee-form-creator.component';
import {EstablishmentFormCreatorComponent} from './application/establishment-management/establishment-creation/establishment-form-creator/establishment-form-creator.component';
import {EstablishmentFormUpdaterComponent} from './application/establishment-management/establishment-information/establishment-information-updater/establishment-form-updater.component';
import {TimeIntervalFormComponent} from './library/form/time-interval-form/time-interval-form.component';
import {ProductSelectionComponent} from './application/product-management/product-selection/product-selection.component';
import {ProductCreationComponent} from './application/product-management/product-creation/product-creation.component';
import {ProductInformationComponent} from './application/product-management/product-information/product-information.component';
import {ElementListComponent} from './library/listing/element-list/element-list.component';
import {SearchingListComponent} from './library/listing/searching-list/searching-list.component';
import {PrestationSelectionComponent} from './application/prestation-management/prestation-selection/prestation-selection.component';
import {PrestationInformationComponent} from './application/prestation-management/prestation-information/prestation-information.component';
import {PrestationCreationComponent} from './application/prestation-management/prestation-creation/prestation-creation.component';
import {BundleSelectionComponent} from './application/bundle-management/bundle-selection/bundle-selection.component';
import {BundleInformationComponent} from './application/bundle-management/bundle-information/bundle-information.component';
import {BundleCreationComponent} from './application/bundle-management/bundle-creation/bundle-creation.component';
import {BasicTitleComponent} from './library/informative/title/basic-title/basic-title.component';
import {ProductFormCreatorComponent} from './application/product-management/product-creation/product-form-creator/product-form-creator.component';
import {BasicInputFormComponent} from './library/form/basic-input-form/basic-input-form.component';
import {AdvancedTitleComponent} from './library/informative/title/advanced-title/advanced-title.component';
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
import {RoleFormUpdaterComponent} from './application/role-management/role-information/role-form-updater/role-form-updater.component';
import {RoleFormContentComponent} from './application/role-management/utils/role-form-content/role-form-content.component';
import {FloatingCardButtonComponent} from './library/button/floating-card-button/floating-card-button.component';
import {EmployeeFormContentComponent} from './application/employee-management/utils/employee-form-content/employee-form-content.component';
import {EmployeeRoleSelectionComponent} from './application/employee-management/utils/employee-role-selection/employee-role-selection.component';
import {EmployeeRoleUpdaterComponent} from './application/employee-management/employee-information/employee-role-updater/employee-role-updater.component';
import {AdvancedBasicInputFormComponent} from './library/form/advanced-basic-input-form/advanced-basic-input-form.component';
import {BundleFormContentComponent} from './application/bundle-management/utils/bundle-form-content/bundle-form-content.component';
import {EquipmentSelectionComponent} from './application/equipment-management/equipment-selection/equipment-selection.component';
import {EquipmentInformationComponent} from './application/equipment-management/equipment-information/equipment-information.component';
import {EquipmentCreationComponent} from './application/equipment-management/equipment-creation/equipment-creation.component';
import {EquipmentFormCreatorComponent} from './application/equipment-management/equipment-creation/equipment-form-creator/equipment-form-creator.component';
import {EquipmentFormContentComponent} from './application/equipment-management/utils/equipment-form-content/equipment-form-content.component';
import {EquipmentFormUpdaterComponent} from './application/equipment-management/equipment-information/equipment-form-updater/equipment-form-updater.component';
import {PrestationFormContentComponent} from './application/prestation-management/utils/prestation-form-content/prestation-form-content.component';
import {EmployeePrestationSelectionComponent} from './application/employee-management/utils/employee-prestation-selection/employee-prestation-selection.component';
import {EmployeeEstablishmentAssignationComponent} from './application/employee-management/employee-information/employee-establishment-assignation/employee-establishment-assignation.component';
import {CheckBoxDetailsSummaryComponent} from './library/form/check-box-details-summary/check-box-details-summary.component';
import {EstablishmentFormContentComponent} from './application/establishment-management/utils/establishment-form-content/establishment-form-content.component';
import {ClientSelectionComponent} from './application/client-management/client-selection/client-selection.component';
import {ClientInformationComponent} from './application/client-management/client-information/client-information.component';
import {ClientCreationComponent} from './application/client-management/client-creation/client-creation.component';
import {ClientFormCreatorComponent} from './application/client-management/client-creation/client-form-creator/client-form-creator.component';
import {ClientFormContentComponent} from './application/client-management/utils/client-form-content/client-form-content.component';
import {ClientFormUpdaterComponent} from './application/client-management/client-information/client-form-updater/client-form-updater.component';
import {EmployeeWorkingHoursUpdaterComponent} from './application/employee-management/employee-information/employee-working-hours-updater/employee-working-hours-updater.component';
import {EmployeeEstablishmentWorkingHoursComponent} from './application/employee-management/employee-information/employee-working-hours-updater/employee-establishment-working-hours/employee-establishment-working-hours.component';
import {WeekSelectionComponent} from './library/informative/week-selection/week-selection.component';
import {DatePipe} from "@angular/common";
import {DayHoursFormComponent} from './library/form/day-hours-form/day-hours-form.component';
import {InfoPaneComponent} from './library/informative/info-pane/info-pane.component';
import {EmployeeContainerComponent} from './application/employee-management/employee-container/employee-container.component';
import {TwoSideManagementComponent} from './library/container/two-side-management/two-side-management.component';
import {SelectCreateInformationContainerComponent} from './library/container/select-create-information-container/select-create-information-container.component';
import {BundleContainerComponent} from './application/bundle-management/bundle-container/bundle-container.component';
import {ClientContainerComponent} from './application/client-management/client-container/client-container.component';
import {EquipmentContainerComponent} from './application/equipment-management/equipment-container/equipment-container.component';
import {EstablishmentContainerComponent} from './application/establishment-management/establishment-container/establishment-container.component';
import {ProductContainerComponent} from './application/product-management/product-container/product-container.component';
import {PrestationContainerComponent} from './application/prestation-management/prestation-container/prestation-container.component';
import {RoleContainerComponent} from './application/role-management/role-container/role-container.component';
import { PrivilegeTableComponent } from './application/role-management/utils/privilege-table/privilege-table.component';
import { SideBarComponent } from './library/side-bar/side-bar.component';
import { NavItemComponent } from './library/side-bar/nav-item/nav-item.component';
import { CompanyManagementNavigationComponent } from './library/side-bar/company-management-navigation/company-management-navigation.component';
import { NavigationZoneComponent } from './library/side-bar/navigation-zone/navigation-zone.component';
import { DetailsSummaryComponent } from './library/container/details-summary/details-summary.component';
import { EstablishmentManagementNavigationComponent } from './library/side-bar/establishment-management-navigation/establishment-management-navigation.component';

const appRoutes: Routes = [
  {path: frontend.login, component: LoginComponent},
  {
    path: frontend.application, component: ApplicationComponent, children: [
      {
        path: frontend.companyManagementPrivileges, children: [
          {
            path: frontend.employees, component: EmployeeContainerComponent
          },
          {
            path: frontend.clients, component: ClientContainerComponent
          },
          {
            path: frontend.establishments, component: EstablishmentContainerComponent
          },
          {
            path: frontend.products, component: ProductContainerComponent
          },
          {
            path: frontend.prestations, component: PrestationContainerComponent
          },
          {
            path: frontend.bundles, component: BundleContainerComponent
          },
          {
            path: frontend.roles, component: RoleContainerComponent
          },
          {
            path: frontend.equipments, component: EquipmentContainerComponent
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
    EmployeeFormUpdaterComponent,
    EmployeeFormCreatorComponent,
    EstablishmentFormCreatorComponent,
    EstablishmentFormUpdaterComponent,
    TimeIntervalFormComponent,
    ProductSelectionComponent,
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
    CheckBoxDetailsSummaryComponent,
    EstablishmentFormContentComponent,
    ClientSelectionComponent,
    ClientInformationComponent,
    ClientCreationComponent,
    ClientFormCreatorComponent,
    ClientFormContentComponent,
    ClientFormUpdaterComponent,
    EmployeeWorkingHoursUpdaterComponent,
    EmployeeEstablishmentWorkingHoursComponent,
    WeekSelectionComponent,
    DayHoursFormComponent,
    InfoPaneComponent,
    EmployeeContainerComponent,
    TwoSideManagementComponent,
    SelectCreateInformationContainerComponent,
    BundleContainerComponent,
    ClientContainerComponent,
    EquipmentContainerComponent,
    EstablishmentContainerComponent,
    ProductContainerComponent,
    PrestationContainerComponent,
    RoleContainerComponent,
    PrivilegeTableComponent,
    SideBarComponent,
    NavItemComponent,
    CompanyManagementNavigationComponent,
    NavigationZoneComponent,
    DetailsSummaryComponent,
    EstablishmentManagementNavigationComponent
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
  providers: [DatePipe,
    AuthenticationService,
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
