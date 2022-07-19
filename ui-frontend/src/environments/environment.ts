// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  frontLoginPage: "login",
  frontApplicationPage: "application",
  backendUrl: "http://192.168.1.14:8080",
  apiV1Url: "/api/v1",
  companiesUrl: "/companies",
  companyAuthenticationIdentifier: "C",
  employeeAuthenticationIdentifier: "E",
  employeeLogin: "/authentication/employee/token",
  employeeRefreshToken: "/authentication/employee/token/refresh",
  employeeManagement: "employees",
  employeesUrl: "/employees",
  establishmentManagement: "establishments",
  langageFr: "fr",
  langageEn: "en",
  bigScreenWidth: 1280,
};
