package com.activity.Activity.service;

import com.activity.Activity.DTO.ActivityDTO;
import com.activity.Activity.model.Activity;
import com.activity.Activity.model.Agency;
import com.activity.Activity.repository.ActivityRepository;
import com.activity.Activity.repository.AgencyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final AgencyRepository agencyRepository;
    private final ModelMapper mapper;

    @Autowired
    public ActivityService(ActivityRepository activityRepo, ModelMapper mapper, AgencyRepository agencyRepo){
        this.activityRepository = activityRepo;
        this.mapper = mapper;
        this.agencyRepository = agencyRepo;
    }

    public List<Activity> findAll(){
        return activityRepository.findAll();
    }

    public ResponseEntity<?> show(Long id){
        Map<String, Object> res = new HashMap<>();
        Activity activity = activityRepository.findById(id)
                .orElse(null);
        if(activity == null){
            res.put("message", "Activity Not Found !");
            return ResponseEntity.status(404)
                    .body(res);
        }

        res.put("data", activity);
        return ResponseEntity.ok().body(res);
    }

    public ResponseEntity<?> create(ActivityDTO activityDTO){
        Map<String, Object> res = new HashMap<>();
        try{
            Activity activity = mapper.map(activityDTO, Activity.class);
            Agency agency = agencyRepository.findById(activityDTO.getAgency())
                    .orElse(null);
            if(agency == null){
                res.put("message", "Agency Not Found");
                return ResponseEntity.status(409)
                        .body(res);
            }
            activity.setAgency(agency);
            activityRepository.save(activity);
            res.put("message", "Agency Was Created Successfully");
            return ResponseEntity.ok()
                    .body(res);
        }catch (RuntimeException e){
            res.put("message", e.getMessage());
            return ResponseEntity.status(500)
                    .body(res);
        }
    }

    public ResponseEntity<?> update(ActivityDTO activityDTO, Long id){
        Map<String, Object> res = new HashMap<>();
        Activity activity = activityRepository.findById(id)
                .orElse(null);
        if(activity == null){
            res.put("message", "Activity Not Found !");
            return ResponseEntity.status(404)
                    .body(res);
        }
        Optional.ofNullable(activityDTO.getDescription()).ifPresent(activity::setDescription);
        Optional.ofNullable(activityDTO.getThumbnail()).ifPresent(activity::setThumbnail);
        Optional.ofNullable(activityDTO.getLocation()).ifPresent(activity::setLocation);
        Optional.of(activityDTO.getDuration()).ifPresent(activity::setDuration);
        Optional.ofNullable(activityDTO.getHighlight()).ifPresent(activity::setHighlight);
        Optional.ofNullable(activityDTO.getMainCategory()).ifPresent(activity::setMainCategory);
        Optional.ofNullable(activityDTO.getTitle()).ifPresent(activity::setTitle);
        Optional.of(activityDTO.isFreeCancel()).ifPresent(activity::setFreeCancel);
        Optional.ofNullable(activityDTO.getIncludes()).ifPresent(activity::setIncludes);
        Optional.ofNullable(activityDTO.getNotIncluded()).ifPresent(activity::setNotIncluded);
        Optional.ofNullable(activityDTO.getNotSuitable()).ifPresent(activity::setNotSuitable);
        Optional.ofNullable(activityDTO.getMeetingPoint()).ifPresent(activity::setMeetingPoint);

        try{
            activityRepository.save(activity);
            res.put("message", "Activity Updated Successfully");
            return ResponseEntity.ok()
                    .body(res);
        }catch (DataAccessException e){
            res.put("message", "DATABASE Error: " + e.getMessage());
            return ResponseEntity.status(500)
                    .body(res);
        }catch (Exception e) {
            res.put("message", e.getMessage());
            return ResponseEntity.status(500)
                    .body(res);
        }
    }
    public ResponseEntity<?> delete(Long id){
        Map<String, Object> res = new HashMap<>();
        Activity activity = activityRepository.findById(id)
                        .orElse(null);
        if(activity == null){
            res.put("message", "Activity Was Not Found");
            return ResponseEntity.status(404)
                    .body(res);
        }
        activityRepository.delete(activity);
        res.put("message", "Activity Was Deleted Successfully");
        return ResponseEntity.ok()
                .body(res);
    }

}
