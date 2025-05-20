package com.qihui.ddddemo.domain.user;

public class UserRegisteredEvent {
    private final Long userId;
    private final String email;

    public UserRegisteredEvent(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public Long getUserId() { return userId; }
    public String getEmail() { return email; }
} 