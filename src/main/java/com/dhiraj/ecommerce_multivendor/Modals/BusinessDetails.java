package com.dhiraj.ecommerce_multivendor.Modals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDetails {

    private String businessName;
    private String businnessEmail;
    private String businnessPhone;
    private String businessAddress;
    private String logo;
    private String banner;
}
