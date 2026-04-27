package com.example.sge.controller;

import com.example.sge.model.Module;
import com.example.sge.model.Etudiant;
import com.example.sge.service.ModuleService;
import com.example.sge.service.EtudiantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    private final ModuleService moduleService;
    private final EtudiantService etudiantService;

    public ModuleController(ModuleService moduleService, EtudiantService etudiantService) {
        this.moduleService = moduleService;
        this.etudiantService = etudiantService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Module> ajouter(@RequestBody Module m) {
        return ResponseEntity.status(201).body(moduleService.ajouter(m));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Module>> listerTous() {
        return ResponseEntity.ok(moduleService.listerTous());
    }

    // READ BY ID + étudiants
    @GetMapping("/{id}/etudiant")
    public ResponseEntity<List<Etudiant>> getEtudiantsParModule(@PathVariable Long id) {
        return moduleService.trouverParId(id)
                .map(module -> ResponseEntity.ok(etudiantService.trouverEtudiantParModule(module)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        moduleService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}