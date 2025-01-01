package com.activity.Activity.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role() {};
    public Role(ERole role){
        this.name = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
