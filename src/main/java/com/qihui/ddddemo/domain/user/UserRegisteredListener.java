package com.qihui.ddddemo.domain.user;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredListener {
    @EventListener
    public void onUserRegistered(UserRegisteredEvent event) {
        // 这里可以发送邮件、写日志等
        System.out.println("欢迎新用户注册: " + event.getEmail());
    }
} 