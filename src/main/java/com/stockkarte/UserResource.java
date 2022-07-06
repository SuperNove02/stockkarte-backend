package com.stockkarte;

import com.stockkarte.models.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserResource {
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id")String id){
        System.out.println(id);
        User user = new User(id, "peter");
        return user;
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        return user;
    }
}
