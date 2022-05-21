package com.myatmin.demo.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getDepartment(){
        return departmentService.getDepartment();
    }

    @PostMapping
    public void saveDepartment(@RequestBody Department department){
        departmentService.addDepartment(department);
    }

    @DeleteMapping(path = "{departmentId}")
    public void deleteDepartment(@PathVariable("departmentId") Long departmentId){
        departmentService.deleteByDepartmentId(departmentId);
    }

    @PutMapping(path = "{departmentId}")
    public void updateDepartment(
            @PathVariable("departmentId") Long departmentId,
            @RequestParam(required = false) String name){
        departmentService.updateDepartmentById(departmentId, name);
    }
}
