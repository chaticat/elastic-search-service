package com.chaticat.elasticsearchservice.chat.controller;

import com.chaticat.elasticsearchservice.chat.model.Chat;
import com.chaticat.elasticsearchservice.chat.service.ChatService;
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

@RequestMapping("/search/chats")
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Chat chat) {
        chatService.saveChat(chat);
    }

    @GetMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Chat> getByName(@RequestParam String searchText,
                                @RequestParam boolean global,
                                @RequestParam(required = false) String participantId) {
        return chatService.findByName(searchText, global, participantId);
    }

    @DeleteMapping("/{chatId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String chatId) {
        chatService.delete(chatId);
    }
}
