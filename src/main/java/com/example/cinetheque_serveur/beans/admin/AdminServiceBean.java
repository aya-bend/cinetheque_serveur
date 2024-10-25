package com.example.cinetheque_serveur.beans.admin;

import com.example.cinetheque_serveur.entities.CD;
import com.example.cinetheque_serveur.entities.Emprunt;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateful
public class AdminServiceBean implements AdminService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void ajouterCD(String titre) {
        CD cd = new CD();
        cd.setTitre(titre);
        cd.setDisponible(true);
        em.persist(cd);
    }

    @Override
    public void modifierCD(Long cdId, String nouveauTitre) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) {
            cd.setTitre(nouveauTitre);
            em.merge(cd);
        }
    }

    @Override
    public void supprimerCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) {
            em.remove(cd);
        }
    }

    @Override
    public List<Emprunt> voirTousLesEmprunts() {
        return em.createQuery("SELECT e FROM Emprunt e", Emprunt.class).getResultList();
    }
}