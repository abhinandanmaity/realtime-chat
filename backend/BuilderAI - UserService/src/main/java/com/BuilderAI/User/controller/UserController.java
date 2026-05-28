package com.BuilderAI.User.controller;


import com.BuilderAI.User.dto.auth.UserProfileResponse;
import com.BuilderAI.User.entity.User;
import com.BuilderAI.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserProfileResponse> getUserByUsername(@PathVariable String username) {

        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponse> getUserById(@PathVariable Long id) {

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/users/bulk")
    public ResponseEntity<Map<Long, UserProfileResponse>> getUsersByIds(
                @RequestBody List<Long> userIds) {

        return ResponseEntity.ok(userService.getUsersByIds(userIds));
    }
}
