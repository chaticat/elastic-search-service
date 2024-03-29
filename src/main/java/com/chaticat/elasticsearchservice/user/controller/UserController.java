package com.chaticat.elasticsearchservice.user.controller;

import com.chaticat.elasticsearchservice.user.model.User;
import com.chaticat.elasticsearchservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequestMapping("/search/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody User user) {
        userService.saveUser(user);
    }

    @GetMapping("/username")
    @ResponseStatus(HttpStatus.OK)
    public Flux<User> getByUsername(@RequestParam("searchText") String searchText,
                                    @RequestParam(value = "global") boolean global,
                                    @RequestParam(required = false) String contactId) {
        return userService.findByUsername(searchText, global, contactId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String userId) {
        userService.delete(userId);
    }
}
