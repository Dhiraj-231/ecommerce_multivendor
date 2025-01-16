package com.dhiraj.ecommerce_multivendor.Modals;

import java.util.HashSet;
import java.util.Set;

import com.dhiraj.ecommerce_multivendor.Domin.USER_ROLE;
import static com.dhiraj.ecommerce_multivendor.Domin.USER_ROLE.ROLE_CUSTOMER;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.AUTO;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String email;
    private String fullName;
    private String mobile;
    private USER_ROLE role = ROLE_CUSTOMER;
    @OneToMany
    private Set<Address> address = new HashSet<>();
    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "user_coupons", inverseJoinColumns = @JoinColumn(name = "coupon_id"))
    private Set<Coupon> usedCoupons = new HashSet<>();
}
