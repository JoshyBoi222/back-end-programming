package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Table (name = "COUNTRIES")
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Country_ID")
    private Long id;

    @Column(name = "Country")
    private String country_name;

    @Column(name = "Create_Date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "Last_Update")
    @UpdateTimestamp
    private Date last_update;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private Set<Division> divisions;

}
