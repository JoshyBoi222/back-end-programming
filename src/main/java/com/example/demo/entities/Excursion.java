package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
import java.util.Set;
import java.math.BigDecimal;


@Table(name = "EXCURSIONS")
@Entity
@Getter
@Setter
public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Excursion_ID")
    private Long id;

    @Column(name = "Excursion_Title")
    private String excursion_title;

    @Column(name = "Excursion_Price")
    private BigDecimal excursion_price;

    @Column(name = "Image_URL")
    private String image_URL;

    @Column(name = "Create_Date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "Last_Update")
    @UpdateTimestamp
    private Date last_update;
    
    @ManyToOne
    @JoinColumn(name = "Vacation_ID")
    private Vacation vacation;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "excursions")
    private Set<CartItem> cartitems;

}

