package com.example.farmSupport.entity.products;

import com.example.farmSupport.entity.user.UserData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Entity
@Table(name="Products", schema="FARMSUPPORT")
public class ProductsData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "product_id")
    private Long id;

    @Getter
    @Column(name = "product_name")
    private String name;

    @Getter
    @Column(name="price")
    private int price;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="REGIST_USER_ID")
    private UserData userData;

    @Getter
    @Column(name="REGIST_DATE")
    private Date date;

    @Getter
    @Column(name="stock")
    private int stock;

    @Getter
    @Column(name="IMAGE_URI")
    private String Uri;

    public String getUserName(){
        return (userData != null) ? userData.getUsername():"取得できませんでした。";
    }

}
