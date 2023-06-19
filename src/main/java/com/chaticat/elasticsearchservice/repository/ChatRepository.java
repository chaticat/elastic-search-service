package com.chaticat.elasticsearchservice.repository;

import com.chaticat.elasticsearchservice.chat.model.Chat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ChatRepository extends ReactiveCrudRepository<Chat, String> {

    Flux<Chat> findByNameLikeIgnoreCaseAndIsPrivateIsFalse(String name, Pageable pageable);

    Flux<Chat> findByNameLikeIgnoreCaseAndParticipantsId(String name, String participantId, Pageable pageable);
}
