package com.ninety9group.userservice.controller;

import com.ninety9group.userservice.exception.ResourceNotFoundException;
import com.ninety9group.userservice.model.User;
import com.ninety9group.userservice.service.UserService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("User service is live!");
    }

    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> getAllUsers(
            @RequestParam(value = "page_num", defaultValue = "1") @Min(value = 1, message = "invalid page_num") int pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") @Min(value = 1, message = "invalid page_size") int pageSize) {

        Page<User> page = userService.getAllUsers(pageNum, pageSize);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("result", true);
        response.put("users", page.getContent());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("result", true);
        response.put("user", user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> createUser(
            @RequestParam(value = "name") @NotBlank(message = "name is required") String name) {

        User created = userService.createUser(name.trim());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("result", true);
        response.put("user", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
