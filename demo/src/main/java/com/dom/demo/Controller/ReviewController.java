package com.dom.demo.Controller;

import com.dom.demo.ErorrHandler.NotFound;
import com.dom.demo.model.Review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dom.demo.repository.ReviewRepository;

@RestController
@RequestMapping("/api")
public class ReviewController {
    
    @Autowired
    ReviewRepository reviewRepository;

    @GetMapping("/Review")
    public List<Review> getAll(){
        return reviewRepository.findAll();
    }

    @PostMapping("/Review")
    public Review insertReview(@Validated @RequestBody Review review){
        return reviewRepository.save(review);
    }

    @GetMapping("/Review/{ordert}")
    public Review getReview(@PathVariable(value="ordert") int id){
        return reviewRepository.findById(id).orElseThrow(()-> new NotFound("Review", "ordert", id));
    }

    @PutMapping("/Review/{ordert}")
    public Review updateReview(@PathVariable(value="ordert") int id, @Validated @RequestBody Review reviewDetail){
        Review review = reviewRepository.findById(id).orElseThrow(()-> new NotFound("Review", "ordert", id));
        review.setOrdert(reviewDetail.getOrdert());
        review.setStar(reviewDetail.getStar());
        review.setDescription(reviewDetail.getDescription());
        Review updateReview = reviewRepository.save(review);
        return updateReview;
    }

    @DeleteMapping("/Review/{ordert}")
    public ResponseEntity<?> deleteReview(@PathVariable(value="ordert") int id){
        Review orderdeReview = reviewRepository.findById(id).orElseThrow(()-> new NotFound("Review", "ordert", id));
        reviewRepository.delete(orderdeReview);
        return ResponseEntity.ok().build();
    }
}
