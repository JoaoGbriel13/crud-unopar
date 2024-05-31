package com.br.jg.PortfolioFaculdade.Resource;

import com.br.jg.PortfolioFaculdade.Entities.User;
import com.br.jg.PortfolioFaculdade.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable long id){
        return userService.findById(id);
    }
    @GetMapping
    public ResponseEntity getAll(){
        return userService.findAll();
    }
    @PostMapping
    public ResponseEntity createUser(@RequestBody User user){
        return userService.insert(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable long id, @RequestBody User user){
        return userService.update(id, user);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable long id){
        return userService.delete(id);
    }

}
