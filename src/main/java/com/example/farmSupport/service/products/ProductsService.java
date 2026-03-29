package com.example.farmSupport.service.products;

import com.example.farmSupport.entity.products.ProductsData;
import com.example.farmSupport.repository.products.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {
    @Autowired
    private final ProductsRepository productsRepository;

    public List<ProductsData> getProductsList() throws Exception {
        List<ProductsData> productsList;
        try{
            productsList = productsRepository.findAll();
        }catch(Exception e){
            return null;
        }
        return productsList;
    }

    public ProductsData getProductDetail(String idStr)throws Exception{
        try{
            Long id = Long.valueOf(idStr);
            return productsRepository.findByProductId(id);
        }catch (Exception e){
            return null;
        }
    }

}
