package org.example.webrisetest.dto;

import java.time.LocalDateTime;

public record UserSubscriptionDto(
        Long userId,
        Integer subscriptionId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Boolean isActive
) {
}
