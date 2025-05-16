package org.example.webrisetest.init;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.webrisetest.entity.Subscription;
import org.example.webrisetest.entity.Subscriptions;
import org.example.webrisetest.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionsInitialization {
    private final SubscriptionRepository subscriptionRepository;

    @PostConstruct
    public void init() {
        subscriptionRepository.save(new Subscription(Subscriptions.YANDEX_PLUS));
        subscriptionRepository.save(new Subscription(Subscriptions.YOUTUBE_PREMIUM));
        subscriptionRepository.save(new Subscription(Subscriptions.VK_MUSIC));
        subscriptionRepository.save(new Subscription(Subscriptions.NETFLIX));
        log.info("Subscriptions initialized");
    }

    @PreDestroy
    public void destroy() {
        subscriptionRepository.deleteAll();
        log.info("Subscriptions destroyed");
    }
}
