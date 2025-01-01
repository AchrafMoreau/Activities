package com.activity.Activity.repository;

import com.activity.Activity.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgencyRepository extends JpaRepository<Agency, Long> {
}
