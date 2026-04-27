package com.example.sge.controller;

import com.example.sge.service.TableauDeBordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private TableauDeBordService tableauDeBoardService;

    @PostMapping("/filiere")
    public ResponseEntity<String> changerFiliere(
            @RequestParam String nom ) {
        tableauDeBoardService.setFiliereActive(nom);
        return ResponseEntity.ok("Filiere active: " + nom);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> stats() {
        tableauDeBoardService.incrementerConsultation();
        return ResponseEntity.ok(Map.of( "filiereActive", tableauDeBoardService.getFiliereActive(),
                "nbr Consultations" , tableauDeBoardService.getConsultations()));
    }

    @DeleteMapping("/reset")
    public ResponseEntity<String> reset() {
        tableauDeBoardService.reinitialiser();
        return  ResponseEntity.ok("Session Reinitialisee");
    }
}