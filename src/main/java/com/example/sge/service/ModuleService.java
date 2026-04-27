package com.example.sge.service;

import com.example.sge.Repositories.ModuleRepository;
import com.example.sge.model.Module;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {
    private final ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    // CREATE
    public Module ajouter(Module m) {
        return moduleRepository.save(m);
    }

    // READ ALL
    public List<Module> listerTous() {
        return moduleRepository.findAll();
    }

    // READ BY ID
    public Optional<Module> trouverParId(Long id) {
        return moduleRepository.findById(id);
    }

    // DELETE
    public void supprimer(Long id) {
        moduleRepository.deleteById(id);
    }
}