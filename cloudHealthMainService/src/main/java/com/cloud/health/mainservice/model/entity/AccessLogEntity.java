package com.cloud.health.mainservice.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 12/21/2019
 * Time: 1:36 AM
 * Project: cloudHealthMainService
 */
@Entity
@Table(name = "access_log", schema = "smart_health_db", catalog = "")
public class AccessLogEntity {
    private int logId;
    private Timestamp accessTime;
    private UserEntity userByAccessedBy;

    @Id
    @Column(name = "log_id")
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "access_time")
    public Timestamp getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Timestamp accessTime) {
        this.accessTime = accessTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessLogEntity that = (AccessLogEntity) o;
        return logId == that.logId &&
                Objects.equals(accessTime, that.accessTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, accessTime);
    }

    @ManyToOne
    @JoinColumn(name = "accessed_by", referencedColumnName = "user_id", nullable = false)
    public UserEntity getUserByAccessedBy() {
        return userByAccessedBy;
    }

    public void setUserByAccessedBy(UserEntity userByAccessedBy) {
        this.userByAccessedBy = userByAccessedBy;
    }

}
