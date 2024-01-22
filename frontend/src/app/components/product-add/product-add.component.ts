import { Component, OnInit } from '@angular/core';
import { Product } from '../../common/product';
import { ProductService } from '../../services/product.service';
import { formatDate } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Category } from '../../common/category';
import { CategoryService } from '../../services/category.service';
import { HeaderService } from '../../services/header.service';
import { SessionStorageService } from '../../services/session-storage.service';


@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrl: './product-add.component.css'
})
export class ProductAddComponent implements OnInit {
  
 id : number = 0;
 code : string = '001';
 name : string = '';
 description : string = '';
 price : number = 0;
 urlImage : string = '';
 userId : string = '0';
 categoryId : string = '3';
 selectFile! : File;
 user : number = 0;
 categories : Category [] = [];

  constructor(
    private categoryService :CategoryService,
    private productService : ProductService , 
    private router : Router,
    private activatedRoute : ActivatedRoute,
    private toastr:ToastrService,
    private SessionStorage:SessionStorageService

    ){}

  ngOnInit(): void {
    this.getProductById();
    this.getCategories();
    this.user = this.SessionStorage.getItem('token').id;
    this.userId = this.user.toString();
  }

  addProduct(){
    
    const formData = new FormData();
    formData.append('id',this.id.toString());
    formData.append('code', this.code);
    formData.append('name', this.name);
    formData.append('description',this.description);
    formData.append('price', this.price.toString());
    formData.append('image',this.selectFile);
    formData.append('urlImage', this.urlImage);
    formData.append('userId', this.userId);
    formData.append('categoryId', this.categoryId);
    console.log(formData);
  
    this.productService.createProduct(formData).subscribe(
      data => {console.log(data)
        if(this.id == 0){
          this.toastr.success('producto registrado correctamente','productos')
        }else{
          this.toastr.success('producto actualizado correctamente','productos')
        }
        this.router.navigate(["admin/product"]);
      }
    );    
  }

  getProductById(){
    this.activatedRoute.params.subscribe(
      prod =>{
        let id = prod['id'];
        if(id){
          console.log('el valor de la variable id es : ' + id);
          this.productService.getProductById(id).subscribe(
            data => {
                
                this.id = data.id;
                this.code = data.code;
                this.name = data.name;
                this.description = data.description;
                this.price = data.price;
                this.urlImage = data.urlImage;
                this.userId = data.userId.toString();
                this.categoryId = data.categoryId.toString();
            
            }
          );
        }
      }
    );
  }

  onFileSelected(event: any){
    this.selectFile = event.target.files[0];
  }


  getCategories(){
    return this.categoryService.getCategoryList().subscribe(
      data => this.categories = data
    );
  }

}
