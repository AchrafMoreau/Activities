package com.activity.Activity.controller;

import com.activity.Activity.DTO.ActivityDTO;
import com.activity.Activity.DTO.AgencyDTO;
import com.activity.Activity.model.Activity;
import com.activity.Activity.model.Agency;
import com.activity.Activity.model.MainCategory;
import com.activity.Activity.repository.ActivityRepository;
import com.activity.Activity.repository.AgencyRepository;
import com.activity.Activity.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService){
        this.activityService = activityService;
    }

    @Operation(summary = "Get All Activities", description = "Display all the Activities", tags = {"Activity Management"})
    @GetMapping("/")
    public List<Activity> findAll(){
        return activityService.findAll();
    }

    @Operation(
            summary = "Show an activity",
            description = "Show an activity based on its unique ID",
            tags = {"Activity Management"}
    )
    @GetMapping("/{activity}")
    public ResponseEntity<?> show(
            @Parameter(description = "ID of the activity to show", required = true)
            @PathVariable Long activity
    ){
        return activityService.show(activity);
    }


    @PostMapping("/")
    @Operation(
            summary = "Create an activity",
            description = "Creates a new activity based on the details provided in the request body",
            tags = {"Activity Management"}
    )
    public ResponseEntity<?> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the activity to create")
            @RequestBody ActivityDTO activityDTO
    ){
        return activityService.create(activityDTO);
    }

    @PutMapping("/{activity}")
    @Operation(
            summary = "Update an activity",
            description = "Update an activity by its unique ID",
            tags = {"Activity Management"}
    )
    public ResponseEntity<?> edit(
            @Parameter(description = "ID of the activity to update", required = true)
            @PathVariable Long activity,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the activity to update")
            @RequestBody ActivityDTO activityDTO
    ){
        return activityService.update(activityDTO, activity);
    }

    @DeleteMapping("/{activity}")
    @Operation(
            summary = "Delete an activity",
            description = "Deletes an activity by its unique ID",
            tags = {"Activity Management"}
    )
    public ResponseEntity<?> delete(
            @Parameter(description = "ID of the activity to delete", required = true)
            @PathVariable Long activity){
        return activityService.delete(activity);
    }
}
