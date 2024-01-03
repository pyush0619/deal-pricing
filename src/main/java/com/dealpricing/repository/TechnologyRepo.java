package com.dealpricing.repository;

import com.dealpricing.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepo extends JpaRepository<Technology,Integer> {
}
