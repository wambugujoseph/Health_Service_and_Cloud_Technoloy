package com.cloud.health.mainservice.model.entity;

import javax.persistence.*;
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
@Table(name = "practitioner", schema = "smart_health_db", catalog = "")
public class PractitionerEntity {
    private String practitionerId;
    private String userId;
    private String title;
    private String skill;
    private String description;
    private int healthUnitId;
    private UserEntity user;
    private HealthUnitEntity healthUnit;

    @Basic
    @Column(name = "user_id",insertable = false, updatable =false)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "practitioner_id")
    public String getPractitionerId() {
        return practitionerId;
    }

    public void setPractitionerId(String practionnerId) {
        this.practitionerId = practionnerId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "skill")
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String descrption) {
        this.description = descrption;
    }

    @Basic
    @Column(name = "health_unit_id", insertable = false, updatable = false)
    public int getHealthUnitId() {
        return healthUnitId;
    }

    public void setHealthUnitId(int healthUnitId) {
        this.healthUnitId = healthUnitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PractitionerEntity that = (PractitionerEntity) o;
        return Objects.equals(practitionerId, that.practitionerId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(skill, that.skill) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(practitionerId, title, skill, description);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity userByUserId) {
        this.user = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "health_unit_id", referencedColumnName = "health_unit_id", nullable = false)
    public HealthUnitEntity getHealthUnit() {
        return healthUnit;
    }

    public void setHealthUnit(HealthUnitEntity healthUnit) {
        this.healthUnit = healthUnit;
    }

}
