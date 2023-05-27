package com.dom.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dom.demo.model.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer>{
    
}
