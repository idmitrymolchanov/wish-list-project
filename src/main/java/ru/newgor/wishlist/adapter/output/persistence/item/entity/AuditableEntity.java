package ru.newgor.wishlist.adapter.output.persistence.item.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AuditableEntity {

    @Column(name = "create_date")
    private Instant createDate;
    @Column(name = "last_update_date")
    private Instant lastUpdateDate;

    @PrePersist
    protected void onCreate() {
        var currentTime = getCurrentTime();
        this.createDate = currentTime;
        this.lastUpdateDate = currentTime;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateDate = getCurrentTime();
    }

    private Instant getCurrentTime() {
        return Instant.now().truncatedTo(ChronoUnit.MICROS);
    }
}
