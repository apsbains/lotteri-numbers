package com.complexica.lotterinumbers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.complexica.lotterinumbers.model.User;
import com.complexica.lotterinumbers.model.UsersGeneratedNumbers;
import com.complexica.lotterinumbers.repository.UserRepository;
import com.complexica.lotterinumbers.repository.UsersGeneratedNumberRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Collections;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersGeneratedNumberRepository generatedNumberRepository;

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
                
        //TODO: Move this code to the service layer
        Optional<User> existingUser = userRepository.findByNameIgnoreCase(user.getName().trim());
        return existingUser.orElseGet(() -> userRepository.save(user));
    }

    @PostMapping("/users/{name}/numbers")
    public UsersGeneratedNumbers generateNumber(@PathVariable String name) {
        
        //TODO: Move this code to the service layer and create exception handler globally
        User user = userRepository.findByNameIgnoreCase(name.trim())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Integer> numbers = IntStream.rangeClosed(1, 45).boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        List<Integer> generatedNumbers = numbers.subList(0, 6).stream().sorted().collect(Collectors.toList());

        UsersGeneratedNumbers generatedNumber = new UsersGeneratedNumbers();
        generatedNumber.setNumbers(generatedNumbers);
        generatedNumber.setGeneratedAt(LocalDateTime.now());
        generatedNumber.setUser(user);
        return generatedNumberRepository.save(generatedNumber);
    }

    @GetMapping("/users/{name}/numbers")
    public List<UsersGeneratedNumbers> getUserNumbers(@PathVariable String name) {
        User user = userRepository.findByNameIgnoreCase(name.trim())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return generatedNumberRepository.findByUserOrderByGeneratedAtDesc(user);
    }
}
