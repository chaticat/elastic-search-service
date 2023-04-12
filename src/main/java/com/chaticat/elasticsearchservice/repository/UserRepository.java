package com.chaticat.elasticsearchservice.repository;

import com.chaticat.elasticsearchservice.user.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface UserRepository extends ReactiveCrudRepository<User, UUID> {

    Flux<User> findByUsernameLikeIgnoreCase(String username);
}
