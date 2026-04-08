package com.example.farmSupport.service.products;

import com.example.farmSupport.entity.products.ProductsData;
import com.example.farmSupport.entity.user.UserData;
import com.example.farmSupport.form.products.ProductForm;
import com.example.farmSupport.repository.UserRepository;
import com.example.farmSupport.repository.products.ProductsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {
    @Autowired
    private final ProductsRepository productsRepository;
    @Autowired
    private UserRepository userRepository;

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
    @Transactional
    public ProductsData regist(ProductForm productForm, Principal principal) throws Exception {
        String userName = principal.getName();
        UserData userData = userRepository.findByUsername(userName).orElseThrow(() -> new Exception("Not Found User"));

        ProductsData productsData = new ProductsData();
        productsData.setName(productForm.getName());
        productsData.setPrice(productForm.getPrice());
        productsData.setStock(productForm.getStock());
        productsData.setUserData(userData);
        productsData.setUri("");
        return productsRepository.save(productsData);

    }

}
