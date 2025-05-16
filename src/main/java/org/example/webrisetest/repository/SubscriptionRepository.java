package org.example.webrisetest.repository;

import org.example.webrisetest.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    @Query(value = """
            SELECT s.* FROM subscriptions as s
            JOIN users_subscriptions as us ON s.id = us.subscription_id
            GROUP BY s.id
            ORDER BY COUNT(us.id) DESC
            LIMIT 3
            """, nativeQuery = true)
    List<Subscription> findTopThreePopular();
}
