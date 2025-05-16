package org.example.webrisetest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.webrisetest.entity.Subscription;
import org.example.webrisetest.exception.SubscriptionNotFoundException;
import org.example.webrisetest.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public Subscription findById(Integer id) {
        log.debug("Find Subscription by id: {}", id);
        return subscriptionRepository.findById(id).
                orElseThrow(() -> {
                    log.warn("Subscription with id {} not found", id);
                    return new SubscriptionNotFoundException(
                            String.format("Subscription with id %s not found", id));
                });
    }

    public List<Subscription> findAll() {
        log.debug("Find all subscriptions");
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        log.info("Found {} subscriptions", subscriptions.size());
        return subscriptions;
    }

    public List<Subscription> findTopThreePopular() {
        log.debug("Fetching top 3 popular subscriptions");
        List<Subscription> topSubscriptions = subscriptionRepository.findTopThreePopular();
        if (topSubscriptions.isEmpty()) {
            log.warn("Top 3 popular subscriptions not found, no one user have subscriptions");
            throw new SubscriptionNotFoundException("Top 3 popular subscriptions not found");
        } else {
            log.info("Top 3 subscriptions: {}", topSubscriptions);
            return topSubscriptions;
        }
    }
}
