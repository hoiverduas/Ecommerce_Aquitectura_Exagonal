import { Component, OnInit } from '@angular/core';
import { Category } from '../../../common/category';
import { CategoryService } from '../../../services/category.service';
import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrl: './category-list.component.css'
})
export class CategoryListComponent implements OnInit{

  categories : Category [] = [];

  constructor(private categoryService:CategoryService,private toast:ToastrService){}


  ngOnInit(): void {
    this.listCategory();
  }

  listCategory(){

    this.categoryService.getCategoryList().subscribe(
         data => this.categories = data

    );


  }

  deleteCateroById(id:number){

    Swal.fire({
      title: "Esta seguro que quiere eliminar el registro ?",
      text: "",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Eliminar",
      cancelButtonText: "Cancelar"
    }).then((result) => {
      if (result.isConfirmed) {

        this.categoryService.deleteCategoryById(id).subscribe(
          ()=>this.listCategory()
        );

        Swal.fire({
          title: "Categorias",
          text: "Categoria Eliminado Correctamente.",
          icon: "success"
        });
      }
    });

    
  
  }



}
