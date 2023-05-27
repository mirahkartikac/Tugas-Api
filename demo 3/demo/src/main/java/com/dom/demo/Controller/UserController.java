package com.dom.demo.Controller;

import com.dom.demo.ErorrHandler.NotFound;
import com.dom.demo.model.Users;

import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

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

import com.dom.demo.repository.UsersRepository;

@RestController
@RequestMapping("/api")
public class UserController {
    
    @Autowired
    UsersRepository usersRepository;


    @GetMapping("/Users")
    public List<Users> getAll(){
        return usersRepository.findAll();
    }

    @PostMapping("/Users")
    public Users insertUsers(@Validated @RequestBody Users users){
        return usersRepository.save(users);
    }

    @GetMapping("/Users/{id}")
    public Users getUsers(@PathVariable(value="id") int id){
        return usersRepository.findById(id).orElseThrow(()-> new NotFound("Users", "id", id));
    }

    @PutMapping("/Users/{id}")
    public Users updatUsers(@PathVariable(value="id") int id, @Validated @RequestBody Users userDetail){
        Users users = usersRepository.findById(id).orElseThrow(()-> new NotFound("Users", "id", id));
        users.setId(userDetail.getId());
        users.setFirst_name(userDetail.getFirst_name());
        users.setLast_name(userDetail.getLast_name());
        users.setEmail(userDetail.getEmail());
        users.setPhone_number(userDetail.getPhone_number());
        users.setType(userDetail.getType());
        Users updateUsers = usersRepository.save(users);
        return updateUsers;
    }

    @DeleteMapping("/Users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value="id") int id){
        Users users = usersRepository.findById(id).orElseThrow(()-> new NotFound("Users", "id", id));
        usersRepository.delete(users);
        return ResponseEntity.ok().build();
    }

}
