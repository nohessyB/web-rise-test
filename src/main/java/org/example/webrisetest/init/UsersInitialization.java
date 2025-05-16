package org.example.webrisetest.init;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.webrisetest.entity.User;
import org.example.webrisetest.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersInitialization {
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        userRepository.save(new User("Cristiano", "Ronaldo"));
        userRepository.save(new User("Lionel", "Messi"));
        userRepository.save(new User("Poul", "Pogba"));
        log.info("Users initialized");
    }

    @PreDestroy
    public void destroy() {
        userRepository.deleteAll();
        log.info("Users destroyed");
    }
}
