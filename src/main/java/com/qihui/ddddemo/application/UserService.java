package com.qihui.ddddemo.application;

import com.qihui.ddddemo.domain.user.User;
import com.qihui.ddddemo.domain.user.UserRegisteredEvent;
import com.qihui.ddddemo.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public User createUser(User user) {
        // 领域规则校验
        user.validateEmailFormat();
        user.validatePasswordStrength();
        // 用户名唯一性校验
        userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
            throw new IllegalArgumentException("用户名已存在");
        });
        User saved = userRepository.save(user);
        // 发布领域事件
        eventPublisher.publishEvent(new UserRegisteredEvent(saved.getId(), saved.getEmail()));
        return saved;
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new com.qihui.ddddemo.common.NotFoundException("User not found"));
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setUsername(userDetails.getUsername());
        user.updateEmail(userDetails.getEmail());
        user.updatePassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
} 