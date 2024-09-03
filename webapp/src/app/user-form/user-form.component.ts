import { Component, OnInit, NgModule } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../service/user-service.service';
import { User } from '../model/user';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrl: './user-form.component.css',
})
export class UserFormComponent {
  user: User;
  createUserForm: FormGroup;
  submitted = false;
  passwordsnotmatching = true;

  constructor(
    private route: ActivatedRoute, 
    private router: Router, 
    private userService: UserService,
    private formBuilder: FormBuilder,
  ) 
  {
    this.user = new User()
  }

  ngOnInit() {
    this.createUserForm = this.formBuilder.group(
      {
        email: ['', Validators.required],
        firstname: ['', Validators.required],
        lastname: ['', [Validators.required]],
        password: ['', Validators.required],
        password2: ['', Validators.required],
        phonenumber: ['', Validators.required],
        city: ['', [Validators.required]],
        country: ['', Validators.required],
        occupation: ['', Validators.required],
        companyinfo: ['', [Validators.required]],
      },

    );
    console.log("ngoninit");
  }

  get f() {
    return this.createUserForm.controls;
  }

  onSubmit() {
    console.log("submit clicked");
    this.submitted = true;

    if (this.createUserForm.invalid) {
      return;
    }

    if(this.createUserForm.controls['password'].value == this.createUserForm.controls['password2'].value){
      this.passwordsnotmatching = false;
    }
    if(this.passwordsnotmatching == true)
      return;
    
    console.log("submit");
    this.user.email = this.createUserForm.controls['email'].value;
    this.user.firstName = this.createUserForm.controls['firstname'].value;
    this.user.lastName = this.createUserForm.controls['lastname'].value;
    this.user.password = this.createUserForm.controls['password'].value;
    this.user.phoneNumber = this.createUserForm.controls['phonenumber'].value;
    this.user.city = this.createUserForm.controls['city'].value;
    this.user.country = this.createUserForm.controls['country'].value;
    this.user.occupation = this.createUserForm.controls['occupation'].value;
    this.user.companyInfo = this.createUserForm.controls['companyinfo'].value;
    this.user.role = "USER";
    /*this.newTour.authorId = "49887a37-e9e1-4b71-8a07-3b84c7d6c676";
    this.newTour.title = this.createTourForm.controls['title'].value;
    this.newTour.description = this.createTourForm.controls['description'].value;
    this.newTour.price = this.createTourForm.controls['price'].value;
    this.newTour.difficulty = this.createTourForm.controls['difficulty'].value;
    this.newTour.interests = this.createTourForm.controls['interests'].value;
    console.log(this.newTour);
    this.toursClient.createTour(this.newTour).subscribe(
      result => {
        this.newTour.id = result;
      }
    );
    */
    alert(
      'SUCCESS!! :-)\n\n' + JSON.stringify(this.user, null, 6)
    );
    this.userService.save(this.user).subscribe(result => this.gotoUserList());
  }

  gotoUserList() {
    this.router.navigate(['/users']);
  }
}
