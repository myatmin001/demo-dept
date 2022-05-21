package com.myatmin.demo.department;

import com.myatmin.demo.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getDepartment(){
        return departmentRepository.findAll();
    }

    public void addDepartment(Department department){
        departmentRepository.save(department);
    }

    public void deleteByDepartmentId(Long departmentId){
        boolean exist = departmentRepository.existsById(departmentId);
        if(!exist){
            throw new IllegalStateException("Department id "+ departmentId + " does not exist");
        }
        departmentRepository.deleteById(departmentId);
    }

    @Transactional
    public void updateDepartmentById(Long departmentId,String name){
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new IllegalStateException("Department with id " + departmentId + " does not exit"));
        if(!Objects.equals(department.getName(), name)){
            department.setName(name);
        }
    }
}
