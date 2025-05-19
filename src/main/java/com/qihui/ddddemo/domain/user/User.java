package com.qihui.ddddemo.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
} 