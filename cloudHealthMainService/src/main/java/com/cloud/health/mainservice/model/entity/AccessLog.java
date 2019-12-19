package com.cloud.health.mainservice.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Wednesday
 * Date: 12/18/2019
 * Time: 1:24 PM
 * Project: cloudHealthMainService
 */
@Entity
@Table(name = "access_log", schema = "smart_health_db", catalog = "")
public class AccessLog {
    private int logId;
    private Timestamp accessTime;
    private User userByAccessedBy;

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
        AccessLog accessLog = (AccessLog) o;
        return logId == accessLog.logId &&
                Objects.equals(accessTime, accessLog.accessTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, accessTime);
    }

    @ManyToOne
    @JoinColumn(name = "accessed_by", referencedColumnName = "user_id", nullable = false)
    public User getUserByAccessedBy() {
        return userByAccessedBy;
    }

    public void setUserByAccessedBy(User userByAccessedBy) {
        this.userByAccessedBy = userByAccessedBy;
    }
}
