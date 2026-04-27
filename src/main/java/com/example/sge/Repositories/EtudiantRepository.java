package com.example.sge.Repositories;

import com.example.sge.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    List<Etudiant> findByNomContainingIgnoreCase(String nom);

    List<Etudiant> findByGroupe(String groupe);

    List<Etudiant> findByMoyenneGreaterThanEqual(double seuil);

    @Query("SELECT e FROM Etudiant e WHERE e.moyenne >= :seuil ORDER BY e.moyenne DESC")
    List<Etudiant> findMeilleurs(@Param("seuil") double seuil);
    List<Etudiant> findByModule(Module module);

    Etudiant findTopByOrderByMoyenneDesc();

    List<Etudiant> findByModule(com.example.sge.model.Module module);
}