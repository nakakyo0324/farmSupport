package com.example.farmSupport.repository.order;

import com.example.farmSupport.entity.order.OrderData;
import com.example.farmSupport.entity.order.OrderDetailData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailData,Long> {
    List<OrderDetailData> findByOrderData(OrderData orderData);
}
