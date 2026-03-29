package com.example.farmSupport.entity.order;


import com.example.farmSupport.entity.user.UserData;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Data
@Entity
@Setter
@Table(name="Orders", schema="FARMSUPPORT")
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name="order_id")
    private Long id;

    @Getter
    @Setter
    @JoinColumn(name="user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserData userData;

    @Setter
    @Getter
    @Column(name = "total_amount")
    private Long totalAmount;

    @Setter
    @Getter
    @Column(name="order_date")
    private Date date;
}
