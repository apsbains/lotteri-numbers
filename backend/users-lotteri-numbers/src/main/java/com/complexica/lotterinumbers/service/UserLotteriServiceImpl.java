package com.complexica.lotterinumbers.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexica.lotterinumbers.exceptions.UserNotFoundException;
import com.complexica.lotterinumbers.model.User;
import com.complexica.lotterinumbers.model.UsersGeneratedNumbers;
import com.complexica.lotterinumbers.repository.UserRepository;
import com.complexica.lotterinumbers.repository.UsersGeneratedNumberRepository;

@Service
public class UserLotteriServiceImpl implements UserLotteriService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersGeneratedNumberRepository generatedNumberRepository;

    public UsersGeneratedNumbers generatedNumber(String name) {

        User user = userRepository.findByNameIgnoreCase(name.trim())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        //TODO: Move to own function.
        List<Integer> numbers = IntStream.rangeClosed(1, 45).boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        List<Integer> generatedNumbers = numbers.subList(0, 6).stream().sorted().collect(Collectors.toList());

        UsersGeneratedNumbers generatedNumber = new UsersGeneratedNumbers();
        generatedNumber.setNumbers(generatedNumbers);
        generatedNumber.setGeneratedAt(LocalDateTime.now());
        generatedNumber.setUser(user);
        return generatedNumberRepository.save(generatedNumber);
    }

    public User addUser(User user) {
        
        Optional<User> existingUser = userRepository.findByNameIgnoreCase(user.getName().trim());
        return existingUser.orElseGet(() -> userRepository.save(user));
    }

    public List<UsersGeneratedNumbers> getUserNumbers(String name) throws UserNotFoundException {
        
        Optional<User> user = userRepository.findByNameIgnoreCase(name.trim());
        if (user.equals(null)) {
            throw new UserNotFoundException("User not found");
        }
        return generatedNumberRepository.findByUserOrderByGeneratedAtDesc(user);
    }

}
