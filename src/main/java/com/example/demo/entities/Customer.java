package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "CUSTOMERS")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Customer_ID")
    private Long id;

    @NotNull
    @Column(name = "First_Name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "Last_Name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "Address", nullable = false)
    private String address;

    @NotNull
    @Column(name = "City", nullable = false)
    private String city;

    @NotNull
    @Column(name = "Postal_Code", nullable = false)
    private String postal_code;

    @NotNull
    @Column(name = "Phone", nullable = false)
    private String phone;

    @Column(name = "Create_Date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "Last_Update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "Division_ID" , nullable = false)
    private Division division;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="customer")
    private Set<Cart> carts = new HashSet<>();

    public void addCart(Cart cart) {
        if (cart != null) {
            carts.add(cart);
            cart.setCustomer(this);
            cart.setStatus(StatusType.ordered);
        }
    }

}
