package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Last_Name")
    private String lastName;

    @Column(name = "Address")
    private String address;

    @Column(name = "City")
    private String city;

    @Column(name = "Postal_Code")
    private String postal_code;

    @Column(name = "Phone")
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
