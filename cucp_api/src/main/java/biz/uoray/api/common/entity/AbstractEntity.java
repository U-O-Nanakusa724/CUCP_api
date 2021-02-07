package biz.uoray.api.common.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

    public abstract Date getCreatedAt();

    public abstract Date getUpdatedAt();

    public abstract void setCreatedAt(Date createdAt);

    public abstract void setUpdatedAt(Date updatedAt);

    @PrePersist
    public void onPrePersist() {
        if (getCreatedAt() == null) {
            setCreatedAt(new Date());
        }
        if (getUpdatedAt() == null) {
            setUpdatedAt(new Date());
        }
    }

    @PreUpdate
    public void onPreUpdate() {
        setUpdatedAt(new Date());
    }

}

