package com.activity.Activity.DTO;

import com.activity.Activity.model.Activity;
import com.activity.Activity.model.City;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.Set;

public class MonumentDTO {

    private Long id;
    private String name;
    private String image;
    private Long city;
    private Set<Activity> activityList;

    public Set<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(Set<Activity> activityList) {
        this.activityList = activityList;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
