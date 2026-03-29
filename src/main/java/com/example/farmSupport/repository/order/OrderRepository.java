package com.example.farmSupport.repository.order;

import com.example.farmSupport.entity.order.OrderData;
import com.example.farmSupport.entity.order.OrderDetailData;
import com.example.farmSupport.entity.products.ProductsData;
import com.example.farmSupport.entity.user.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<OrderData, Long> {
    List<OrderDetailData> findByUserData(UserData userData);

}
