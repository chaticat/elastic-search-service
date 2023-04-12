package com.chaticat.elasticsearchservice.user.service;

import com.chaticat.elasticsearchservice.repository.UserRepository;
import com.chaticat.elasticsearchservice.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User newUser) {
        updateIfExists(newUser);
        userRepository.save(newUser);
    }

    private void updateIfExists(User newUser) {
        userRepository.findById(newUser.getId()).ifPresent(user -> {
            user.setUsername(newUser.getUsername());
            userRepository.save(user);
        });
    }

    public List<User> findByUsername(String username) {
        return userRepository.findByUsernameLikeIgnoreCase(username);
    }

    public void delete(UUID userId) {
        userRepository.deleteById(userId);
    }
}
