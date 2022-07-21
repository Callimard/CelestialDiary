// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  frontLoginPage: "login",
  frontApplicationPage: "application",
  employeeManagement: "employees",
  establishmentManagement: "establishments",
  productManagement: "products",
  langageFr: "fr",
  langageEn: "en",
  bigScreenWidth: 1280,
};

export const frontend = {
  login: "login",
  application: "application",
  companyManagement: "company/management",
  products: "products",
  productInformation: "information/:productId",
  prestations: "prestations",
  prestationInformation: "information/:prestationId",
  bundles: "bundles",
  bundleInformation: "information/:bundleId",
  create: "create"
}

export const backend = {
  companyAuthenticationIdentifier: "C",
  employeeAuthenticationIdentifier: "E",
  backendUrl: "http://192.168.1.14:8080",
  apiV1Url: "/api/v1",
  employeeLogin: "/authentication/employee/token",
  employeeRefreshToken: "/authentication/employee/token/refresh",
  companiesUrl: "/companies",
  employeesUrl: "/employees",
  establishmentUrl: "/establishments",
  productUrl: "/products",
  prestationUrl: "/prestations",
  bundleUrl: "/bundles"
}
