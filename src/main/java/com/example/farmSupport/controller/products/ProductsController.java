package com.example.farmSupport.controller.products;

import com.example.farmSupport.entity.products.ProductsData;
import com.example.farmSupport.service.products.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
    public String product(@RequestParam() String productId,Model model)throws Exception{
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
}
