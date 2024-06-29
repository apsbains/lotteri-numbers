package com.complexica.lotterinumbers.service;

import java.util.List;

import com.complexica.lotterinumbers.exceptions.UserNotFoundException;
import com.complexica.lotterinumbers.model.User;
import com.complexica.lotterinumbers.model.UsersGeneratedNumbers;

public interface UserLotteriService {

    public abstract UsersGeneratedNumbers generatedNumber(String name) throws UserNotFoundException;
    public abstract User addUser(User user);
    public abstract List<UsersGeneratedNumbers> getUserNumbers(String name) throws UserNotFoundException;

    
}
