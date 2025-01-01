package com.activity.Activity.controller;

import com.activity.Activity.DTO.AgencyDTO;
import com.activity.Activity.model.Agency;
import com.activity.Activity.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agency")
public class AgencyController {
    private final AgencyService agencyService;

    @Autowired
    public AgencyController(AgencyService agencyService){
        this.agencyService = agencyService;
    }

    @GetMapping("/")
    public List<Agency> findAll(){
        return agencyService.findAll();
    }

    @GetMapping("/{agency}")
    public Agency show(@RequestParam Long agency){
        return agencyService.show(agency);
    }

    @PostMapping("/")
    public String create(@RequestBody AgencyDTO agencyDTO){
        agencyService.create(agencyDTO);
        return "Agency Created Successfully";
    }

    @PutMapping("/{agency}")
    public String edit(@RequestParam Long agency, @RequestBody AgencyDTO agencyDTO){
        System.out.println(agency);
        agencyService.update(agencyDTO, agency);
        return "Agency Updated Successfully";
    }

    @DeleteMapping("/{agency}")
    public String delete(@RequestParam Long agency){
        agencyService.delete(agency);
        return "Agency Was Deleted Successfully";
    }

}
