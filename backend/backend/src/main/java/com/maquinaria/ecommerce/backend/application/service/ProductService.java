package com.maquinaria.ecommerce.backend.application.service;

import com.maquinaria.ecommerce.backend.domain.model.Product;
import com.maquinaria.ecommerce.backend.domain.port.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
public class ProductService {

    private  final IProductRepository productRepository;
    private  final UploadFile uploadFile;
    public ProductService(IProductRepository productRepository, UploadFile uploadFile) {
        this.productRepository = productRepository;
        this.uploadFile = uploadFile;
    }

    public Product save(Product product, MultipartFile multipartFile) throws IOException {
        if (product.getId()!=0){
            if (multipartFile == null){
                product.setUrlImage(product.getUrlImage());
            }else {
                String nameFail = product.getUrlImage().substring(29);
                log.info("este es el nombre de la imagen : {}",nameFail);
                if(!nameFail.equals("default.jpg")){
                    uploadFile.delete(nameFail);
                }
                product.setUrlImage(uploadFile.upload(multipartFile));
            }
        }else {
            product.setUrlImage(uploadFile.upload(multipartFile));
        }

        return this.productRepository.save(product);
    }

    public Iterable<Product> findAll(){
        return this.productRepository.findAll();
    }

    public Product findById(Integer id){
        return productRepository.findById(id);
    }

    public void delateById(Integer id){
        Product product = findById(id);
        String nameFail = product.getUrlImage().substring(29);
        log.info("este es el nombre de la imagen : {}",nameFail);
        if(!nameFail.equals("default.jpg")){
            uploadFile.delete(nameFail);
        }
        this.productRepository.deleteById(id);
    }
}
