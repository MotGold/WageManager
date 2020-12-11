import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AllEmployeesComponent } from './components/all-employees/all-employees.component';
import { EmployeeByIdComponent } from './components/employee-by-id/employee-by-id.component';
import { EmployeeByNameComponent } from './components/employee-by-name/employee-by-name.component';
import { EmployeeByDepartmentComponent } from './components/employee-by-department/employee-by-department.component';
import { LayedOffEmployeesComponent } from './components/layed-off-employees/layed-off-employees.component';
import { AdminsComponent } from './components/admins/admins.component';
import { LoginComponent } from './components/login/login.component';
import { FrameworkComponent } from './components/framework/framework.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { NewEmployeeComponent } from './components/new-employee/new-employee.component';
import { FormsModule } from '@angular/forms';
import { EmployeeById2Component } from './components/employee-by-id2/employee-by-id2.component';
import { EmployeeByName2Component } from './components/employee-by-name2/employee-by-name2.component';
import { EmployeeByDepatment2Component } from './components/employee-by-depatment2/employee-by-depatment2.component';
import { EmployeeArchiveComponent } from './components/layedOffComponents/employee-archive/employee-archive.component';
import { ArchiveByIdComponent } from './components/layedOffComponents/archive-by-id/archive-by-id.component';
import { ArchiveById2Component } from './components/layedOffComponents/archive-by-id2/archive-by-id2.component';
import { ArchiveByNameComponent } from './components/layedOffComponents/archive-by-name/archive-by-name.component';
import { ArchiveByName2Component } from './components/layedOffComponents/archive-by-name2/archive-by-name2.component';
import { ArchiveByDepartmentComponent } from './components/layedOffComponents/archive-by-department/archive-by-department.component';
import { ArchiveByDepartment2Component } from './components/layedOffComponents/archive-by-department2/archive-by-department2.component';



@NgModule({
  declarations: [
    AllEmployeesComponent,
    EmployeeByIdComponent,
    EmployeeByNameComponent,
    EmployeeByDepartmentComponent,
    LayedOffEmployeesComponent,
    AdminsComponent,
    LoginComponent,
    FrameworkComponent,
    EmployeeListComponent,
    NewEmployeeComponent,
    EmployeeById2Component,
    EmployeeByName2Component,
    EmployeeByDepatment2Component,
    EmployeeArchiveComponent,
    ArchiveByIdComponent,
    ArchiveById2Component,
    ArchiveByNameComponent,
    ArchiveByName2Component,
    ArchiveByDepartmentComponent,
    ArchiveByDepartment2Component
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [FrameworkComponent]
})
export class AppModule { }
