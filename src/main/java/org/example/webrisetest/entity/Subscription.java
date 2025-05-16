package org.example.webrisetest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private Subscriptions subscription;

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<UserSubscription> userSubscriptions;

    public Subscription(Subscriptions subscription) {
        this.subscription = subscription;
    }
}
