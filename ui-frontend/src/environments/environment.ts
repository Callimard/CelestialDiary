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
  companyManagement: "company/management",
  company: "company",
  management: "management",
  employees: "employees",
  employeeInformation: "information/:employeeId",
  clients: "clients",
  clientInformation: "information/:clientId",
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
  equipments: "equipments",
  equipmentInformation: "information/:equipmentId",
  internEstablishmentManagement: "establishment/:establishmentId",
  rooms: "rooms",
  create: "create"
}

export const backend = {
  companyAuthenticationIdentifier: "C",
  employeeAuthenticationIdentifier: "E",
  backendUrl: "http://192.168.1.14:8080",
  apiV1Url: "/api/v1",
  privilegesUrl: "/privileges",
  company: "/company",
  establishment: "/establishment",
  employeeLogin: "/authentication/employee/token",
  employeeRefreshToken: "/authentication/employee/token/refresh",
  companiesUrl: "/companies",
  employeesUrl: "/employees",
  clientsUrl: "/clients",
  establishmentsUrl: "/establishments",
  productsUrl: "/products",
  prestationsUrl: "/prestations",
  bundlesUrl: "/bundles",
  rolesUrl: "/roles",
  equipmentsUrl: "/equipments",
}

export const privileges = {
  company: {
    all: "COMPANY_ALL",
    employee: {
      all: "EMPLOYEE_ALL",
      read: "EMPLOYEE_READ",
      create: "EMPLOYEE_CREATE",
      update: "EMPLOYEE_UPDATE_INFORMATION",
      update_role: "EMPLOYEE_UPDATE_ROLE",
      assign: "EMPLOYEE_ASSIGN_TO",
      activate: "EMPLOYEE_ACTIVATE"
    },
    client: {
      all: "CLIENT_ALL",
      read: "CLIENT_READ",
      create: "CLIENT_CREATE",
      update: "CLIENT_UPDATE",
    },
    establishment: {
      all: "ESTABLISHMENT_ALL",
      read: "ESTABLISHMENT_READ",
      create: "ESTABLISHMENT_CREATE",
      update: "ESTABLISHMENT_UPDATE",
      activate: "ESTABLISHMENT_ACTIVATE"
    },
    saleable: {
      all: "SALEABLE_ALL",
      read: "SALEABLE_READ",
      create: "SALEABLE_CREATE",
      update: "SALEABLE_UPDATE",
      activate: "SALEABLE_ACTIVATE"
    },
    role: {
      all: "ROLE_ALL",
      read: "ROLE_READ",
      create: "ROLE_CREATE",
      update: "ROLE_UPDATE",
      delete: "ROLE_DELETE"
    },
    equipment: {
      all: "EQUIPMENT_ALL",
      read: "EQUIPMENT_READ",
      create: "EQUIPMENT_CREATE",
      update: "EQUIPMENT_UPDATE",
      delete: "EQUIPMENT_DELETE"
    }
  },
  establishment: {
    all: "ESTABLISHMENT_ALL",
    room: {
      all: "ROOM_ALL",
      read: "ROOM_READ",
      create: "ROOM_CREATE",
      update: "ROOM_UPDATE",
      delete: "ROOM_DELETE",
    },
    equipment: {
      all: "ESTABLISHMENT_EQUIPMENT_all",
      read: "ESTABLISHMENT_EQUIPMENT_READ",
      add: "ESTABLISHMENT_EQUIPMENT_ADD",
      update: "ESTABLISHMENT_EQUIPMENT_UPDATE",
      delete: "ESTABLISHMENT_EQUIPMENT_DELETE",
    }
  }
}
