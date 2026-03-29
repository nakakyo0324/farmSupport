package com.example.farmSupport.controller.order;

import com.example.farmSupport.entity.order.CartData;
import com.example.farmSupport.entity.order.OrderData;
import com.example.farmSupport.entity.products.ProductsData;
import com.example.farmSupport.entity.user.UserData;
import com.example.farmSupport.service.order.OrderService;
import com.example.farmSupport.service.products.ProductsService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    ProductsService productsService;
    @Autowired
    OrderService orderService;

    @PostMapping("/order/add")
    public String addToCart(@RequestParam Long id, @RequestParam int quantity, HttpSession session, Model model) throws Exception {
        List<CartData> cartList = (List<CartData>) session.getAttribute("cart");
        boolean findFlg = false;
        if(cartList == null) {
            cartList = new ArrayList<>();
        }

        for(CartData cartData:cartList){
            if(cartData.getId().equals(id)){
                cartData.setQuantity(cartData.getQuantity()+quantity);
                findFlg = true;
            }
        }
        if(!findFlg){
        CartData cart = new CartData();
        cart.setId(id);
        cart.setQuantity(quantity);
        cartList.add(cart);
        }
        session.setAttribute("cart",cartList);

        ProductsData product = productsService.getProductDetail(String.valueOf(id));

        model.addAttribute("product",product);
        model.addAttribute("quantity",quantity);
        return "order/addCart";
    }

    @GetMapping("/order/confirm")
    public String confirmCart(HttpSession session, Model model)throws Exception{
        List<CartData> cartList = (List<CartData>) session.getAttribute("cart");
        if(cartList == null){
            System.out.println("hoge");
            cartList = new ArrayList<>();
        }
        List<CartProduct> productsDataList = new ArrayList<>();
        for(CartData cart : cartList){
             ProductsData product = productsService.getProductDetail(String.valueOf(cart.getId()));

             CartProduct cartProduct = new CartProduct();
             cartProduct.setId(product.getId());
             cartProduct.setName(product.getName());
             cartProduct.setPrice(product.getPrice());
             cartProduct.setQuantity(cart.getQuantity());
             productsDataList.add(cartProduct);
        }

        model.addAttribute("products",productsDataList);

        return "order/cartConfirm";
    }

    @PostMapping("/order/submit")
    public String orderSubmit(Model model, HttpSession session, Principal principal) throws Exception {
        List<CartData> cartList = (List<CartData>) session.getAttribute("cart");
        if(cartList == null){
            model.addAttribute("ErrorMessage","カートに商品が入っていません。");
            return "order/failed";
        }
        boolean flag = false;
        for(CartData cartData: cartList){
            ProductsData product = productsService.getProductDetail(String.valueOf(cartData.getId()));
            if(product.getStock() < cartData.getQuantity()){
                model.addAttribute("ErrorMessage","一部の商品の在庫がありませんでした。");
                if(product.getStock() != 0) {
                    cartData.setQuantity(product.getStock());
                }else{
                    cartList.remove(cartData);
                }
                flag = true;
            }
        }

        if(flag){
            session.setAttribute("cart",cartList);
            return "order/failed";
        }
        OrderData orderData = new OrderData();
        try{
             orderData = orderService.executeOrder(cartList,principal);
        }catch(SQLException e){
            model.addAttribute("ErrorMessage","商品購入時にエラーが発生しました。<br>再度、購入手続きを行ってください");
            return "order/failed";
        }

        model.addAttribute("order",orderData);

        return "order/success";
    }

    public static class CartProduct extends ProductsData{
        @Getter
        @Setter
        private int quantity;
    }
}
