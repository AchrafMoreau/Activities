package com.activity.Activity.service;

import com.activity.Activity.DTO.MonumentDTO;
import com.activity.Activity.exception.ResourceNotFoundException;
import com.activity.Activity.model.Activity;
import com.activity.Activity.model.Agency;
import com.activity.Activity.model.City;
import com.activity.Activity.model.Monument;
import com.activity.Activity.repository.CityRepository;
import com.activity.Activity.repository.MonumentRepository;
import jakarta.validation.constraints.Negative;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MonumentService {

    private final MonumentRepository monumentRepository;
    private final CityRepository cityRepository;
    private final ModelMapper mapper;

    public MonumentService(MonumentRepository repo, CityRepository cityRepo, ModelMapper mapper){
        this.monumentRepository = repo;
        this.cityRepository = cityRepo;
        this.mapper = mapper;
    }

    public List<Monument> findAll(){
        return monumentRepository.findAll();
    }

    public ResponseEntity<?> show(Long id){
        return monumentRepository.findById(id)
                .map(data -> ResponseEntity.ok(monumentResponse("data", data)))
                .orElseThrow(ResourceNotFoundException::new);
    }

    public ResponseEntity<?> create(MonumentDTO monumentDTO){
        City city = cityRepository.findById(monumentDTO.getCity())
                .orElseThrow(ResourceNotFoundException::new);

        try{
            Monument monument = mapper.map(monumentDTO, Monument.class);
            monument.setCity(city);
            monumentRepository.save(monument);
            return ResponseEntity.ok(monumentResponse("message", "Monument Was Created Successfully"));
        }catch (RuntimeException e) {
            return ResponseEntity.status(500)
                    .body(monumentResponse("message", e.getMessage()));
        }
    }

    public ResponseEntity<?> update(MonumentDTO monumentDTO, Long id){
        Monument monument = monumentRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        Optional.ofNullable(monumentDTO.getName()).ifPresent(monument::setName);
        Optional.ofNullable(monumentDTO.getImage()).ifPresent(monument::setImage);
        if(monumentDTO.getCity() != null){
            City city = cityRepository.findById(monumentDTO.getCity())
                    .orElseThrow(ResourceNotFoundException::new);
            monument.setCity(city);
        }

        try{
            monumentRepository.save(monument);
            return ResponseEntity.ok(monumentResponse("message", "Monument Updated Successfully"));
        }catch (DataAccessException e){
            return ResponseEntity.status(500)
                    .body(monumentResponse("message", "Database Error :" + e.getMessage()));
        }catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(monumentResponse("message", e.getMessage()));
        }
    }


    public ResponseEntity<?> delete(Long id){
        Monument monument = monumentRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        try{
           monumentRepository.delete(monument);
           return ResponseEntity.ok(monumentResponse("message", "Monument Deleted Successfully"));
        }catch (DataAccessException e){
            return ResponseEntity.status(500)
                    .body(monumentResponse("message", "Database Error :" + e.getMessage()));
        }catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(monumentResponse("message", e.getMessage()));
        }

    }

    public Map<String, Object> monumentResponse(String message, Object data){
        return new HashMap<String, Object>(){
            {
                put(message, data);
            }
        };
    }
}
