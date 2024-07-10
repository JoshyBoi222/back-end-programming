package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
import java.util.Set;
import java.math.BigDecimal;


@Table(name = "CART_ITEMS")
@Entity
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Cart_Item_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Vacation_ID")
    private Vacation vacation;

    @ManyToMany
    @JoinTable(name = "excursion_cartitem",
            joinColumns = @JoinColumn(name = "Cart_Item_ID"),
            inverseJoinColumns = @JoinColumn(name = "Excursion_ID")
    )
    private Set<Excursion> excursions;

    @ManyToOne
    @JoinColumn(name = "Cart_ID")
    private Cart cart;

    @Column(name = "Create_Date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "Last_Update")
    @UpdateTimestamp
    private Date last_update;

}