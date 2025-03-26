package com.vishnu.ExpenseTracker.service;

import com.vishnu.ExpenseTracker.entity.User;
import com.vishnu.ExpenseTracker.entity.UserModel;
import com.vishnu.ExpenseTracker.exceptions.ItemAlreadyExistsException;
import com.vishnu.ExpenseTracker.exceptions.ResourceNotFoundException;
import com.vishnu.ExpenseTracker.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject PasswordEncoder

    @Override
    public User createUser(UserModel userModel) {
        if (userRepository.existsByEmail(userModel.getEmail())) {
            throw new ItemAlreadyExistsException("User already registered with email: " + userModel.getEmail());
        }

        User newUser = new User();
        BeanUtils.copyProperties(userModel, newUser);

        // Encrypt the password before saving
        newUser.setPassword(passwordEncoder.encode(userModel.getPassword()));

        return userRepository.save(newUser);
    }

    @Override
    public User read(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    @Override
    public User update(User user, Long id) {
        User existingUser = read(id);

        if (user.getName() != null) existingUser.setName(user.getName());
        if (user.getEmail() != null && !user.getEmail().equals(existingUser.getEmail())) {
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new ItemAlreadyExistsException("Email is already in use: " + user.getEmail());
            }
            existingUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt updated password
        }
        if (user.getAge() != null) existingUser.setAge(user.getAge());

        return userRepository.save(existingUser);
    }

    @Override
    public void delete(Long id) {
        User user = read(id);
        userRepository.delete(user);
    }

    @Override
    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResourceNotFoundException("Invalid email or password");
        }

        return user; // Return user if authentication is successful
    }
}
