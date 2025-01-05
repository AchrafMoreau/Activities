package com.activity.Activity.controller;

import com.activity.Activity.DTO.AgencyDTO;
import com.activity.Activity.model.Agency;
import com.activity.Activity.service.AgencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "Get All Agency", description = "Display all the Agencies", tags = {"Agency Management"})
    @GetMapping("/")
    public List<Agency> findAll(){
        return agencyService.findAll();
    }

    @Operation(
            summary = "Show an agency",
            description = "Show an agency based on its unique ID",
            tags = {"Agency Management"}
    )
    @GetMapping("/{agency}")
    public Agency show(
            @Parameter(description = "ID of the activity to show", required = true)
            @PathVariable Long agency
    ){
        return agencyService.show(agency);
    }

    @Operation(
            summary = "Create an agency",
            description = "Creates a new agency based on the details provided in the request body",
            tags = {"Agency Management"}
    )
    @PostMapping("/")
    public ResponseEntity<?> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the agency to create")
            @RequestBody AgencyDTO agencyDTO
    ){
        return agencyService.create(agencyDTO);
    }


    @Operation(
            summary = "Update an agency",
            description = "Update an agency by its unique ID",
            tags = {"Agency Management"}
    )
    @PutMapping("/{agency}")
    public ResponseEntity<?> edit(
            @Parameter(description = "ID of the agency to update", required = true)
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the agency to update")
            @PathVariable Long agency, @RequestBody AgencyDTO agencyDTO
    ){
        System.out.println("This 's the id : " + agency);
        return agencyService.update(agencyDTO, agency);
    }

    @Operation(
            summary = "Delete an agency",
            description = "Deletes an agency by its unique ID",
            tags = {"Agency Management"}
    )
    @DeleteMapping("/{agency}")
    public ResponseEntity<?> delete(
            @Parameter(description = "ID of the agency to delete", required = true)
            @PathVariable Long agency
    ){
        return agencyService.delete(agency);
    }

}
