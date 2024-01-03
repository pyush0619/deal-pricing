package com.dealpricing.repository;

import com.dealpricing.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EducationRepo extends JpaRepository<Education,Integer> {
    /*@Query()
    public Optional<List<Education>> findByfk_emp_code(Long fk_emp_code);
*/
    @Query( "SELECT e FROM User us join us.education e WHERE us.emp_Code = :emp_Code")
    List<Education> findEducationByUser(@Param("emp_Code") Long employeeCode);
}
