package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
import java.util.Set;

@Table (name = "DIVISIONS")
@Entity
@Getter
@Setter
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Division_ID")
    private Long id;

    @Column(name = "Division")
    private String division_name;

    @Column(name = "Create_Date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "Last_Update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne
    @JoinColumn (name = "Country_ID")
    private Country country;

    @Column(name = "Country_ID")
    private Long country_id;

    @OneToMany(mappedBy = "division")
    private Set<Customer> customers;

}
