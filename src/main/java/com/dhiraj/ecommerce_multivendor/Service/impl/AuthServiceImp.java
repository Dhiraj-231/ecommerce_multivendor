package com.dhiraj.ecommerce_multivendor.Service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dhiraj.ecommerce_multivendor.Config.JwtProvider;
import com.dhiraj.ecommerce_multivendor.Domin.USER_ROLE;
import com.dhiraj.ecommerce_multivendor.Modals.Cart;
import com.dhiraj.ecommerce_multivendor.Modals.Seller;
import com.dhiraj.ecommerce_multivendor.Modals.User;
import com.dhiraj.ecommerce_multivendor.Modals.VerificationCode;
import com.dhiraj.ecommerce_multivendor.Repository.CartRepositiory;
import com.dhiraj.ecommerce_multivendor.Repository.SellerRepositiory;
import com.dhiraj.ecommerce_multivendor.Repository.UserRepo;
import com.dhiraj.ecommerce_multivendor.Repository.VerificationCodeRepositiory;
import com.dhiraj.ecommerce_multivendor.Request.LoginRequest;
import com.dhiraj.ecommerce_multivendor.Response.AuthResponse;
import com.dhiraj.ecommerce_multivendor.Response.SignupRequest;
import com.dhiraj.ecommerce_multivendor.Service.AuthService;
import com.dhiraj.ecommerce_multivendor.Service.EmailService;
import com.dhiraj.ecommerce_multivendor.utils.OtpUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final CartRepositiory cartRepositiory;
    private final JwtProvider jwtProvider;
    private final VerificationCodeRepositiory verificationCodeRepositiory;
    private final EmailService emailService;
    private final CustomUserServiceImp customUserServiceImp;
    private final SellerRepositiory sellerRepositiory;

    @Override
    public String registerUser(SignupRequest req) throws Exception {

        VerificationCode verificationCode = verificationCodeRepositiory.findByemail(req.getEmail());

        if (verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())) {
            throw new Exception("Invalid OTP...");
        }

        User user = userRepo.findByEmail(req.getEmail());

        if (user == null) {
            User createdUser = new User();
            createdUser.setEmail(req.getEmail());
            createdUser.setFullName(req.getFullName());
            createdUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createdUser.setMobile("9798964079");
            createdUser.setPassword(passwordEncoder.encode(req.getOtp()));

            userRepo.save(createdUser);

            Cart cart = new Cart();
            cart.setUser(createdUser);
            cartRepositiory.save(cart);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(authentication);
    }

    @Override
    public void sentLoginOTP(String email, USER_ROLE role) throws Exception {
        String SIGNING_PREFIX = "signin_";

        if (email.startsWith(SIGNING_PREFIX)) {
            email = email.substring(SIGNING_PREFIX.length());

            if (role.equals(USER_ROLE.ROLE_SELLER)) {
                Seller seller = sellerRepositiory.findByEmail(email);
                System.out.println("I got this..");
                if (seller == null) {
                    throw new Exception("Seller not exist with provided email");
                }

            } else {
                User user = userRepo.findByEmail(email);
                if (user == null) {
                    throw new Exception("User not exist with provided email");
                }
            }
        }

        VerificationCode isExist = verificationCodeRepositiory.findByemail(email);

        if (isExist != null) {
            verificationCodeRepositiory.delete(isExist);
        }

        String otp = OtpUtil.generateOtp();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);

        verificationCodeRepositiory.save(verificationCode);

        String subject = "Fun Bazzar Login/Signup OTP";
        String text = "Your login/Signup otp is - " + otp;

        emailService.sendVerificationOtpEmail(email, otp, subject, text);
    }

    @Override
    public AuthResponse loginUser(LoginRequest req) throws Exception {
        String username = req.getEmail();
        String otp = req.getOtp();

        Authentication authentication = authenticate(username, otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setMessage("Login Successfully....!");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

        authResponse.setRole(USER_ROLE.valueOf(roleName));

        return authResponse;
    }

    private Authentication authenticate(String username, String otp) {
        UserDetails userDetails = customUserServiceImp.loadUserByUsername(username);
        String SELLER_PREFIX = "seller_";
        if (username.startsWith(SELLER_PREFIX)) {
            username = username.substring(SELLER_PREFIX.length());

        }
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid Username..");
        }

        VerificationCode verificationCode = verificationCodeRepositiory.findByemail(username);
        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new BadCredentialsException(" Wrong Otp..");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
