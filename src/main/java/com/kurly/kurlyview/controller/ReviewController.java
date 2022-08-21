package com.kurly.kurlyview.controller;

import com.kurly.kurlyview.domain.review.Review;
import com.kurly.kurlyview.dto.LeaveReviewRequestDto;
import com.kurly.kurlyview.service.ProductService;
import com.kurly.kurlyview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api")
@RestController
public class ReviewController {
    private final ProductService productService;
    private final ReviewService reviewService;

    /**
     * 리뷰 작성하기
     */
    @PostMapping("/products/{productId}/reviews")
    public ResponseEntity<?> postReview(@RequestHeader("X-ACCESS-TOKEN") String token,
                                        @PathVariable String productId,
                                        @RequestBody LeaveReviewRequestDto dto){
        return ResponseEntity.ok(reviewService.leaveReview(token, productId, dto));
    }

    /**
     * 상품 리뷰 목록
     */
    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<?> getProductReviews(@PathVariable String productId){
        return ResponseEntity.ok(
                Review.Reviews
                        .builder()
                        .reviews(reviewService.findProductReviews(productId))
                        .build());
    }

    /**
     * 사용자 리뷰 목록
     */
    @GetMapping("/users/{memberId}/reviews")
    public ResponseEntity<?> getUserReviews(@PathVariable String memberId) {
        return ResponseEntity.ok(reviewService.findUserReviews(memberId));
    }
}
