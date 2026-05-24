package com.ninety9group.userservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    @JsonProperty("created_at")
    private Long createdAt;

    @Column(name = "updated_at", nullable = false)
    @JsonProperty("updated_at")
    private Long updatedAt;

    @PrePersist
    protected void onCreate() {
        long nowMicros = System.currentTimeMillis() * 1000L;
        this.createdAt = nowMicros;
        this.updatedAt = nowMicros;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = System.currentTimeMillis() * 1000L;
    }
}