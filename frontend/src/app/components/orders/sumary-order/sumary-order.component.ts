import { Component, OnInit } from '@angular/core';
import { ItemCart } from '../../../common/item-cart';
import { CartService } from '../../../services/cart.service';
import { UserService } from '../../../services/user.service';
import { OrderProduct } from '../../../common/order-product';
import { OrderStatus } from '../../../common/order-state';
import { Order } from '../../../common/order';
import { OrderService } from '../../../services/order.service';
import { PaymentService } from '../../../services/payment.service';
import { DataPayment } from '../../../common/data-payment';
import { SessionStorageService } from '../../../services/session-storage.service';

@Component({
  selector: 'app-sumary-order',
  templateUrl: './sumary-order.component.html',
  styleUrl: './sumary-order.component.css'
})
export class SumaryOrderComponent implements OnInit {

  items : ItemCart [] = [];

  totalCart : number = 0;

  firstName : string = '';
  lastName : string = '';
  email : string = '';
  address : string = '';
  oderProducts : OrderProduct [] = [];
  userId : number = 0; 

  constructor(private orderService: OrderService,
    private userService : UserService,
    private cartService : CartService,
    private paymentService:PaymentService,
    private sessionStore:SessionStorageService
    )
    {}

  ngOnInit(): void {
    this.items = this.cartService.convertToListFromMap();
    this.totalCart =this.cartService.totalCart();
    this.userId = this.sessionStore.getItem('token').id;
    this.getUserById(this.userId);
    setTimeout(
      () =>{
        this.sessionStore.removeItem('token');
        },600000);
     
  }

deleteItemCart(productId:number){
  this.cartService.deleteItemCart(productId);
  this.items = this.cartService.convertToListFromMap();
  this.totalCart =this.cartService.totalCart();

}

generateOrder(){
  this.items.forEach(
    item =>{
      let oderProduct = new OrderProduct(null,item.productId,item.quantity,item.price);
      this.oderProducts.push(oderProduct);
    }
  );

  let order = new Order(null,new Date(),this.oderProducts,this.userId,OrderStatus.CANCELLED);
  console.log('Order: '+ order.orderStatus);
  this.orderService.createOrder(order).subscribe(
    data =>{
      console.log('orden creada con id :' + data.id);
      this.sessionStore.setItem('order',data);
    }
  );

  //redireccion a paypal
  let urlPayment;
    let dataPayment = new DataPayment ('PAYPAL', this.totalCart.toString(), 'USD', 'COMPRA');

    console.log('Data Payment:', dataPayment);

    this.paymentService.getUrlPaypalPayment(dataPayment).subscribe(
      data => {
        urlPayment = data.url;
        console.log('Respuesta exitosa...');
        window.location.href = urlPayment;
      }    
    );

}

getUserById(id:number){
  this.userService.getUserById(id).subscribe(
    data =>{
      this.firstName = data.firstName;
      this.lastName = data.lastName;
      this.email = data.email;
      this.address = data.address;
    }
  );
}

}
