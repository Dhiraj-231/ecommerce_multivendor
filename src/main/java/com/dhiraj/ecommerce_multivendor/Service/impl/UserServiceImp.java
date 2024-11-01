package com.dhiraj.ecommerce_multivendor.Service.impl;

import org.springframework.stereotype.Service;

import com.dhiraj.ecommerce_multivendor.Config.JwtProvider;
import com.dhiraj.ecommerce_multivendor.Modals.User;
import com.dhiraj.ecommerce_multivendor.Repository.UserRepo;
import com.dhiraj.ecommerce_multivendor.Service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepo userRepo;
    private final JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwtToken) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwtToken);

        User user = this.findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepo.findByEmail(email);
        if (user == null)
            throw new Exception("User not found with email :- " + email);
        return user;
    }

}
