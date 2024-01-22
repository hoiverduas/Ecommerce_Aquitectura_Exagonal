import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { HeaderService } from './header.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private appiUrl : string = "http://localhost:9092/api/v1/admin/products";

  constructor(private httpClient:HttpClient,private headerService:HeaderService) { }

  getPtoducts():Observable<Product[]>{
    return this.httpClient.get<Product[]>(this.appiUrl,{headers:this.headerService.headers});
  }

  createProduct(formData:any):Observable<any>{
    return this.httpClient.post<Product>(this.appiUrl,formData,{headers:this.headerService.headers});
  }

  deleteProductById(id:number):Observable<any>{
    return this.httpClient.delete(this.appiUrl +"/"+id,{headers:this.headerService.headers});
  }

  getProductById(id:number):Observable<Product>{
    return this.httpClient.get<Product>(this.appiUrl+"/"+id,{headers:this.headerService.headers});
  }



}
