import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './user-list/user-list.component';
import { UserFormComponent } from './user-form/user-form.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { AuthGuardService } from './service/auth-guard.service';
import { UserLogoutComponent } from './user-logout/user-logout.component';
import { CompanyListComponent } from './company-list/company-list.component';
import { EquipmentListComponent } from './equipment-list/equipment-list.component';
import { AppointmentListComponent } from './appointment-list/appointment-list.component';

const routes: Routes = [
  { path: 'users', component: UserListComponent, canActivate: [AuthGuardService] },
  { path: 'signup', component: UserFormComponent },
  { path: 'login', component: UserLoginComponent },
  { path: 'logout', component: UserLogoutComponent },
  { path: 'companies', component: CompanyListComponent },
  { path: 'equipments', component: EquipmentListComponent },
  { path: 'reserveAppointment', component: AppointmentListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
