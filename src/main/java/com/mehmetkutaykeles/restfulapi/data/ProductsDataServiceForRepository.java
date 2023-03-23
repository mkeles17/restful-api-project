package com.mehmetkutaykeles.restfulapi.data;

import com.mehmetkutaykeles.restfulapi.models.Product;
import com.mehmetkutaykeles.restfulapi.models.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class ProductsDataServiceForRepository implements ProductsDataAccessInterface<Product>{

    @Autowired
    ProductRepositoryInterface productRepository;

    @Autowired
    DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private ModelMapper modelMapper = new ModelMapper();

    public ProductsDataServiceForRepository(DataSource dataSource){

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Product getById(long id) {
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        Product product = modelMapper.map(productEntity, Product.class);
        return product;
    }

    @Override
    public List<Product> getProducts() {
        Iterable<ProductEntity> productsEntity = productRepository.findAll();
        List<Product> products = new ArrayList<Product>();
        for(ProductEntity item: productsEntity) {
            products.add(modelMapper.map(item, Product.class));
        }
        return products;
    }

    @Override
    public List<Product> searchProducts(String searchTerm) {
        Iterable<ProductEntity> productsEntity = productRepository.findByProductNameContainingIgnoreCase(searchTerm);
        List<Product> products = new ArrayList<Product>();
        for(ProductEntity item: productsEntity) {
            products.add(modelMapper.map(item, Product.class));
        }
        return products;
    }

    @Override
    public long addProduct(Product newProduct) {
        ProductEntity productEntity = modelMapper.map(newProduct, ProductEntity.class);
        ProductEntity result = productRepository.save(productEntity);
        if(result == null) return 0;
        return result.getId();
    }

    @Override
    public boolean deleteProduct(long id) {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public Product modifyProduct(long idToUpdate, Product updateProduct) {
        ProductEntity productEntity = modelMapper.map(updateProduct, ProductEntity.class);
        ProductEntity result = productRepository.save(productEntity);
        Product product = modelMapper.map(result, Product.class);
        return product;
    }
}
