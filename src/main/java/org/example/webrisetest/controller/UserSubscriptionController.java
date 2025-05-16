package org.example.webrisetest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.webrisetest.dto.UserSubscriptionDto;
import org.example.webrisetest.entity.UserSubscription;
import org.example.webrisetest.service.UserSubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{id}/subscriptions")
@RequiredArgsConstructor
@Tag(name = "User Subscriptions", description = "API для управления подписками пользователя")
public class UserSubscriptionController {
    private final UserSubscriptionService userSubscriptionService;

    @Operation(
            summary = "Получить подписки пользователя",
            description = "Возвращает список всех подписок указанного пользователя"
    )
    @GetMapping
    public ResponseEntity<List<UserSubscription>> getUserSubscriptions(@PathVariable Long id) {
        return new ResponseEntity<>(userSubscriptionService.findAllByUserId(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Добавить подписку пользователю",
            description = "Создает новую подписку для указанного пользователя"
    )
    @PostMapping()
    public ResponseEntity<UserSubscription> createUserSubscription(
            @PathVariable Long id,
            @RequestBody UserSubscriptionDto userSubscription) {
        return new ResponseEntity<>(userSubscriptionService.save(id, userSubscription), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Удалить подписку у пользователя",
            description = "Удаляет подписку по её идентификатору из таблицы subscriptions у указанного пользователя"
    )
    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<String> deleteUserSubscription(@PathVariable Long id,
                                                         @PathVariable Integer subscriptionId
    ) {
        userSubscriptionService.deleteByUserIdAndSubscriptionId(id, subscriptionId);
        return new ResponseEntity<>(String.format("Subscription was deleted from user with id %s", id),
                HttpStatus.OK);
    }
}
