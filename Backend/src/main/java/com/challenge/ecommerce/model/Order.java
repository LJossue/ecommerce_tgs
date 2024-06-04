
package com.challenge.ecommerce.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity @Table(name = "purchase_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
private UserEntity user;
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
private List<OrderItem> items = new ArrayList<>();
private Date orderDate;
private Double totalAmount;

}
