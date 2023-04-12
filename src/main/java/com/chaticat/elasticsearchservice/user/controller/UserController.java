package com.chaticat.elasticsearchservice.user.controller;

import com.chaticat.elasticsearchservice.user.model.User;
import com.chaticat.elasticsearchservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public void save(@RequestBody User user) {
        userService.saveUser(user);
    }

    @GetMapping("/username")
    public List<User> getByUsername(@RequestParam("username") String username) {
        return userService.findByUsername(username);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable UUID userId) {
        userService.delete(userId);
    }
}
