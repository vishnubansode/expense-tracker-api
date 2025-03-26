package com.vishnu.ExpenseTracker.service;

import com.vishnu.ExpenseTracker.entity.User;
import com.vishnu.ExpenseTracker.entity.UserModel;

public interface UserService {
    User createUser(UserModel userModel);
    User read(Long id);
    User update(User user, Long id);
    void delete(Long id);

    // Correct authenticate method definition
    User authenticate(String email, String password);
}
