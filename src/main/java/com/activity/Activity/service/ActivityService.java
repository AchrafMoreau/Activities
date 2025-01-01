package com.activity.Activity.service;

import com.activity.Activity.DTO.ActivityDTO;
import com.activity.Activity.model.Activity;
import com.activity.Activity.model.Agency;
import com.activity.Activity.repository.ActivityRepository;
import com.activity.Activity.repository.AgencyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void update(ActivityDTO activityDTO, Long id){
        try{
            Activity activity = activityRepository.findById(id)
                    .orElseThrow(()-> new IllegalArgumentException("Activity Not Found"));

            activity.setDescription(activityDTO.getDescription());
            activity.setThumbnail(activityDTO.getThumbnail());
            activity.setLocation(activityDTO.getLocation());
            activity.setDuration(activityDTO.getDuration());
            activity.setHighlight(activityDTO.getHighlight());
            activity.setMainCategory(activityDTO.getMainCategory());
            activity.setTitle(activityDTO.getTitle());
            activity.setFreeCancel(activityDTO.isFreeCancel());
            activity.setIncludes(activityDTO.getIncludes());
            activity.setNotIncluded(activityDTO.getNotIncluded());
            activity.setNotSuitable(activityDTO.getNotSuitable());
            activity.setMeetingPoint(activityDTO.getMeetingPoint());
            activityRepository.save(activity);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void create(ActivityDTO activityDTO){
        try{
            Activity activity = mapper.map(activityDTO, Activity.class);
            Agency agency = agencyRepository.findById(activityDTO.getAgency())
                    .orElseThrow(() -> new IllegalArgumentException("Agency Not Found"));
            activity.setAgency(agency);
            activityRepository.save(activity);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(Long id){
        Activity activity = activityRepository.findById(id)
                        .orElseThrow(()-> new IllegalArgumentException("Activity Was Not Found"));
        activityRepository.delete(activity);
    }

    public Activity show(Long id){
        Activity activity = activityRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Activity Was Not Found"));
        return activity;
    }
}
