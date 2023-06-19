package com.chaticat.elasticsearchservice.repository;

import com.chaticat.elasticsearchservice.user.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;


public interface UserRepository extends ReactiveCrudRepository<User, String> {

    Flux<User> findByUsernameLikeIgnoreCaseAndIsPrivateIsFalse(String username, Pageable pageable);

    Flux<User> findByUsernameLikeIgnoreCaseAndContactsId(String name, String contactId, Pageable pageable);
}
