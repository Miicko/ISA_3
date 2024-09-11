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
import { UsersAppointmentsComponent } from './users-appointments/users-appointments.component';
import { ComplaintListComponent } from './complaint-list/complaint-list.component';
import { ComplaintFormComponent } from './complaint-form/complaint-form.component';

const routes: Routes = [
  { path: 'users', component: UserListComponent, canActivate: [AuthGuardService] },
  { path: 'signup', component: UserFormComponent },
  { path: 'login', component: UserLoginComponent },
  { path: 'logout', component: UserLogoutComponent },
  { path: 'companies', component: CompanyListComponent },
  { path: 'equipments', component: EquipmentListComponent , canActivate: [AuthGuardService]},
  { path: 'reserveAppointment', component: AppointmentListComponent , canActivate: [AuthGuardService]},
  { path: 'userAppointments', component: UsersAppointmentsComponent , canActivate: [AuthGuardService]},
  { path: 'userComplaints', component: ComplaintListComponent , canActivate: [AuthGuardService]},
  { path: 'leaveComplaint', component: ComplaintFormComponent , canActivate: [AuthGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
