package com.jake.serverCalculator.data;

import com.jake.serverCalculator.models.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeRepository {

    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getAllEmployees(){
        return employeeList;
    }

    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }

    public Employee findEmployeeById(String id){
        for (Employee employee : employeeList){
            if(employee.getId().equalsIgnoreCase(id)){
                return employee;
            }
        }
        return null;
    }

    private List<Employee> employeePositionList = new ArrayList<>();

    public List<Employee> findEmployeeByPosition(String position){
        for (Employee employee : employeeList){
            if(employee.getPosition().equalsIgnoreCase(position)){
                employeePositionList.add(employee);
            }
        }
        return employeePositionList;
    }
}
