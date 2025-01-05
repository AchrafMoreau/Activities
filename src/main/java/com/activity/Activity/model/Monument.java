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



}
