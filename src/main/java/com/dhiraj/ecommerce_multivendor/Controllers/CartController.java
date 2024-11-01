package com.dhiraj.ecommerce_multivendor.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhiraj.ecommerce_multivendor.Exceptions.ProductException;
import com.dhiraj.ecommerce_multivendor.Modals.Cart;
import com.dhiraj.ecommerce_multivendor.Modals.CartItem;
import com.dhiraj.ecommerce_multivendor.Modals.Product;
import com.dhiraj.ecommerce_multivendor.Modals.User;
import com.dhiraj.ecommerce_multivendor.Request.AddItemRequest;
import com.dhiraj.ecommerce_multivendor.Response.ApiResponse;
import com.dhiraj.ecommerce_multivendor.Service.CartItemService;
import com.dhiraj.ecommerce_multivendor.Service.CartService;
import com.dhiraj.ecommerce_multivendor.Service.ProductService;
import com.dhiraj.ecommerce_multivendor.Service.impl.UserServiceImp;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserServiceImp userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Cart cart = cartService.finduserCart(user);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestHeader("Authorization") String jwt,
            @RequestBody AddItemRequest req) throws ProductException, Exception {

        User user = userService.findUserByJwtToken(jwt);
        Product product = productService.findProductById(req.getProductId());

        CartItem item = cartService.addCartItem(user, product, req.getSize(), req.getQuantity());

        ApiResponse res = new ApiResponse();
        res.setMessage("Item Added To Cart Successfully...");

        return new ResponseEntity<>(item, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItemHandler(@PathVariable Long cartItemId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse res = new ApiResponse();
        res.setMessage("Item Removed From Cart Successfully...");

        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemHandler(@PathVariable Long cartItemId, @RequestBody CartItem cartItem,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        CartItem updatedCartItem = null;

        if (cartItem.getQuantity() > 0) {
            updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
        }

        return new ResponseEntity<>(updatedCartItem, HttpStatus.ACCEPTED);
    }

}
