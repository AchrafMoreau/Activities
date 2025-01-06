package com.activity.Activity.repository;

import com.activity.Activity.model.Monument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonumentRepository extends JpaRepository<Monument, Long> {
}
