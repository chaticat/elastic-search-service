package com.chaticat.elasticsearchservice.chat.service;

import com.chaticat.elasticsearchservice.chat.model.Chat;
import com.chaticat.elasticsearchservice.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRepository chatRepository;

    @Async
    public void saveChat(Chat newChat) {
        updateIfExists(newChat);
        chatRepository.save(newChat).subscribe();
    }

    private void updateIfExists(Chat newChat) {
        chatRepository.findById(newChat.getId()).subscribe(chat -> {
            chat.setName(newChat.getName());
            chat.setIsPrivate(newChat.getIsPrivate());
            chat.setGroup(newChat.isGroup());
            chat.setParticipants(newChat.getParticipants());
            chatRepository.save(chat).subscribe();
        });
    }

    public Flux<Chat> findByName(String name, boolean isGlobal, String participantId) {
        var pageRequest = PageRequest.of(0, 100);
        return isGlobal
                ? chatRepository.findByNameLikeIgnoreCaseAndIsPrivateIsFalse(name, pageRequest)
                : chatRepository.findByNameLikeIgnoreCaseAndParticipantsId(name, participantId, pageRequest);
    }

    @Async
    public void delete(String chatId) {
        chatRepository.deleteById(chatId).subscribe();
    }
}
