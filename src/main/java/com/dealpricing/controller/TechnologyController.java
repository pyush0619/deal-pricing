package com.dealpricing.controller;

import com.dealpricing.entity.Technology;
import com.dealpricing.entity.Technology;
import com.dealpricing.exception.TechnologyNotFoundException;
import com.dealpricing.service.ITechnologyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController


public class TechnologyController {

    @Autowired
    ITechnologyService technologyService;

    @GetMapping(value= "/tech")
    public ResponseEntity<List<Technology>> getAllTechnology(){
        return ResponseEntity.ok().body(technologyService.getAllTechnology());
    }

    @GetMapping(value="/tech/{id}")
    public ResponseEntity<Technology> getTechnologyById(@PathVariable Integer id) throws TechnologyNotFoundException {
        Technology technology =technologyService.getTechnologyById(id);
        return ResponseEntity.ok().body(technology);
    }

    @PostMapping(value="/tech")
    public ResponseEntity<Technology> addTechnology(@Valid @RequestBody Technology technology){
        Technology savedTechnology = technologyService.addTechnology(technology);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTechnology);
    }

    @DeleteMapping(value="/tech/{id}")
    public ResponseEntity<Void> deleteTechnology(@PathVariable Integer id) throws TechnologyNotFoundException{
        technologyService.deleteTechnology(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/tech/{id}")
    public ResponseEntity<Technology> updateTechnology(@PathVariable Integer id,@Valid @RequestBody Technology technology) throws TechnologyNotFoundException{
        Technology updateTechnology = technologyService.updateTechnology(id, technology);
        return ResponseEntity.ok(updateTechnology);
    }

}
