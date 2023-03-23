package com.mehmetkutaykeles.restfulapi.data;

import com.mehmetkutaykeles.restfulapi.models.Product;
import com.mehmetkutaykeles.restfulapi.models.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductsDataService implements ProductsDataAccessInterface<Product>{

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Product getById(long id) {
        List<Product> results = jdbcTemplate.query("SELECT * FROM products WHERE ID = ?", new ProductMapper(), id);
        if (results.size() > 0) return results.get(0);
        return null;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> results = jdbcTemplate.query("SELECT * FROM products", new ProductMapper());
        return results;
    }

    @Override
    public List<Product> searchProducts(String searchTerm) {
        List<Product> results = jdbcTemplate.query("SELECT * FROM products WHERE PRODUCT_NAME LIKE ?",
                new ProductMapper(), "%"+searchTerm+"%");
        return results;
    }

    @Override
    public long addProduct(Product newProduct) {
        // long result = jdbcTemplate.update("INSERT INTO products (PRICE, " +
        //                "QUANTITY, PRODUCT_NAME, OWNER_NAME) VALUES (?,?,?,?)", newProduct.getPrice(),
        //        newProduct.getQuantity(), newProduct.getProductName(), newProduct.getOwnerName());
        // return result;

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(jdbcTemplate);

        simpleInsert.withTableName("products").usingGeneratedKeyColumns("ID");

        Map<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("PRICE", newProduct.getPrice());
        parameters.put("QUANTITY", newProduct.getQuantity());
        parameters.put("PRODUCT_NAME", newProduct.getProductName());
        parameters.put("OWNER_NAME", newProduct.getOwnerName());

        Number result = simpleInsert.executeAndReturnKey(parameters);

        return result.longValue();
    }

    @Override
    public boolean deleteProduct(long id) {
        int result = jdbcTemplate.update("DELETE FROM products WHERE ID = ?", id);
        if(result>0) return true;
        return false;
    }

    @Override
    public Product modifyProduct(long idToUpdate, Product updateProduct) {

        int result = jdbcTemplate.update("UPDATE products SET PRICE = ?, QUANTITY = ?, PRODUCT_NAME = ?, " +
                "OWNER_NAME = ? WHERE ID = ?",
                updateProduct.getPrice(),
                updateProduct.getQuantity(),
                updateProduct.getProductName(),
                updateProduct.getOwnerName(),
                idToUpdate
                );
        if(result>0) return updateProduct;
        return null;
    }
}
