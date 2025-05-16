package org.example.webrisetest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.webrisetest.dto.UserSubscriptionDto;
import org.example.webrisetest.entity.Subscription;
import org.example.webrisetest.entity.User;
import org.example.webrisetest.entity.UserSubscription;
import org.example.webrisetest.exception.UserAlreadyHaveSubscriptionException;
import org.example.webrisetest.exception.UserSubscriptionNotFoundException;
import org.example.webrisetest.mapper.UserSubscriptionMapper;
import org.example.webrisetest.repository.UserSubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserSubscriptionService {
    private final UserSubscriptionRepository userSubscriptionRepository;
    private final UserService userService;
    private final SubscriptionService subscriptionService;
    private final UserSubscriptionMapper userSubscriptionMapper;

    @Transactional(readOnly = true)
    public List<UserSubscription> findAllByUserId(Long userId) {
        log.debug("Finding all user subscriptions by userId: {}", userId);

        log.info("Checking is user exists with userId {}", userId);
        userService.findById(userId);

        List<UserSubscription> userSubscriptionList =
                userSubscriptionRepository.findByUserId(userId);
        if (userSubscriptionList.isEmpty()) {
            log.warn("No subscriptions found for user with id: {}", userId);
            throw new UserSubscriptionNotFoundException(String.format("User with id %s has no subscriptions", userId));
        } else {
            log.info("Found {} subscriptions for user with id: {}", userSubscriptionList.size(), userId);
            return userSubscriptionList;
        }
    }

    public UserSubscription save(Long userId, UserSubscriptionDto userSubscriptionDto) {
        log.debug("Saving subscription for userId: {}, subscriptionId: {}",
                userId, userSubscriptionDto.subscriptionId());

        log.info("Checking if user exists with id: {}", userId);
        User user = userService.findById(userId);

        log.info("Checking if subscription exists with id: {}", userSubscriptionDto.subscriptionId());
        Subscription subscription = subscriptionService.findById(userSubscriptionDto.subscriptionId());

        log.info("Checking if user have subscription with id: {}", userSubscriptionDto.subscriptionId());
        Optional<UserSubscription> checkIfAlreadyExists = userSubscriptionRepository.
                findByUserIdAndSubscriptionId(userId, subscription.getId());
        if (checkIfAlreadyExists.isPresent()) {
            throw new UserAlreadyHaveSubscriptionException(
                    String.format("User with id %s has already subscription with id %s", userId, subscription.getId())
            );
        } else {
            UserSubscription saved = userSubscriptionRepository.save(
                    userSubscriptionMapper.toUserSubscription(userSubscriptionDto, user, subscription));
            log.info("Subscription with id {} saved for user with id {}", subscription.getId(), user.getId());
            return saved;
        }
    }

    public void deleteByUserIdAndSubscriptionId(Long userId, Integer subscriptionId) {
        log.debug("Deleting subscription with id {} for user with id {}", subscriptionId, userId);

        log.info("Checking if user exists with id: {}", userId);
        userService.findById(userId);

        log.info("Checking if subscription exists with id: {}", subscriptionId);
        subscriptionService.findById(subscriptionId);

        userSubscriptionRepository.findByUserIdAndSubscriptionId(userId, subscriptionId)
                .orElseThrow(() -> {
                    log.warn("User with id {} has no subscription with id {}", userId, subscriptionId);
                    return new UserSubscriptionNotFoundException(String.format("User with id %s has no subscription with id %s",
                            userId,
                            subscriptionId));
                });

        userSubscriptionRepository.deleteByUserIdAndSubscriptionId(userId, subscriptionId);
        log.info("Subscription with id {} deleted for user with id {}", subscriptionId, userId);
    }
}
