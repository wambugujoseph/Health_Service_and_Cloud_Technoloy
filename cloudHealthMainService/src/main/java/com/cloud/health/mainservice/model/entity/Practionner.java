package com.cloud.health.mainservice.model.entity;

import javax.persistence.*;
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
public class Practionner {
    private String practionnerId;
    private String title;
    private String skill;
    private String healthUnitId;
    private String descrption;
    private User userByUserId;

    @Id
    @Column(name = "practionner_id")
    public String getPractionnerId() {
        return practionnerId;
    }

    public void setPractionnerId(String practionnerId) {
        this.practionnerId = practionnerId;
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
    @Column(name = "health_unit_id")
    public String getHealthUnitId() {
        return healthUnitId;
    }

    public void setHealthUnitId(String healthUnitId) {
        this.healthUnitId = healthUnitId;
    }

    @Basic
    @Column(name = "descrption")
    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Practionner that = (Practionner) o;
        return Objects.equals(practionnerId, that.practionnerId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(skill, that.skill) &&
                Objects.equals(healthUnitId, that.healthUnitId) &&
                Objects.equals(descrption, that.descrption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(practionnerId, title, skill, healthUnitId, descrption);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
