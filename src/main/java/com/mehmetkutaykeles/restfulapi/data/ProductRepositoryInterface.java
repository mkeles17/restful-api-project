package com.mehmetkutaykeles.restfulapi.data;

import com.mehmetkutaykeles.restfulapi.models.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepositoryInterface extends CrudRepository<ProductEntity, Long> {

    List<ProductEntity> findByProductNameContainingIgnoreCase(String searchTerm);
}
