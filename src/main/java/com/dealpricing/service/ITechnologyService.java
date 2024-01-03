package com.dealpricing.service;

import com.dealpricing.entity.Technology;
import com.dealpricing.exception.TechnologyNotFoundException;

import java.util.List;

public interface ITechnologyService {

    public List<Technology> getAllTechnology();

    public Technology getTechnologyById(Integer t_id);

    public Technology addTechnology(Technology technology);

    public Technology updateTechnology(Integer t_id,Technology technology);

    public void deleteTechnology(Integer t_id);

}

