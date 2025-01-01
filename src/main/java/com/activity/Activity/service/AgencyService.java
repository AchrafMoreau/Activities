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
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void create(AgencyDTO agencyDTO){
        Agency agency = mapper.map(agencyDTO, Agency.class);
        agencyRepository.save(agency);
    }

    public void update(AgencyDTO agencyDTO, Long id){
        Agency agency = agencyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agency Was Not Found"));

//        this it should be something that handel if some attribute is null insert the old one
        agency.setName(agencyDTO.getName());
        agency.setDescription(agencyDTO.getDescription());
        agency.setCategory(agencyDTO.getCategory());
        agency.setLocation(agencyDTO.getLocation());
        agency.setImages(agencyDTO.getImages());
        agency.setCarryingCapacity(agencyDTO.getCarryingCapacity());

        agencyRepository.save(agency);
    }

    public void delete(Long id){
        Agency agency = agencyRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Agency Was Not Found"));
        agencyRepository.delete(agency);
    }
}
