package com.complexica.lotterinumbers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.complexica.lotterinumbers.model.User;
import com.complexica.lotterinumbers.model.UsersGeneratedNumbers;
import com.complexica.lotterinumbers.service.UserLotteriService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserLotteriService userLotteriService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user) {

        return userLotteriService.addUser(user);
    }

    @PostMapping("/users/{name}/numbers")
    @ResponseStatus(HttpStatus.CREATED)
    public UsersGeneratedNumbers generateNumber(@PathVariable String name) {
             
        return userLotteriService.generatedNumber(name);
    }

    @GetMapping("/users/{name}/numbers")
    public ResponseEntity<List<UsersGeneratedNumbers>> getUserNumbers(@PathVariable String name) {

        return new ResponseEntity<>(userLotteriService.getUserNumbers(name), HttpStatus.OK);
    }
}
