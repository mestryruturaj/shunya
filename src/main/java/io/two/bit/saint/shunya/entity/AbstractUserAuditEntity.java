package io.two.bit.saint.shunya.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AbstractUserAuditEntity extends AbstractAuditEntity {
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @PrePersist
    @PreUpdate
    public void ensureUpdatedBy() {
        if (this.updatedBy == null) {
            this.updatedBy = "SYSTEM"; // Default value
        }
    }
}
