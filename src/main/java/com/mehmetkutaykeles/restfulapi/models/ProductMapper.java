package com.mehmetkutaykeles.restfulapi.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

        Product product = new Product(rs.getLong("ID"),rs.getFloat("PRICE")
                ,rs.getInt("QUANTITY"),rs.getString("PRODUCT_NAME"),rs.getString("OWNER_NAME"));

        return product;
    }
}
