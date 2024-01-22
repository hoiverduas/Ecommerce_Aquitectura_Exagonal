import { Component, OnInit } from '@angular/core';
import { AutheticationService } from '../../../services/authetication.service';
import { Userdto } from '../../../common/userdto';
import { SessionStorageService } from '../../../services/session-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  username : string = '';
  password : string = '';

  ngOnInit(): void {
    
  }

  constructor(
    private Authetication:AutheticationService,
    private sessionStorage:SessionStorageService,
    private router : Router
    ){}

  login(){

    let userDto = new Userdto(this.username,this.password);
    this.Authetication.login(userDto).subscribe(
      token =>{ 
        console.log(token);
        this.sessionStorage.setItem('token',token);
        if(token.type == 'ADMIN'){
          this.router.navigate(['/admin/product']);
        }else{
          this.router.navigate(['/']);
        }
      
      }
    );
    console.log(userDto);


  }

}
