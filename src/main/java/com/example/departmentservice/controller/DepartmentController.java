package com.example.departmentservice.controller;

import com.example.departmentservice.model.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentRepository departmentRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @PostMapping
    public Department add(@RequestBody Department department){
        LOGGER.info("DepartmentController:add:Department: {}", department);
        return departmentRepository.add(department);
    }

    @GetMapping
    public List<Department> all(){
        LOGGER.info("DepartmentController:all");
        return departmentRepository.all();
    }

    @GetMapping("/with-employees")
    public List<Department> allWithEmployees(){
        LOGGER.info("DepartmentController:all");
        return departmentRepository.allWithEmployees();
    }

    @GetMapping("/{id}")
    public Department get(@PathVariable Long id){
        LOGGER.info("DepartmentController:get:id: {}", id);
        return departmentRepository.get(id);
    }

}
