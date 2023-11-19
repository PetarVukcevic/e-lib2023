package com.petarvukcevic.elib.services;

import com.petarvukcevic.elib.dto.command.UserCreateCommand;
import com.petarvukcevic.elib.dto.query.UserQuery;
import com.petarvukcevic.elib.entities.User;
import com.petarvukcevic.elib.mappers.UserMapper;
import com.petarvukcevic.elib.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void create(UserCreateCommand userCreateCommand)
    {
        User user = userMapper.toEntity(userCreateCommand);
        userRepository.save(user);
    }

//    public UserAuthRecord findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }

    public List<UserQuery> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toUserQuery)
                .toList();
    }

    public Optional<User> findByUsernameWithRoles(String username) {
        return userRepository.findByUsernameWithRoles(username);
    }

//    public String findEmailByUsername(String username) {
//        return userRepository.findEmailByUsername(username);
//    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
