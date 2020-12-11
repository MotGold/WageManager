import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ArchivedEmployee } from '../models/archived-employee';
import { ArchivedHistory } from '../models/archived-history';

@Injectable({
  providedIn: 'root'
})
export class ArchiveControllerService {

  constructor(private client: HttpClient) { }

  public historyOfArchivedEmployee(id: number, token: string) {
    return this.client.get<ArchivedHistory[]>("http://localhost:8080/controller/archivedHistory/" + id + "/" + token);
  }

  public recieveAllArchivedEmployees(token: string) {
    return this.client.get<ArchivedEmployee[]>("http://localhost:8080/controller/archivedEmployees/" + token);
  }

  public editArchivedEmployee(oldId: number, department: number, token: string, emp: ArchivedEmployee) {
    return this.client.post<ArchivedEmployee>("http://localhost:8080/controller/editArchivedEmployee/" + oldId + "/" + department + "/" + token, emp);
  }

  public deleteEmployee(id: number, token: string) {
    return this.client.delete("http://localhost:8080/controller/archivedEmployees/delete/" + id + "/" + token);
  }

  public findEmployee(id: number, token: string) {
    return this.client.get<ArchivedEmployee>("http://localhost:8080/controller/archivedEmployees/" + id + "/" + token);
  }

  public findEmployeesByName(first: string, last: string, token: string) {
    return this.client.get<ArchivedEmployee[]>("http://localhost:8080/controller/archivedEmployees/" + first + "/" + last + "/" + token);
  }

  public findEmployeesByDepartment(type: number, token: string) {
    return this.client.get<ArchivedEmployee[]>("http://localhost:8080/controller/archivedDepartment/" + type + "/" + token);
  }
}
