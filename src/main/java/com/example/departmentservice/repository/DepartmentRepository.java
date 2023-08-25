package com.example.departmentservice.repository;

import com.example.departmentservice.client.EmployeeClient;
import com.example.departmentservice.model.Department;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepository {

    private static List<Department> departments = new ArrayList<>();

    @Autowired
    private EmployeeClient employeeClient;

    public Department add(Department department){
        departments.add(department);
        return department;
    }
    public Department get(Long id){
        return departments.stream().filter(d->d.getId().longValue()==id.longValue()).findFirst().orElseThrow(()->new RuntimeException("Department not found"));
    }

    public List<Department> all(){
        return departments;
    }

    public List<Department> allWithEmployees() {
        departments.forEach(department -> department.setEmployees( employeeClient.getByDepartmentId(department.getId())));
        return departments;
    }
}
