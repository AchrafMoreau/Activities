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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Operation(summary = "Get All Activities", description = "Retrive all Activities from the Database")
    @GetMapping("/")
    public List<Activity> findAll(){
        return activityService.findAll();
    }

    @Operation(summary = "Show One Activity By id", description = "Get One Activity from the Database")
    @GetMapping("/{id}")
    public Activity show(Long id){
        return activityService.show(id);
    }


    @PostMapping("/create")
    public String create(@RequestBody ActivityDTO activityDTO){
        activityService.create(activityDTO);
        return "Activity Was Created Successfully";
    }

    @PutMapping("/{id}/edit")
    public String edit(@RequestParam Long id, @RequestBody ActivityDTO activityDTO){
        activityService.update(activityDTO, id);
        return "Activity Was Updated Successfully";
    }

    @DeleteMapping("/{id}")
    public String delete(@RequestParam Long id){
        activityService.delete(id);
        return "Activity Was Deleted Successfully";
    }
}
