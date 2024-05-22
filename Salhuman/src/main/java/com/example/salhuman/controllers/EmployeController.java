package com.example.salhuman.controllers;

import com.example.salhuman.models.Employe;
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
    public ResponseEntity<List<Employe>> getAllEmployes() {
        return ResponseEntity.ok(employeService.getAllEmployes());
    }

    @DeleteMapping("/admin/delete-employe/{id}")
    public ResponseEntity<Void> deleteEmploye(@PathVariable Long id) {
        employeService.deleteEmployeById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/get-employe/{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable long id) {
        Employe employe = employeService.getEmployeById(id);
        if (employe != null) {
            return ResponseEntity.ok(employe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/admin/update-employe/{id}")
    public ResponseEntity<Employe> updateEmploye(@PathVariable long id, @RequestBody Employe employe) {
        Employe existingEmploye = employeService.getEmployeById(id);
        if (existingEmploye != null) {
            employe.setEmployeId(existingEmploye.getEmployeId());
            employe.setUser(existingEmploye.getUser());
            Employe updatedEmploye = employeService.updateEmploye(employe);
            return ResponseEntity.ok(updatedEmploye);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/admin/create-employe")
    public ResponseEntity<Employe> createEmploye(@RequestBody Employe employe) {
        Employe savedEmploye = employeService.saveEmploye(employe);
        return ResponseEntity.ok(savedEmploye);
    }
}
