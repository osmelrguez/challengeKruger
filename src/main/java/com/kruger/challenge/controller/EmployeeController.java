package com.kruger.challenge.controller;

import com.kruger.challenge.dto.Employee;
import com.kruger.challenge.enums.Vaccine;
import com.kruger.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    public ResponseEntity<Optional<Employee>> findById(@PathVariable(value = "id") String id){
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<Employee>> findAll(){
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/filter")
    public ResponseEntity<List<Employee>> findByVaccinate(@RequestParam("vaccinate") boolean vaccinate){
        return new ResponseEntity<>(employeeService.findByVaccinate(vaccinate), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/filter1")
    public ResponseEntity<List<Employee>> findByVaccinateDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
                                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to){
        return new ResponseEntity<>(employeeService.findByVaccinateDateBetween(from, to), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/filter2")
    public ResponseEntity<List<Employee>> findByVaccine(@RequestParam("vaccine") Vaccine vaccine){
        return new ResponseEntity<>(employeeService.findByVaccine(vaccine), HttpStatus.OK);
    }


    @PostMapping("/admin")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/admin/{id}")
    public  ResponseEntity<Employee> editEmployee(@PathVariable(value = "id") String id, @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.editEmployee(id, employee), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/employee/{id}")
    public  ResponseEntity<Employee> employeeEditEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.employeeEditEmployee(employee), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/admin/{id}")
    public  ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") String id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully");
    }
}
