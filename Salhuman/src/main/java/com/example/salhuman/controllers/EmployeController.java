package com.example.salhuman.controllers;

import com.example.salhuman.models.Employe;
import com.example.salhuman.security.dto.ReqRes;
import com.example.salhuman.security.dto.employeReqRes;
import com.example.salhuman.security.entities.User;
import com.example.salhuman.security.services.UserServiceImp;
import com.example.salhuman.services.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeController {
    @Autowired
    EmployeService employeService;
    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/admin/get-all-employes")
    public ResponseEntity<employeReqRes> getAllEmployes() {

        return ResponseEntity.ok(employeService.getAllEmployes());
    }


    @DeleteMapping("/admin/delete-employe/{id}")
    public ResponseEntity<Void> deleteEmploye(@PathVariable Long id) {
        employeService.deleteEmployeById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/get-employe/{id}")
    public ResponseEntity<employeReqRes> getEmployeById(@PathVariable long id) {
        return ResponseEntity.ok(employeService.getEmployeById(id));
    }



    @PutMapping("/admin/update-employe/{id}")
    public ResponseEntity<employeReqRes> updateEmploye(@PathVariable long id, @RequestBody Employe reqres) {
        return ResponseEntity.ok(employeService.updateEmploye(id, reqres));
    }


    @PostMapping("/admin/create-employe")
    public ResponseEntity<employeReqRes> createEmployee(@RequestBody employeReqRes emp){
        return ResponseEntity.ok(employeService.saveEmploye(emp));
    }

}
