// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  frontLoginPage: "login",
  frontApplicationPage: "application",
  productManagement: "products",
  langageFr: "fr",
  langageEn: "en",
  bigScreenWidth: 1280,
};

export const frontend = {
  login: "login",
  application: "application",
  companyManagementPrivileges: "company/management",
  company: "company",
  management: "management",
  employees: "employees",
  employeeInformation: "information/:employeeId",
  establishments: "establishments",
  establishmentInformation: "information/:establishmentId",
  products: "products",
  productInformation: "information/:productId",
  prestations: "prestations",
  prestationInformation: "information/:prestationId",
  bundles: "bundles",
  bundleInformation: "information/:bundleId",
  roles: "roles",
  roleInformation: "information/:roleId",
  create: "create"
}

export const backend = {
  companyAuthenticationIdentifier: "C",
  employeeAuthenticationIdentifier: "E",
  backendUrl: "http://192.168.1.14:8080",
  apiV1Url: "/api/v1",
  privilegesUrl: "/privileges",
  company: "/company",
  employeeLogin: "/authentication/employee/token",
  employeeRefreshToken: "/authentication/employee/token/refresh",
  companiesUrl: "/companies",
  employeesUrl: "/employees",
  establishmentsUrl: "/establishments",
  productsUrl: "/products",
  prestationsUrl: "/prestations",
  bundlesUrl: "/bundles",
  rolesUrl: "/roles"
}
