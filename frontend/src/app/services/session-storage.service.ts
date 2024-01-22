import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionStorageService {

  constructor() { }

  setItem(key :string,valor:any){
    sessionStorage.setItem(key,JSON.stringify(valor));
  }

  getItem(key:string){
    const item = sessionStorage.getItem(key);
    return item ? JSON.parse(item):null;

  }

  removeItem(key:string){
    sessionStorage.removeItem(key);
  }

  clea(){
    sessionStorage.clear();
  }
}
