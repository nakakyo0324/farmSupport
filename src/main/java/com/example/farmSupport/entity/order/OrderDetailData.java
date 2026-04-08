package com.example.farmSupport.entity.order;

import com.example.farmSupport.entity.products.ProductsData;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="order_details", schema="FARMSUPPORT")
public class OrderDetailData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name="order_detail_id")
    private Long detailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    @JoinColumn(name = "order_id")
    private OrderData orderData;

    @Getter
    @Setter
    @Column(name="prices")
    private Long price;

    @Getter
    @Setter
    @Column(name="piece")
    private Long piece;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductsData productsData;

    public String getProductName(){
        return productsData.getName();
    }

    public String getProductRegistUserName(){
        return productsData.getUserName();
    }
}
