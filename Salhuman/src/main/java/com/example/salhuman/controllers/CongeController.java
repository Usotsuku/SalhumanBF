package com.example.salhuman.controllers;

import com.example.salhuman.models.Conge;
import com.example.salhuman.models.Employe;
import com.example.salhuman.repositories.CongeRepository;
import com.example.salhuman.repositories.EmployeRepository;
import com.example.salhuman.security.dto.congeResReq;
import com.example.salhuman.security.dto.employeReqRes;
import com.example.salhuman.security.entities.User;
import com.example.salhuman.security.repositories.UserRepository;
import com.example.salhuman.services.CongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
public class CongeController {
    @Autowired
    EmployeRepository employeRepository;
    @Autowired
    CongeRepository congeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CongeService congeService;

    @PostMapping("/employe/demander-conge")
    public ResponseEntity<congeResReq> demanderConge(@RequestBody congeResReq conge, Principal principal){

        System.out.println("Received conge request: " + conge);
        String userEmail = principal.getName();
        Optional<User> user = userRepository.findByEmail(userEmail);
        Employe employe = user.get().getEmploye();

        return ResponseEntity.ok(congeService.saveConge(conge,employe));
    }

    @PutMapping("/manager/approuver-conge/{id}")
    public ResponseEntity<congeResReq> approuverConge(@PathVariable long id) {
        return ResponseEntity.ok(congeService.approuverConge(id));
    }

    @GetMapping("/employe/get-all-conges")
    public ResponseEntity<congeResReq> getAllEmployeConges(Principal principal) {

        String userEmail = principal.getName();
        Optional<User> user = userRepository.findByEmail(userEmail);
        Employe employe = user.get().getEmploye();
        return ResponseEntity.ok(congeService.getAllEmployeConges(employe));
    }

    @GetMapping("/manager/get-all-conges")
    public ResponseEntity<congeResReq> getAllConges() {

        return ResponseEntity.ok(congeService.getAllConges());
    }


}
