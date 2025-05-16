package org.example.webrisetest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.webrisetest.dto.UserDto;
import org.example.webrisetest.entity.User;
import org.example.webrisetest.exception.UserNotFoundException;
import org.example.webrisetest.mapper.UserMapper;
import org.example.webrisetest.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public User findById(Long id) {
        log.debug("Finding user with id: {}", id);
        return userRepository.findById(id).
                orElseThrow(() -> {
                    log.warn("User with id {} not found", id);
                    return new UserNotFoundException(String.format("User with id %s not found", id));
                });
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        log.debug("Finding all users");
        return userRepository.findAll();
    }

    public User save(UserDto dto) {
        log.debug("Saving new user: {}", dto);

        User savedUser = userRepository.save(userMapper.toUser(dto));
        log.info("User saved with id: {}", savedUser.getId());
        return savedUser;
    }

    public User update(Long id, UserDto dto) {
        log.debug("Updating user with id: {}", id);

        log.info("Checking if user with id: {} exists and finding him", dto);
        User user = findById(id);
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        log.info("User with id {} updated", id);
        return user;
    }

    public void delete(Long id) {
        log.debug("Deleting user with id: {}", id);

        log.info("Checking if user with id: {} exists", id);
        findById(id);

        userRepository.deleteById(id);
        log.info("User with id {} deleted", id);
    }
}
