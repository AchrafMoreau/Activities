package com.activity.Activity.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private boolean freeCancel;
    private String thumbnail;
    private double duration;

    @ManyToOne()
    @JoinColumn(name = "agency_id", nullable = false)
    private Agency agency;

    @ManyToMany(mappedBy = "activityList")
    private Set<Monument> monuments;

    @ManyToOne()
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

//    city_id one to many
//    category_id one to many

    @Enumerated(EnumType.STRING)
    private MainCategory mainCategory;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String location;

    @Column(columnDefinition = "TEXT")
    private String meetingPoint;

    @Column(columnDefinition = "TEXT")
    private String includes;

    @Column(columnDefinition = "TEXT")
    private String notIncluded;

    @Column(columnDefinition = "TEXT")
    private String highlight;

    @Column(columnDefinition = "TEXT")
    private String notSuitable;

    public Set<Monument> getMonuments(){
        return monuments;
    }
    public void setMonuments(Set<Monument> monuments){
        this.monuments = monuments;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFreeCancel() {
        return freeCancel;
    }

    public void setFreeCancel(boolean freeCancel) {
        this.freeCancel = freeCancel;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public MainCategory getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(MainCategory mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMeetingPoint() {
        return meetingPoint;
    }

    public void setMeetingPoint(String meetingPoint) {
        this.meetingPoint = meetingPoint;
    }

    public String getIncludes() {
        return includes;
    }

    public void setIncludes(String includes) {
        this.includes = includes;
    }

    public String getNotIncluded() {
        return notIncluded;
    }

    public void setNotIncluded(String notIncluded) {
        this.notIncluded = notIncluded;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getNotSuitable() {
        return notSuitable;
    }

    public void setNotSuitable(String notSuitable) {
        this.notSuitable = notSuitable;
    }
}
