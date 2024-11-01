package com.dhiraj.ecommerce_multivendor.Modals;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import static jakarta.persistence.GenerationType.AUTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
public class Category {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String name;

    @SuppressWarnings("deprecation")
    @NotNull
    @Column(unique = true)
    private String categoryId;

    @ManyToOne
    private Category parentCategory;

    @SuppressWarnings("deprecation")
    @NotNull
    private Integer level;

}
