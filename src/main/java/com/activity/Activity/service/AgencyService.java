package com.activity.Activity.service;

import com.activity.Activity.DTO.AgencyDTO;
import com.activity.Activity.model.Activity;
import com.activity.Activity.model.Agency;
import com.activity.Activity.repository.AgencyRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
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
public class AgencyService {
    private final AgencyRepository agencyRepository;
    private final ModelMapper mapper;

    @Autowired
    public AgencyService(AgencyRepository repo, ModelMapper mapper){
        this.agencyRepository = repo;
        this.mapper = mapper;
    }

    public List<Agency> findAll(){
        return agencyRepository.findAll();
    }

    public Agency show(Long id){
        return agencyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agency Was Not Found"));
    }

    public ResponseEntity<?> create(AgencyDTO agencyDTO){
        Agency agency = mapper.map(agencyDTO, Agency.class);
        Map<String, Object> res = new HashMap<>();
        try{
            agencyRepository.save(agency);
            res.put("message", "Agency Created Successfully");
            return ResponseEntity.ok()
                    .body(res);
        }catch (Exception e){
            res.put("message", e.getMessage());
            return ResponseEntity.status(500)
                    .body(res);
        }
    }

    public ResponseEntity<?> update(AgencyDTO agencyDTO, Long id){
        Map<String, Object> res = new HashMap<>();
        Agency agency = agencyRepository.findById(id)
                .orElse(null);
        if(agency == null){
            res.put("message", "Agency Was Not Found");
            return ResponseEntity.status(404).body(res);
        }

        Optional.ofNullable(agencyDTO.getName()).ifPresent(agency::setName);
        Optional.ofNullable(agencyDTO.getDescription()).ifPresent(agency::setDescription);
        Optional.ofNullable(agencyDTO.getCategory()).ifPresent(agency::setCategory);
        Optional.ofNullable(agencyDTO.getLocation()).ifPresent(agency::setLocation);
        Optional.of(agencyDTO.getCarryingCapacity()).ifPresent(agency::setCarryingCapacity);
        Optional.ofNullable(agencyDTO.getImages()).ifPresent(agency::setImages);

        try {
            agencyRepository.save(agency);
            res.put("message", "Agency Updated Successfully");
            return ResponseEntity.ok()
                    .body(res);
        }catch (DataAccessException e){
            res.put("message", "DATABASE Error: " + e.getMessage());
            return ResponseEntity.status(500)
                    .body(res);
        }catch (Exception e){
            res.put("message", e.getMessage());
            return ResponseEntity.status(500)
                    .body(res);
        }
    }

    public ResponseEntity<?> delete(Long id){
        Map<String, Object> res = new HashMap<>();
        Agency agency = agencyRepository.findById(id)
            .orElse(null);
        if(agency == null){
            res.put("message", "Agency Was Not Found");
            return ResponseEntity.status(404)
                    .body(res);
        }
        try {
            agencyRepository.delete(agency);
            res.put("message", "Agency Deleted Successfully");
            return ResponseEntity.ok()
                    .body(res);
        }catch (DataAccessException e){
            res.put("message", "DATABASE Error: " + e.getMessage());
            return ResponseEntity.status(500)
                    .body(res);
        }catch (Exception e){
            res.put("message", e.getMessage());
            return ResponseEntity.status(500)
                    .body(res);
        }
    }
}
