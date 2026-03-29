package com.example.farmSupport.repository.products;

import com.example.farmSupport.entity.products.ProductsData;
import com.example.farmSupport.entity.user.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsData, Long> {

    @Query("SELECT u FROM ProductsData u WHERE u.id = :id")
    ProductsData findByProductId(@Param("id") Long id);
}
