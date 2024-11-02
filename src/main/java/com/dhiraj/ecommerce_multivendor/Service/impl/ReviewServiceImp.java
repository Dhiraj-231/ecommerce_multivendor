package com.dhiraj.ecommerce_multivendor.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhiraj.ecommerce_multivendor.Modals.Product;
import com.dhiraj.ecommerce_multivendor.Modals.Review;
import com.dhiraj.ecommerce_multivendor.Modals.User;
import com.dhiraj.ecommerce_multivendor.Repository.ReviewRepository;
import com.dhiraj.ecommerce_multivendor.Request.CreateReviewRequest;
import com.dhiraj.ecommerce_multivendor.Service.ReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImp implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(CreateReviewRequest req, User user, Product product) {
        Review review = new Review();
        review.setReviewText(req.getReviewText());
        review.setRating(req.getReviewRating());
        review.setUser(user);
        review.setProduct(product);
        review.setProductImage(req.getProductImages());

        product.getReviews().add(review);

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Override
    public Review addReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception {
        Review review = getReviewById(reviewId);
        if (review.getUser().getId() == userId) {
            review.setReviewText(reviewText);
            review.setRating(rating);
            return reviewRepository.save(review);
        } else {
            throw new Exception("You are not authorized to update this review");
        }

    }

    @Override
    public void deleteReview(Long reviewId, Long userId) throws Exception {
        Review review = getReviewById(reviewId);
        if (review.getUser().getId() == userId) {
            reviewRepository.delete(review);
        } else {
            throw new Exception("You are not authorized to delete this review");
        }
    }

    @Override
    public Review getReviewById(Long reviewId) throws Exception {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new Exception("Review not found"));
    }

}
