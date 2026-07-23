package com.futagend.preferences;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "user_preference")
public class Preference {
    @Id private UUID id;
    @Column(name = "user_id", nullable = false) private UUID userId;
    @Column(nullable = false) private String category;
    @Column(name = "canonical_id", nullable = false) private String canonicalId;
    @Column(nullable = false) private String label;
    @Column(name = "created_at", nullable = false) private Instant createdAt;

    protected Preference() {}

    public Preference(UUID userId, String category, String canonicalId, String label) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.category = category;
        this.canonicalId = canonicalId;
        this.label = label;
        this.createdAt = Instant.now();
    }

    public UUID getId() { return id; }
    public String getCategory() { return category; }
    public String getCanonicalId() { return canonicalId; }
    public String getLabel() { return label; }
}

