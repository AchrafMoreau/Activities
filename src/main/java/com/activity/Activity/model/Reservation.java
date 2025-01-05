package com.activity.Activity.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    user_id (one to many with User)
//    activit_id (one to many with Acitviy)
//    price
//    language
//    participant
//    date
//    reservation status
//    

}
