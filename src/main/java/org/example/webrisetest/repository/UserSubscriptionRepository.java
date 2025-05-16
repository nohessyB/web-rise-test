package org.example.webrisetest.repository;

import org.example.webrisetest.entity.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {
    @Query("select us from UserSubscription us join fetch us.subscription where us.user.id = :userId")
    List<UserSubscription> findByUserId(Long userId);

    Optional<UserSubscription> findByUserIdAndSubscriptionId(Long userId, Integer subscriptionId);

    void deleteByUserIdAndSubscriptionId(Long userId, Integer subscriptionId);
}
