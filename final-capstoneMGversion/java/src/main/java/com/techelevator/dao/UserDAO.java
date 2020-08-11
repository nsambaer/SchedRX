package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.ForgotPasswordDTO;
import com.techelevator.model.UpdatePasswordDTO;
import com.techelevator.model.User;

public interface UserDAO {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    int create(String username, String password, String role);
    
    void updatePassword(UpdatePasswordDTO user);
    
    void resetPassword(ForgotPasswordDTO user);
}
