import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../services/order.service';
import { SessionStorageService } from '../../services/session-storage.service';
import { OrderStatus } from '../../common/order-state';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payment-success',
  templateUrl: './payment-success.component.html',
  styleUrl: './payment-success.component.css'
})
export class PaymentSuccessComponent implements OnInit{
  
  constructor(private orderService:OrderService,private sessionStorage:SessionStorageService){}
  
  ngOnInit(): void {
    console.log(this.sessionStorage.getItem('order'));
    let order = this.sessionStorage.getItem('order');

    let formData = new FormData();

    formData.append('id',order.id);
    formData.append('status',OrderStatus.CONFIRMED.toString());


    this.orderService.updateOrder(formData).subscribe(
      data =>{
        console.log(data);
        console.log('LogoutComponent : ' + this.sessionStorage.getItem('token'));
        this.sessionStorage.removeItem('token');
        console.log('LogoutComponent eliminado :' +this.sessionStorage.getItem('token'));
       
      }
    );


    }
    


}
