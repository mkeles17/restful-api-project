package com.mehmetkutaykeles.restfulapi.data;

import com.mehmetkutaykeles.restfulapi.models.Product;

import java.util.List;

public interface ProductsDataAccessInterface<T> {

    public T getById(long id);
    public List<T> getProducts();
    public List<T> searchProducts(String searchTerm);

    public long addProduct(T newProduct);
    public boolean deleteProduct(long id);
    public T modifyProduct(long idToUpdate,T updateProduct);
}
