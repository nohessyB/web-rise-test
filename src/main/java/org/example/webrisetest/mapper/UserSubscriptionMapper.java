package org.example.webrisetest.mapper;

import org.example.webrisetest.dto.UserSubscriptionDto;
import org.example.webrisetest.entity.Subscription;
import org.example.webrisetest.entity.User;
import org.example.webrisetest.entity.UserSubscription;
import org.springframework.stereotype.Component;

@Component
public class UserSubscriptionMapper {
    public UserSubscription toUserSubscription(UserSubscriptionDto userSubscriptionDto, User user, Subscription subscription) {
        return UserSubscription.builder()
                .user(user)
                .subscription(subscription)
                .startDate(userSubscriptionDto.startDate())
                .endDate(userSubscriptionDto.endDate())
                .isActive(userSubscriptionDto.isActive())
                .build();
    }
}
