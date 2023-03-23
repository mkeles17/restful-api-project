package com.mehmetkutaykeles.restfulapi.controllers;

import com.mehmetkutaykeles.restfulapi.models.Product;
import com.mehmetkutaykeles.restfulapi.services.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    ProductManagementService productManagementService;

    @Autowired
    public ProductController(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }

    @GetMapping("/")
    public List<Product> showAllProducts(){
        List<Product> products = productManagementService.getAllProducts();
        return products;
    }

    @GetMapping("/search/{searchTerm}")
    public List<Product> searchProducts(@PathVariable(name="searchTerm") String searchTerm){
        List<Product> products = productManagementService.searchProducts(searchTerm);
        return products;
    }

    @PostMapping("/")
    public long addProduct(@RequestBody Product newProduct){
        return productManagementService.addProduct(newProduct);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(name="id") long id){
        Product product = productManagementService.getById(id);
        return product;
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteProductById(@PathVariable(name="id") long id){
        return productManagementService.deleteProduct(id);
    }

    @PutMapping("/update/{id}")
    public Product addProduct(@RequestBody Product updatedProduct, @PathVariable(name="id") long id){
        return productManagementService.modifyProduct(id,updatedProduct);
    }
}
