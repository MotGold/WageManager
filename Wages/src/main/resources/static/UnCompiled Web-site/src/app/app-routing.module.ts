import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminsComponent } from './components/admins/admins.component';
import { AllEmployeesComponent } from './components/all-employees/all-employees.component';
import { EmployeeByDepartmentComponent } from './components/employee-by-department/employee-by-department.component';
import { EmployeeByDepatment2Component } from './components/employee-by-depatment2/employee-by-depatment2.component';
import { EmployeeByIdComponent } from './components/employee-by-id/employee-by-id.component';
import { EmployeeById2Component } from './components/employee-by-id2/employee-by-id2.component';
import { EmployeeByNameComponent } from './components/employee-by-name/employee-by-name.component';
import { EmployeeByName2Component } from './components/employee-by-name2/employee-by-name2.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { LayedOffEmployeesComponent } from './components/layed-off-employees/layed-off-employees.component';
import { ArchiveByDepartmentComponent } from './components/layedOffComponents/archive-by-department/archive-by-department.component';
import { ArchiveByDepartment2Component } from './components/layedOffComponents/archive-by-department2/archive-by-department2.component';
import { ArchiveByIdComponent } from './components/layedOffComponents/archive-by-id/archive-by-id.component';
import { ArchiveById2Component } from './components/layedOffComponents/archive-by-id2/archive-by-id2.component';
import { ArchiveByNameComponent } from './components/layedOffComponents/archive-by-name/archive-by-name.component';
import { ArchiveByName2Component } from './components/layedOffComponents/archive-by-name2/archive-by-name2.component';
import { EmployeeArchiveComponent } from './components/layedOffComponents/employee-archive/employee-archive.component';
import { LoginComponent } from './components/login/login.component';


const routes: Routes = [
  { path: "login", component: LoginComponent },
  {
    path: "employees", component: AllEmployeesComponent,
    children: [
      { path: "list", component: EmployeeListComponent },
      { path: "byId", component: EmployeeByIdComponent },
      { path: "byId2", component: EmployeeById2Component },
      { path: "byName", component: EmployeeByNameComponent },
      { path: "byName2", component: EmployeeByName2Component },
      { path: "bydepartment", component: EmployeeByDepartmentComponent },
      { path: "bydepartment2", component: EmployeeByDepatment2Component },
    ]
  },
  { path: "admins", component: AdminsComponent },
  {
    path: "old", component: LayedOffEmployeesComponent,
    children: [
      { path: "archive", component: EmployeeArchiveComponent },
      { path: "byId", component: ArchiveByIdComponent },
      { path: "byId2", component: ArchiveById2Component },
      { path: "byName", component: ArchiveByNameComponent },
      { path: "byName2", component: ArchiveByName2Component },
      { path: "bydepartment", component: ArchiveByDepartmentComponent },
      { path: "bydepartment2", component: ArchiveByDepartment2Component }
    ]
  },
  { path: "", redirectTo: "login", pathMatch: "full" },
  { path: "**", component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
