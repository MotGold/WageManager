import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AutherizedAdmin } from '../models/autherized-admin';
import { Employee } from '../models/employee';
import { History } from '../models/history';

@Injectable({
  providedIn: 'root'
})
export class MainControllerService {

  constructor(private client: HttpClient) { }

  public recieveAllAdmins(token: string) {
    return this.client.get<AutherizedAdmin[]>("http://localhost:8080/controller/admins/" + token);
  }

  public deleteAdmin(id: number, token: string) {
    return this.client.delete("http://localhost:8080/controller/admins/delete/" + id + "/" + token);
  }

  public postAdmin(token: string, admin: AutherizedAdmin) {
    return this.client.post<AutherizedAdmin>("http://localhost:8080/controller/addAdmin/" + token, admin);
  }

  public recieveAllEmployees(token: string) {
    return this.client.get<Employee[]>("http://localhost:8080/controller/employees/" + token);
  }

  public postEmployee(department: number, token: string, emp: Employee) {
    return this.client.post<Employee>("http://localhost:8080/controller/addEmployee/" + department + "/" + token, emp);
  }

  public fireEmployee(id: number, token: string) {
    return this.client.delete("http://localhost:8080/controller/employees/fire/" + id + "/" + token);
  }

  public editEmployee(oldId: number, department: number, token: string, emp: Employee) {
    return this.client.post<Employee>("http://localhost:8080/controller/editEmployee/" + oldId + "/" + department + "/" + token, emp);
  }

  public payEmpolyee(id: number, token: string) {
    return this.client.get<number>("http://localhost:8080/controller/pay/" + id + "/" + token);
  }

  public historyOfEmployee(id: number, token: string) {
    return this.client.get<History[]>("http://localhost:8080/controller/history/" + id + "/" + token);
  }

  public payAllEmpolyees(token: string) {
    return this.client.get("http://localhost:8080/controller/payAll/" + token, { responseType: 'text' });
  }

  public findEmployee(id: number, token: string) {
    return this.client.get<Employee>("http://localhost:8080/controller/employees/" + id + "/" + token);
  }

  public findEmployeesByName(first: string, last: string, token: string) {
    return this.client.get<Employee[]>("http://localhost:8080/controller/employees/" + first + "/" + last + "/" + token);
  }

  public findEmployeesByDepartment(type: number, token: string) {
    return this.client.get<Employee[]>("http://localhost:8080/controller/employeesDep/" + type + "/" + token);
  }

}
