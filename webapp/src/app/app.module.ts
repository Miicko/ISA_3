import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserService } from './service/user-service.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserFormComponent } from './user-form/user-form.component';
import { NavMenuComponent } from './nav-menu/nav-menu.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AuthGuardService } from './service/auth-guard.service';
import { JwtInterceptorService } from './service/jwt-interceptor.service';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserLogoutComponent } from './user-logout/user-logout.component';
import { CompanyListComponent } from './company-list/company-list.component';
import { EquipmentListComponent } from './equipment-list/equipment-list.component';
import { MatSlider, MatSliderModule } from '@angular/material/slider';
import { AppointmentListComponent } from './appointment-list/appointment-list.component';
import { UsersAppointmentsComponent } from './users-appointments/users-appointments.component';
import { ProfileComponent } from './profile/profile.component';
import { ComplaintListComponent } from './complaint-list/complaint-list.component';
import { ComplaintFormComponent } from './complaint-form/complaint-form.component';


@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserFormComponent,
    NavMenuComponent,
    UserLoginComponent,
    UserLogoutComponent,
    CompanyListComponent,
    EquipmentListComponent,
    AppointmentListComponent,
    UsersAppointmentsComponent,
    ProfileComponent,
    ComplaintListComponent,
    ComplaintFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatSliderModule
  ],
  providers: [UserService, provideAnimationsAsync('noop'),
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptorService, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
