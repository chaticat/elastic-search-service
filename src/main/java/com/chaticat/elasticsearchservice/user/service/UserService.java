package com.chaticat.elasticsearchservice.user.service;

import com.chaticat.elasticsearchservice.repository.UserRepository;
import com.chaticat.elasticsearchservice.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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
            user.setIsPrivate(newUser.getIsPrivate());
            userRepository.save(user).subscribe();
        });
    }

    public Flux<User> findByUsername(String username, boolean isGlobal, String contactId) {
        var pageRequest = PageRequest.of(0, 100);
        return isGlobal
                ? userRepository.findByUsernameLikeIgnoreCaseAndIsPrivateIsFalse(username, pageRequest)
                : userRepository.findByUsernameLikeIgnoreCaseAndContactsId(username, contactId, pageRequest);
    }

    @Async
    public void delete(String userId) {
        userRepository.deleteById(userId).subscribe();
    }
}
