package com.BuilderAI.Project.client;

import com.BuilderAI.Project.dto.user.Response.UserProfileResponse;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "USER-SERVICE", path = "/api") // service name (Eureka or URL)
public interface UserClient {

    @GetMapping("/users/{username}")
    UserProfileResponse getUserByUsername(@PathVariable String username);

    @GetMapping("/users/{id}")
    UserProfileResponse getUserById(@PathVariable Long id);

    @GetMapping("/users/bulk")
    Map<Long, UserProfileResponse> getUsersByIds(@RequestBody List<Long> userIds);
}
