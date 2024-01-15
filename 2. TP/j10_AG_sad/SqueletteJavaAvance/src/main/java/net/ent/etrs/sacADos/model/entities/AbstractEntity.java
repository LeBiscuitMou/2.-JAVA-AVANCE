package net.ent.etrs.sacADos.model.entities;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@ToString(of = {"id", "createdAt", "updatedAt"})
public abstract class AbstractEntity implements Serializable {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "CREATED_AT")
    public LocalDate createdAt;

    @Getter
    @Column(name = "UPDATED_AT")
    public LocalDate updatedAt;

    @PrePersist
    protected void prePersist() {
        this.createdAt = LocalDate.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
