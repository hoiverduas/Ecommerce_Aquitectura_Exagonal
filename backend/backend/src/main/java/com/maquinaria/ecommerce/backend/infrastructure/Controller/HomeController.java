package com.maquinaria.ecommerce.backend.infrastructure.Controller;


import com.maquinaria.ecommerce.backend.application.service.ProductService;
import com.maquinaria.ecommerce.backend.domain.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/home")
@CrossOrigin("http://localhost:4200")
public class HomeController {

    private final ProductService productService;


    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable Integer id){
        return ResponseEntity.ok(productService.findById(id));
    }


}
