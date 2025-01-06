package com.activity.Activity.repository;

import com.activity.Activity.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
