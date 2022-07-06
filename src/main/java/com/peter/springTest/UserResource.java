package com.peter.springTest;

import com.peter.springTest.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserResource {
    @GetMapping("/{id}")
    public User getUser(String id){
        User user = new User(id, "peter");
        return user;
    }
}
