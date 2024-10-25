package com.example.cinetheque_serveur.beans.user;

import com.example.cinetheque_serveur.entities.CD;
import com.example.cinetheque_serveur.entities.Emprunt;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.Date;
import java.util.List;

@Stateless
public class UserServiceBean implements UserService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<CD> listerCDsDisponibles() {
        TypedQuery<CD> query = em.createQuery("SELECT c FROM CD c WHERE c.disponible = true", CD.class);
        return query.getResultList();
    }

    @Override
    public void emprunterCD(Long cdId, String utilisateur) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null && cd.isDisponible()) {
            cd.setDisponible(false);
            Emprunt emprunt = new Emprunt();
            emprunt.setCd(cd);
            emprunt.setUtilisateur(utilisateur);
            emprunt.setDateEmprunt(new Date());
            emprunt.setRetourne(false);
            em.persist(emprunt);
            em.merge(cd);
        }
    }

    @Override
    public List<Emprunt> voirEmprunts(String utilisateur) {
        TypedQuery<Emprunt> query = em.createQuery("SELECT e FROM Emprunt e WHERE e.utilisateur = :utilisateur AND e.retourne = false", Emprunt.class);
        query.setParameter("utilisateur", utilisateur);
        return query.getResultList();
    }

    @Override
    public void retournerCD(Long empruntId) {
        Emprunt emprunt = em.find(Emprunt.class, empruntId);
        if (emprunt != null && !emprunt.isRetourne()) {
            emprunt.setRetourne(true);
            CD cd = emprunt.getCd();
            cd.setDisponible(true);
            em.merge(emprunt);
            em.merge(cd);
        }
    }
}