package com.mehmetkutaykeles.restfulapi.services;

import com.mehmetkutaykeles.restfulapi.data.ProductsDataAccessInterface;
import com.mehmetkutaykeles.restfulapi.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductManagementService {

    @Autowired
    ProductsDataAccessInterface<Product> productsDataServiceForRepository;

    public List<Product> getAllProducts(){
        return productsDataServiceForRepository.getProducts();
    }

    public List<Product> searchProducts(String searchTerm){
        return productsDataServiceForRepository.searchProducts(searchTerm);
    }

    public long addProduct(Product newProduct){
        return productsDataServiceForRepository.addProduct(newProduct);
    }

    public Product getById(long id) {
        return productsDataServiceForRepository.getById(id);
    }

    public boolean deleteProduct(long id) {
        return productsDataServiceForRepository.deleteProduct(id);
    }

    public Product modifyProduct(long idToUpdate, Product updateProduct) {
        return productsDataServiceForRepository.modifyProduct(idToUpdate, updateProduct);
    }
}
