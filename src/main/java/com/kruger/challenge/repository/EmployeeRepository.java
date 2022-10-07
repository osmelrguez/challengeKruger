package com.kruger.challenge.repository;

import com.kruger.challenge.dto.Employee;
import com.kruger.challenge.enums.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findByVaccinate(boolean vaccinate);
    List<Employee> findByVaccine(Vaccine vaccine);
    @Modifying
    @Transactional
    @Query("select e from Employee e where e.vaccinateDate BETWEEN :from and :to ")
    List<Employee> findByVaccinateDateBetween(@Param("from") Date from, @Param("to") Date to);

}
