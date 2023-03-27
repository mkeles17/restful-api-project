package com.mehmetkutaykeles.restfulapi.controllers;

import com.mehmetkutaykeles.restfulapi.data.ProductsDataServiceForRepository;
import com.mehmetkutaykeles.restfulapi.models.Product;
import com.mehmetkutaykeles.restfulapi.services.ProductManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;



@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
//@WithMockUser
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductManagementService productManagementService;

    @MockBean
    private DataSource dataSource;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @MockBean
    private ProductsDataServiceForRepository productsDataServiceForRepository;

    List<Product> mockDB = new ArrayList<Product>();

    @BeforeEach
    public void init() {
        Product mockProduct1 = new Product(1,75.0f,120,"iphone holder","mehmet kutay keles");
        Product mockProduct2 = new Product(2,32.0f,400,"key chain","oktay keles");
        mockDB.add(mockProduct1);
        mockDB.add(mockProduct2);
    }

    @Test
    public void showAllProducts() throws Exception {
        when(productManagementService.getAllProducts()).thenReturn(mockDB);
        RequestBuilder request = MockMvcRequestBuilders.get("/api/products/");
        MvcResult result = mvc.perform(request).andReturn();
        String expected = "[{\"id\":1,\"price\":75.0,\"quantity\":120,\"productName\":\"iphone holder\",\"ownerName\":\"mehmet kutay keles\"},{\"id\":2,\"price\":32.0,\"quantity\":400,\"productName\":\"key chain\",\"ownerName\":\"oktay keles\"}]";
        assertEquals(expected,result.getResponse().getContentAsString());
    }

    @Test
    void searchProducts() throws Exception {
        String searchTerm = "key";
        when(productManagementService.searchProducts(searchTerm)).thenReturn(mockDB
                .stream().filter(product -> product.getProductName().contains(searchTerm)).toList());
        RequestBuilder request = MockMvcRequestBuilders.get("/api/products/search/key");
        MvcResult result = mvc.perform(request).andReturn();
        String expected = "[{\"id\":2,\"price\":32.0,\"quantity\":400,\"productName\":\"key chain\",\"ownerName\":\"oktay keles\"}]";
        assertEquals(expected,result.getResponse().getContentAsString());
    }

    @Test
    void addProduct() throws Exception {
        String newProductJson = "{\"id\":3,\"price\":10.0,\"quantity\":923,\"productName\":\"bubble gum\",\"ownerName\":\"berkay koruyucu\"}";
        when(productManagementService.addProduct(any())).thenReturn(3l);
        RequestBuilder request = MockMvcRequestBuilders.post("/api/products/")
                .accept(MediaType.APPLICATION_JSON).content(newProductJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(request).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("3",response.getContentAsString());
    }

    @Test
    void getProductById() throws Exception {
        long id = 1;
        when(productManagementService.getById(id)).thenReturn(mockDB.get((int)id-1));
        RequestBuilder request = MockMvcRequestBuilders.get("/api/products/1");
        MvcResult result = mvc.perform(request).andReturn();
        String expected = "{\"id\":1,\"price\":75.0,\"quantity\":120,\"productName\":\"iphone holder\",\"ownerName\":\"mehmet kutay keles\"}";
        assertEquals(expected,result.getResponse().getContentAsString());
    }

    @Test
    void deleteProductById() throws Exception {
        long id = 2;
        when(productManagementService.deleteProduct(id)).thenReturn(true);
        RequestBuilder request = MockMvcRequestBuilders.delete("/api/products/delete/2");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("true",result.getResponse().getContentAsString());
    }

    @Test
    void updateProduct() throws Exception {
        String updatedProductJson = "{\"id\":1,\"price\":10.0,\"quantity\":923,\"productName\":\"bubble gum\",\"ownerName\":\"mehmet kutay keles\"}";
        Product updatedProduct = new Product(1,10.0f,923,"bubble gum","mehmet kutay keles");
        when(productManagementService.modifyProduct(1l,updatedProduct)).thenReturn(updatedProduct);
        RequestBuilder request = MockMvcRequestBuilders.put("/api/products/update/1")
                .accept(MediaType.APPLICATION_JSON).content(updatedProductJson)
                .contentType(MediaType.APPLICATION_JSON);;
        MvcResult result = mvc.perform(request).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}