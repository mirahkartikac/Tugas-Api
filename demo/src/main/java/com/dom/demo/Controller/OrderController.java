package com.dom.demo.Controller;

import com.dom.demo.ErorrHandler.NotFound;
import com.dom.demo.model.Orders;

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

import com.dom.demo.repository.OrdersRepository;

@RestController
@RequestMapping("/api")

public class OrderController {
    
    @Autowired
    OrdersRepository ordersRepository;

    @GetMapping("/Orders")
    public List<Orders> getAll(){
        return ordersRepository.findAll();
    }

    @PostMapping("/Orders")
    public Orders insertOrders(@Validated @RequestBody Orders orders){
        return ordersRepository.save(orders);
    }

    @GetMapping("/Orders/{id}")
    public Orders getOrders(@PathVariable(value="id") int id){
        return ordersRepository.findById(id).orElseThrow(()-> new NotFound("Orders", "id", id));
    }

    @PutMapping("/Orders/{id}")
    public Orders updateOrders(@PathVariable(value="id") int id, @Validated @RequestBody Orders orderDetail){
        Orders orders = ordersRepository.findById(id).orElseThrow(()-> new NotFound("Orders", "id", id));
        orders.setId(orderDetail.getId());
        orders.setBuyer(orderDetail.getBuyer());
        orders.setNote(orderDetail.getNote());
        orders.setTotal(orderDetail.getTotal());
        orders.setDiscount(orderDetail.getDiscount());
        orders.setIs_paid(orderDetail.getIs_paid());
        Orders updateOrders = ordersRepository.save(orders);
        return updateOrders;
    }

    @DeleteMapping("/Orders/{id}")
    public ResponseEntity<?> deleteOrders(@PathVariable(value="id") int id){
        Orders orders = ordersRepository.findById(id).orElseThrow(()-> new NotFound("Orders", "id", id));
        ordersRepository.delete(orders);
        return ResponseEntity.ok().build();
    }

}
