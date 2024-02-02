package io.ai.comandside.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Супер тип для сущностей и агрегатов(что по сути тоже является сущностью)
 * Содержит версию изменения сущностью, по умолчанию -1
 */
@MappedSuperclass
@Getter
public abstract class IdentifiedDomainObject {

    @Id
    @UuidGenerator
    protected UUID id;

    /**
     * Суррогатный идентификатор
     */
    @Column(name = "version")
    private long version = -1;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedDate;

    protected long getVersion() {
        return version;
    }

    protected void setVersion(long version) {
        this.version = version;
    }

    protected void nextVersion() {
        this.version++;
    }

    @PrePersist
    public void prePersist() {
        this.createdDate = this.createdDate == null ? LocalDateTime.now() : this.createdDate;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedDate = LocalDateTime.now();
    }
}
