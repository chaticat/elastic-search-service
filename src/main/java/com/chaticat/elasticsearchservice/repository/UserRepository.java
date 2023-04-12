package com.chaticat.elasticsearchservice.repository;

import com.chaticat.elasticsearchservice.user.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends ElasticsearchRepository<User, UUID> {

    List<User> findByUsernameLikeIgnoreCase(String username);
}
