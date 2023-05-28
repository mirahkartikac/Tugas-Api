package com.dom.demo.Controller;

import com.dom.demo.ErorrHandler.NotFound;
import com.dom.demo.model.Orderdetail;

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

import com.dom.demo.repository.OrderdetailRepository;

@RestController
@RequestMapping("/api")
public class OrderdetailController {
    
    @Autowired
    OrderdetailRepository orderdetailRepository;

    @GetMapping("/Orderdetail")
    public List<Orderdetail> getAll(){
        return orderdetailRepository.findAll();
    }

    @PostMapping("/Orderdetail")
    public Orderdetail insertOrderdetail(@Validated @RequestBody Orderdetail orderdetail){
        return orderdetailRepository.save(orderdetail);
    }

    @GetMapping("/Orderdetail/{ordert}")
    public Orderdetail getOrderdetail(@PathVariable(value="ordert") int id){
        return orderdetailRepository.findById(id).orElseThrow(()-> new NotFound("Orderdetail", "ordert", id));
    }

    @PutMapping("/Orderdetail/{ordert}")
    public Orderdetail updateOrderdetail(@PathVariable(value="ordert") int id, @Validated @RequestBody Orderdetail o){
        Orderdetail orderdetail = orderdetailRepository.findById(id).orElseThrow(()-> new NotFound("Orderdetail", "ordert", id));
        orderdetail.setOrder(o.getOrder());
        orderdetail.setProduct(o.getProduct());
        orderdetail.setQuantity(o.getQuantity());
        orderdetail.setPrice(o.getPrice());
        Orderdetail updateOrderdetail = orderdetailRepository.save(orderdetail);
        return updateOrderdetail;
    }

    @DeleteMapping("/Orderdetail/{ordert}")
    public ResponseEntity<?> deleteOrderdetail(@PathVariable(value="ordert") int id){
        Orderdetail orderdeOrderdetail = orderdetailRepository.findById(id).orElseThrow(()-> new NotFound("Orderdetail", "ordert", id));
        orderdetailRepository.delete(orderdeOrderdetail);
        return ResponseEntity.ok().build();
    }
}
