package com.maquinaria.ecommerce.backend.infrastructure.Controller;


import com.maquinaria.ecommerce.backend.application.service.ProductService;
import com.maquinaria.ecommerce.backend.domain.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.jaxb.Origin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/admin/products")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestParam("id") Integer id,
                                        @RequestParam("code") String code,
                                        @RequestParam("name") String name,
                                        @RequestParam("description") String description,
                                        @RequestParam("price") BigDecimal price,
                                        @RequestParam("urlImage") String urlImage,
                                        @RequestParam("userId") Integer userId,
                                        @RequestParam("categoryId") Integer categoryId,
                                        @RequestParam(value ="image",required = false)MultipartFile multipartFile

                                        ) throws IOException {
        Product product = new Product();
        product.setId(id);
        product.setCode(code);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setUrlImage(urlImage);
        product.setUserId(userId);
        product.setCategoryId(categoryId);

        log.info("Nombre Producto {}",product.getName());
        return ResponseEntity.ok(productService.save(product,multipartFile));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable Integer id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id){
        productService.delateById(id);
        return ResponseEntity.ok().build();
    }

}
