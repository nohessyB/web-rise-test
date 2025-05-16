package org.example.webrisetest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.webrisetest.entity.Subscription;
import org.example.webrisetest.service.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Subscription Utility Controller", description = "API для работы с подписками")
public class SubscriptionUtilityController {
    private final SubscriptionService subscriptionService;

    @Operation(
            summary = "Получить все подписки",
            description = "Возвращает список всех доступных подписок"
    )
    @GetMapping
    public ResponseEntity<List<Subscription>> subscriptions() {
        return new ResponseEntity<>(subscriptionService.findAll(), HttpStatus.OK);
    }

    @Operation(
            summary = "Получить топ-3 подписки",
            description = "Возвращает три самые популярные подписки"
    )
    @GetMapping("/top")
    public ResponseEntity<List<Subscription>> getTopThreeSubscriptions() {
        return new ResponseEntity<>(subscriptionService.findTopThreePopular(), HttpStatus.OK);
    }
}
