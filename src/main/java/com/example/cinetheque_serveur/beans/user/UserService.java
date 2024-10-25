package com.example.cinetheque_serveur.beans.user;

import com.example.cinetheque_serveur.entities.CD;
import com.example.cinetheque_serveur.entities.Emprunt;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface UserService {
    List<CD> listerCDsDisponibles();
    void emprunterCD(Long cdId, String utilisateur);
    List<Emprunt> voirEmprunts(String utilisateur);
    void retournerCD(Long empruntId);
}