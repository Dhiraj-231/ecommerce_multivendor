package com.dhiraj.ecommerce_multivendor.Modals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BankDetails {

    private String accountNumber;
    private String accountHolderName;
    private String ifscCode;
}
