package com.activity.Activity.controller;

import com.activity.Activity.DTO.ActivityDTO;
import com.activity.Activity.DTO.MonumentDTO;
import com.activity.Activity.model.Activity;
import com.activity.Activity.model.Monument;
import com.activity.Activity.service.MonumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monument")
public class MonumentController {

    private final MonumentService monumentService;

    @Autowired
    public MonumentController(MonumentService monumentController){
        this.monumentService = monumentController;
    }


    @Operation(summary = "Get All Monuments", description = "Display all the Monuments", tags = {"Monuments Management"})
    @GetMapping("/")
    public List<Monument> findAll(){
        return monumentService.findAll();
    }

    @Operation(
            summary = "Show an monument",
            description = "Show an monument based on its unique ID",
            tags = {"Monuments Management"}
    )
    @GetMapping("/{monument}")
    public ResponseEntity<?> show(
            @Parameter(description = "ID of the monument to show", required = true)
            @PathVariable Long monument
    ){
        return monumentService.show(monument);
    }

    @PostMapping("/")
    @Operation(
            summary = "Create an monument",
            description = "Creates a new monument based on the details provided in the request body",
            tags = {"Monuments Management"}
    )
    public ResponseEntity<?> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the monument to create")
            @RequestBody MonumentDTO monumentBody
    ){
        return monumentService.create(monumentBody);
    }


    @PutMapping("/{monument}")
    @Operation(
            summary = "Update an monument",
            description = "Update an monument by its unique ID",
            tags = {"Monument Management"}
    )
    public ResponseEntity<?> edit(
            @Parameter(description = "ID of the monument to update", required = true)
            @PathVariable Long monument,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the monument to update")
            @RequestBody MonumentDTO monumentBody
    ){
        return monumentService.update(monumentBody, monument);
    }

    @DeleteMapping("/{monument}")
    @Operation(
            summary = "Delete an monument",
            description = "Deletes an monument by its unique ID",
            tags = {"Monument Management"}
    )
    public ResponseEntity<?> delete(
            @Parameter(description = "ID of the monument to delete", required = true)
            @PathVariable Long monument){
        return monumentService.delete(monument);
    }
}
