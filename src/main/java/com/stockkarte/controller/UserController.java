package com.stockkarte.controller;

import com.stockkarte.exception.ResourceNotFoundException;
import com.stockkarte.models.User;
import com.stockkarte.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers () {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value = "id") long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        // TODO: user and all other return values should be put into the Response entity body
        return user;
    }

    @PostMapping("/user")
    public User createUser(@Valid @RequestBody User user) {
        // TODO: Password should be hashed
        return userRepository.save(user);
    }

    @PutMapping("/user")
    public User updateUser(@Valid @RequestBody User user)  throws ResourceNotFoundException{
        Long userId = user.getId();
        User userFromDb = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        userFromDb.setName(user.getName());
        userFromDb.setEmail(user.getEmail());
        // TODO: Password should be hashed
        userFromDb.setPassword(user.getPassword());
        return userRepository.save(userFromDb);
    }

    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value="id") Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
