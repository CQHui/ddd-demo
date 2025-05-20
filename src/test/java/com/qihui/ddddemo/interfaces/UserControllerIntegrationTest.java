package com.qihui.ddddemo.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qihui.ddddemo.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testUserCrud() throws Exception {
        // 1. 创建用户
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("123456");
        ResultActions createResult = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").value("testuser"));
        String response = createResult.andReturn().getResponse().getContentAsString();
        User createdUser = objectMapper.readValue(response, User.class);
        Long userId = createdUser.getId();

        // 2. 查询用户
        mockMvc.perform(get("/api/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"));

        // 3. 更新用户
        user.setUsername("updateduser");
        user.setEmail("updated@example.com");
        user.setPassword("654321");
        mockMvc.perform(put("/api/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("updateduser"));

        // 4. 查询所有用户
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists());

        // 5. 删除用户
        mockMvc.perform(delete("/api/users/" + userId))
                .andExpect(status().isOk());

        // 6. 查询已删除用户
        mockMvc.perform(get("/api/users/" + userId))
                .andExpect(status().is4xxClientError());
    }
} 