package com.dealpricing.service;

import com.dealpricing.entity.Technology;
import com.dealpricing.exception.TechnologyNotFoundException;
import com.dealpricing.repository.TechnologyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyService implements ITechnologyService{

    @Autowired
    private TechnologyRepo technologyRepo;

    @Override
    public List<Technology> getAllTechnology() {
        return technologyRepo.findAll();
    }

    public Technology getTechnologyById(Integer t_id) throws TechnologyNotFoundException {
        return technologyRepo.findById(t_id).orElseThrow(()->new TechnologyNotFoundException("No Technology found by this id "+ t_id));
    }

    @Override
    public Technology addTechnology(Technology technology) {
        return technologyRepo.save(technology);
    }

    @Override
    public Technology updateTechnology(Integer t_id, Technology technology) throws  TechnologyNotFoundException{
        Technology technology1 = getTechnologyById(t_id);
        technology1.setTechnology_name(technology.getTechnology_name());
        return technologyRepo.save(technology1);
    }

    @Override
    public void deleteTechnology(Integer t_id) throws TechnologyNotFoundException {
        Technology technology = getTechnologyById(t_id);
        technologyRepo.delete(technology);

    }
}
