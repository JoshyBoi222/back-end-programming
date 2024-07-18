package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {
    public Customer(Division division, String firstName, String lastName, String address, String postal_code, String phone) {
        this.division = division;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postal_code = postal_code;
        this.phone = phone;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    @Column(name = "customer_first_name", nullable = false)
    private String firstName;
    @Column(name = "customer_last_name", nullable = false)
    private String lastName;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "postal_code", nullable = false)
    private String postal_code;
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;
    @ManyToOne
    @JoinColumn(name = "division_id" , nullable = false)
    private Division division;
    @OneToMany(cascade = CascadeType.ALL, mappedBy ="customer")
    private Set<Cart> carts = new HashSet<>();
    public void add(Cart cart) {
        if (cart != null) {
            if (carts == null) {
                carts = new HashSet<>();
            }
            carts.add(cart);
            cart.setCustomer(this);
        }
    }

}
