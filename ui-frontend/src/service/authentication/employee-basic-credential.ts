export class EmployeeBasicCredential {

  constructor(private companyName: string, private employeeEmail: string, private employeePassword: string) {
  }

  public toBasicCredentials(): string {
    return "Basic " + btoa(this.companyName + "|" + this.employeeEmail + ":" + this.employeePassword);
  }
}
