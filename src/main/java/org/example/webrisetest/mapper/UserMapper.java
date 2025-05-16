package org.example.webrisetest.mapper;

import org.example.webrisetest.dto.UserDto;
import org.example.webrisetest.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(UserDto dto) {
        return User.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .build();
    }
}
