import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  private appiUrl : string = "http://localhost:9092/api/v1/home";

  constructor(private httpClient:HttpClient) { }

  getPtoducts():Observable<Product[]>{
    return this.httpClient.get<Product[]>(this.appiUrl);
  }
  getProductById(id:number):Observable<Product>{
    return this.httpClient.get<Product>(this.appiUrl+"/"+id);
  }
  
}
