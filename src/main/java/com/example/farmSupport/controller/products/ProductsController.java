package com.example.farmSupport.controller.products;

import com.example.farmSupport.entity.products.ProductsData;
import com.example.farmSupport.form.products.ProductForm;
import com.example.farmSupport.service.products.ProductsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/products/")
public class ProductsController {
    @Autowired
    ProductsService productsService;

    @GetMapping("List")
    public String productsList(Model model) throws Exception {

        List<ProductsData> productsDataList = productsService.getProductsList();
        if(productsDataList == null) {
            model.addAttribute("errorMessage","取得時にエラーが発生しました。");
            model.addAttribute("productsDataList",new ArrayList<>());
        }else{
            System.out.println("here:"+productsDataList.size());
            model.addAttribute("productsDataList",productsDataList);
        }

        return "products/productsList";
    }

    @GetMapping("detail")
    public String productDetail(@RequestParam() String productId,Model model)throws Exception{
        if(productId == null){
            return "redirect:/products/List";
        }
        ProductsData productsData = productsService.getProductDetail(productId);
        if(productsData == null){
            model.addAttribute("errorMessage","存在しない商品IDが入力されています");
        }
        model.addAttribute("product",productsData);
        return "products/productDetail";
    }

    @GetMapping("regist")
    public String productRegist(Model model){

        ProductForm productForm = new ProductForm();

        model.addAttribute("productForm",productForm);
        return "products/regist";
    }


    @PostMapping("registComplete")
    public String productRegistComplete(@Validated @ModelAttribute ProductForm productForm, BindingResult bindingResult, Principal principal, Model model){
        if (bindingResult.hasErrors()) {
            return "products/regist";
        }
        ProductsData productsData = new ProductsData();
        try{
             productsData = productsService.regist(productForm,principal);
        }catch (Exception e){
            model.addAttribute("ErrorMessage","登録時にエラーが発生しました。");
            model.addAttribute("productForm",productForm);
            return "products/regist";
        }

        return "redirect:/products/detail?productId="+ productsData.getId();
    }
}
