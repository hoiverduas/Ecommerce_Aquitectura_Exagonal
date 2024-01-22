package com.maquinaria.ecommerce.backend.infrastructure.adapter;

import com.maquinaria.ecommerce.backend.domain.model.Product;
import com.maquinaria.ecommerce.backend.domain.port.IProductRepository;
import com.maquinaria.ecommerce.backend.infrastructure.mapper.IProductMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductCrudRepositoryImpl implements IProductRepository {

    private final IProductCrudRepository productCrudRepository;
    private final IProductMapper productMapper;

    public ProductCrudRepositoryImpl(IProductCrudRepository productCrudRepository, IProductMapper productMapper) {
        this.productCrudRepository = productCrudRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product save(Product product) {
        return productMapper.toProduct(productCrudRepository.save(productMapper.toProductEntity(product)));
    }

    @Override
    public Iterable<Product> findAll() {
        return productMapper.toProductList(productCrudRepository.findAll());
    }

    @Override
    public Product findById(Integer id) {
        return productMapper.toProduct(productCrudRepository.findById(id).orElseThrow(()-> new RuntimeException("Producto con id"+id+"no existe")));
    }

    @Override
    public void deleteById(Integer id) {

        productCrudRepository.findById(id).orElseThrow(()->new RuntimeException("Producto con id"+id+"no existe"));
        productCrudRepository.deleteById(id);

    }
}
