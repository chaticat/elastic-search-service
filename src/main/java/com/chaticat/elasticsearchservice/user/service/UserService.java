package com.chaticat.elasticsearchservice.user.service;

import com.chaticat.elasticsearchservice.repository.UserRepository;
import com.chaticat.elasticsearchservice.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Async
    public void saveUser(User newUser) {
        updateIfExists(newUser);
        userRepository.save(newUser).subscribe();
    }

    private void updateIfExists(User newUser) {
        userRepository.findById(newUser.getId()).subscribe(user -> {
            user.setUsername(newUser.getUsername());
            userRepository.save(user).subscribe();
        });
    }

    public Flux<User> findByUsername(String username) {
        return userRepository.findByUsernameLikeIgnoreCase(username);
    }

    @Async
    public void delete(UUID userId) {
        userRepository.deleteById(userId).subscribe();
    }
}
