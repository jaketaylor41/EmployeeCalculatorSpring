package com.jake.serverCalculator.controllers;

import com.jake.serverCalculator.data.EmployeeRepository;
import com.jake.serverCalculator.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class BaseController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping("/getAllEmployees")
    public @ResponseBody List<Employee> getAllEmployees(){
        return employeeRepository.getAllEmployees();
    }

    @RequestMapping("/get/{id}")
    public @ResponseBody List<Employee> findEmployeeById(@PathVariable String id){
        Employee foundEmployee = employeeRepository.findEmployeeById(id);

        List<Employee> responseArray = new ArrayList<>();
        responseArray.add(foundEmployee);

        return responseArray;

    }

    @RequestMapping("/findEmployeeByPosition/{position}")
    public @ResponseBody List<Employee> findEmployeeByPosition(@PathVariable String position){
        return employeeRepository.findEmployeeByPosition(position);
    }

    @RequestMapping(value = "/add/employee", method = RequestMethod.POST)
    public @ResponseBody Employee postEmployee(@RequestBody Map<String, Object> payload){

        String newId = String.valueOf(payload.get("id"));
        String newFirstName = String.valueOf(payload.get("firstName"));
        String newLastName = String.valueOf(payload.get("lastName"));
        String newSalary = String.valueOf(payload.get("salary"));
        String newPosition = String.valueOf(payload.get("position"));

        Employee newEmployee = new Employee(newId, newFirstName, newLastName, newSalary, newPosition);

        employeeRepository.addEmployee(newEmployee);

        return newEmployee;

    }

    @RequestMapping("/")
    public String baseRoute(){
        return "index";
    }
}
