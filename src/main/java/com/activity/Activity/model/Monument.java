package com.activity.Activity.model;

import jakarta.persistence.*;
import org.springframework.data.geo.Circle;

import java.util.Set;

@Entity
@Table(name = "monuments")
public class Monument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String image;

    @ManyToOne()
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToMany()
    @JoinTable(
            name = "monument_activity",
            joinColumns = @JoinColumn(name = "monument_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private Set<Activity> activityList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(Set<Activity> activityList) {
        this.activityList = activityList;
    }
}
