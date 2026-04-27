package com.example.sge.controller;

import com.example.sge.model.Etudiant;
import com.example.sge.service.EtudiantService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/etudiant")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Etudiant> ajouter(@RequestBody Etudiant e) {
        return ResponseEntity.status(201).body(etudiantService.ajouter(e));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Etudiant>> listerTous() {
        return ResponseEntity.ok(etudiantService.listerTous());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> trouverParId(@PathVariable Long id) {
        Optional<Etudiant> etu = etudiantService.trouverParId(id);
        return etu.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // SEARCH BY NAME
    @GetMapping("/recherche")
    public ResponseEntity<List<Etudiant>> rechercher(@RequestParam String nom) {
        return ResponseEntity.ok(etudiantService.rechercherParNom(nom));
    }

    // SEARCH BY GROUP
    @GetMapping("/groupe")
    public ResponseEntity<List<Etudiant>> rechercherParGroupe(@RequestParam String groupe) {
        return ResponseEntity.ok(etudiantService.trouverEtudiantParGroupe(groupe));
    }

    // SEARCH ADMIS
    @GetMapping("/admis")
    public ResponseEntity<List<Etudiant>> rechercherAdmis(@RequestParam double seuil) {
        return ResponseEntity.ok(etudiantService.trouverAdmis(seuil));
    }

    // SEARCH MEILLEURS
    @GetMapping("/meilleurs")
    public ResponseEntity<List<Etudiant>> rechercherMeilleurs(@RequestParam double seuil) {
        return ResponseEntity.ok(etudiantService.trouverMeilleurs(seuil));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> modifier(@PathVariable Long id, @RequestBody Etudiant e) {
        Etudiant updated = etudiantService.modifier(id, e);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        etudiantService.supprimer(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ PAGINATION CORRIGÉE
    @GetMapping("/page")
    public Page<Etudiant> getPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return etudiantService.listerAvecPagination(page, size, sortBy);
    }
}