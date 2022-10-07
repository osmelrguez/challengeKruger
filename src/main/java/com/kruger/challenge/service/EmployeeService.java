package com.kruger.challenge.service;

import com.kruger.challenge.dto.Employee;
import com.kruger.challenge.enums.Vaccine;
import com.kruger.challenge.exception.NotFoundException;
import com.kruger.challenge.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(String id){
        return employeeRepository.findById(id);
    }

    public List<Employee> findByVaccinate(boolean vaccinate){
        return employeeRepository.findByVaccinate(vaccinate);
    }

    public List<Employee> findByVaccinateDateBetween(Date from, Date to){
        return employeeRepository.findByVaccinateDateBetween(from, to);
    }

    public List<Employee> findByVaccine(Vaccine vaccine){
        return employeeRepository.findByVaccine(vaccine);
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee editEmployee(String id, Employee employee){
        if (employeeRepository.existsById(id)){
            throw new NotFoundException("Employee not found");
        }
        Employee employee1 = new Employee();

        employee1.setId(employee.getId());
        employee1.setNames(employee.getNames());
        employee1.setLastName(employee.getLastName());
        employee1.setEmail(employee.getEmail());

        employeeRepository.save(employee1);
        return employee1;
    }

    public Employee employeeEditEmployee(Employee employee){
        Employee employee1 = new Employee();

        employee1.setBirthDate(employee.getBirthDate());
        employee1.setAddress(employee.getAddress());
        employee1.setPhone(employee.getPhone());
        employee1.setVaccinate(employee.isVaccinate());
        employee1.setVaccine(employee.getVaccine());
        employee1.setVaccinateDate(employee.getVaccinateDate());
        employee1.setDose(employee.getDose());

        employeeRepository.save(employee1);
        return employee1;
    }

    public void deleteEmployee(String id){
        if (!employeeRepository.existsById(id)){
            throw new NotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }
}
