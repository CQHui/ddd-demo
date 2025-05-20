package com.qihui.ddddemo.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Embedded;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String email;
    private String password;
    
    @Embedded
    private Profile profile;
    
    // 领域行为
    public void updateEmail(String newEmail) {
        if (newEmail != null && !newEmail.equals(this.email)) {
            this.email = newEmail;
        }
    }
    
    public void updatePassword(String newPassword) {
        if (newPassword != null && !newPassword.equals(this.password)) {
            this.password = newPassword;
        }
    }

    public void changeProfile(String nickname, String avatarUrl) {
        this.profile = new Profile(nickname, avatarUrl);
    }

    public void validatePasswordStrength() {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("密码强度不够");
        }
    }

    public void validateEmailFormat() {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("邮箱格式不正确");
        }
    }
} 