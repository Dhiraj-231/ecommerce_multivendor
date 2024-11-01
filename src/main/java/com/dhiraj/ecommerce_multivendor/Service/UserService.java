package com.dhiraj.ecommerce_multivendor.Service;

import com.dhiraj.ecommerce_multivendor.Modals.User;

public interface UserService {

    User findUserByJwtToken(String jwtToken) throws Exception;

    User findUserByEmail(String email) throws Exception;
}
