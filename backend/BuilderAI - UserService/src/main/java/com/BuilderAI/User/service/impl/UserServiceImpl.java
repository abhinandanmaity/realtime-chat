package com.BuilderAI.User.service.impl;

import com.BuilderAI.User.dto.auth.UserProfileResponse;
import com.BuilderAI.User.entity.User;
import com.BuilderAI.User.error.ResourceNotFoundException;
import com.BuilderAI.User.mapper.UserMapper;
import com.BuilderAI.User.repository.UserRepository;
import com.BuilderAI.User.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public UserProfileResponse getProfile(Long userId) {

        User user = userRepository.getReferenceById(userId);

        return userMapper.toUserProfileResponse(user);
    }

    @Override
    public UserProfileResponse getUserByUsername(String username) {

        Optional<User> user = userRepository.findByUsername(username);

        return userMapper.toUserProfileResponse(user.get());
    }

    @Override
    public UserProfileResponse getUserById(Long id) {

        User user = userRepository.getReferenceById(id);
        return userMapper.toUserProfileResponse(user);
    }

    @Override
    public Map<Long, UserProfileResponse> getUsersByIds(List<Long> userIds) {

        List<User> users = userRepository.findAllById(userIds);

        return users.stream()
                .collect(Collectors.toMap(
                        User::getId,
                        user -> new UserProfileResponse(
                                user.getId(),
                                user.getUsername(),
                                user.getName()
                        )
                ));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", username));
    }
}
