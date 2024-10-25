package com.example.cinetheque_serveur.beans.admin;

import com.example.cinetheque_serveur.entities.CD;
import com.example.cinetheque_serveur.entities.Emprunt;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface AdminService {
    void ajouterCD(String titre);
    void modifierCD(Long cdId, String nouveauTitre);
    void supprimerCD(Long cdId);
    List<Emprunt> voirTousLesEmprunts();
}