package com.example.Student._Library_Management_System.Controllers;

import com.example.Student._Library_Management_System.DTOs.StudentMobileUpdateDTO;
import com.example.Student._Library_Management_System.Model_Layers.Student;
import com.example.Student._Library_Management_System.ServiceLayers.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class StudentController {

    @Autowired
    StudentService studentService;


    @PostMapping("/add")
    public String createStudent(@RequestBody Student student){

        return studentService.createStudent(student);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity getStudent(@PathVariable("id") int id){
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.FOUND);
    }
    @GetMapping("/getUser")
    public String getNameByEmail(@RequestParam("email") String email){
        return studentService.findNameByEmail(email);
    }
    @PutMapping("/updateMob")
    public String updateMob(@RequestBody StudentMobileUpdateDTO studentMobileUpdateDTO){

       return studentService.updateMob(studentMobileUpdateDTO);
    }

}
