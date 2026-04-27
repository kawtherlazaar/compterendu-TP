package com.example.sge.service;

import com.example.sge.Repositories.FiliereRepository;
import com.example.sge.model.Filiere;
import com.example.sge.Repositories.FiliereRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiliereService {

    private final FiliereRepository filiereRepository;

    public FiliereService(FiliereRepository filiereRepository) {
        this.filiereRepository = filiereRepository;
    }

    // CREATE
    public Filiere ajouter(Filiere f) {
        return filiereRepository.save(f);
    }

    // READ ALL
    public List<Filiere> listerTous() {
        return filiereRepository.findAll();
    }

    // READ BY ID
    public Filiere trouverParId(Long id) {
        return filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filière non trouvée avec id : " + id));
    }

    // DELETE
    public void supprimer(Long id) {
        if (!filiereRepository.existsById(id)) {
            throw new RuntimeException("Filière non trouvée avec id : " + id);
        }
        filiereRepository.deleteById(id);
    }

    // UPDATE
    public Filiere modifier(Long id, Filiere f) {
        Filiere filiere = filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filière non trouvée avec id : " + id));

        filiere.setNom(f.getNom());

        return filiereRepository.save(filiere);
    }
}