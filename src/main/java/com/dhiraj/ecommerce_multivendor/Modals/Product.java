package com.dhiraj.ecommerce_multivendor.Modals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.OneToMany;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import static jakarta.persistence.GenerationType.AUTO;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String title;
    private String description;
    private int mrpPrice;
    private int sellingPrice;
    private int discountPercent;
    private int quantity;
    private String color;
    @ElementCollection
    @Column(name = "image")
    private List<String> images = new ArrayList<>();
    private int numRatings;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Seller seller;
    private LocalDateTime createAt;
    private String sizes;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
    private boolean in_stock = true;
}
