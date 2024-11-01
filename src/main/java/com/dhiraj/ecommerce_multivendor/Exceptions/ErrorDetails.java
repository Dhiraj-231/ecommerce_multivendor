package com.dhiraj.ecommerce_multivendor.Exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

    private String error;
    private String details;
    private LocalDateTime timestamp;
}