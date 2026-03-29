package com.example.farmSupport.service.order;

import com.example.farmSupport.entity.order.CartData;
import com.example.farmSupport.entity.order.OrderData;
import com.example.farmSupport.entity.order.OrderDetailData;
import com.example.farmSupport.entity.products.ProductsData;
import com.example.farmSupport.entity.user.UserData;
import com.example.farmSupport.repository.UserRepository;
import com.example.farmSupport.repository.order.OrderDetailRepository;
import com.example.farmSupport.repository.order.OrderRepository;
import com.example.farmSupport.repository.products.ProductsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public OrderData executeOrder(List<CartData> cartDataList, Principal principal) throws Exception {

        String userName = principal.getName();
        UserData userData = userRepository.findByUsername(userName).orElseThrow(() -> new Exception("Not Found User"));

        OrderData orderData = new OrderData();
        orderData.setUserData(userData);
        orderData.setDate(Date.valueOf(LocalDate.now()));
        Long totalAmount = cartDataList.stream().mapToLong(p -> productsRepository.findByProductId(p.getId()).getPrice() * p.getQuantity()).sum();
        orderData.setTotalAmount(totalAmount);

        OrderData saveOrder = orderRepository.save(orderData);

        for(CartData cartData:cartDataList){
            ProductsData productsData = productsRepository.findByProductId(cartData.getId());

            productsData.setStock(productsData.getStock() - cartData.getQuantity());
            productsRepository.save(productsData);

            OrderDetailData detail = new OrderDetailData();
            detail.setOrderData(saveOrder);
            detail.setProductsData(productsData);
            detail.setPrice((long) productsData.getPrice());

            orderDetailRepository.save(detail);
        }
        return saveOrder;
    }
}
