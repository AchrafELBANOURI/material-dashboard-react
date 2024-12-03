package metier;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import metier.entities.*;
import metier.entities.Salle;

@Stateless
public class SalleImpl implements ISalleLocal, ISalleRemote {
    @PersistenceContext(unitName="SalleEJB")
    private EntityManager em;


    @Override
    public Salle addSalle(Salle salle) {

        Salle existingSalle = em.find(Salle.class, salle.getNomSalle());
        if (existingSalle != null) {
            throw new RuntimeException("Salle already exists");
        } else {
            try {
                em.persist(salle);
                Journal journal = new Journal();
                journal.setOperation("ADD Salle nom="+salle.getNomSalle());
                journal.setTime(LocalDateTime.now());
                journal.setNom(salle.getRespoSalle().getNom());
                journal.setPrenom(salle.getRespoSalle().getPrenom());
                journal.setRole("Responsable de Salle");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return salle;
        }
    }

    public Salle getSalle( String nomSalle ) {
        Salle pr = (Salle) this.em.find(Salle.class, nomSalle );
        if (pr == null) {
            throw new RuntimeException("Salle introuvable");
        } else {
            return pr;
        }
    }

    public List<Salle> listSallesByRespoSalle(ResponsableDeSalle respoSalle) {
        respoSalle.setListSalles(
                em.createQuery("select s from Salle s where s.respoSalle = :respoSalle", Salle.class)
                        .setParameter("respoSalle", respoSalle)
                        .getResultList()
        );
        return respoSalle.getListSalles();
    }

    @Override
    public void updateSalle(Salle salle) {
        try {
            em.merge(salle);
            Journal journal = new Journal();
            journal.setOperation("UDPATE Salle nom="+salle.getNomSalle());
            journal.setTime(LocalDateTime.now());
            journal.setNom(salle.getRespoSalle().getNom());
            journal.setPrenom(salle.getRespoSalle().getPrenom());
            journal.setRole("Responsable de Salle");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSalle(String nomSalle) {
        try {
            Salle salle = em.find(Salle.class, nomSalle);
            em.remove(salle);
            Journal journal = new Journal();
            journal.setOperation("DELETE Salle nom="+salle.getNomSalle());
            journal.setTime(LocalDateTime.now());
            journal.setNom(salle.getRespoSalle().getNom());
            journal.setPrenom(salle.getRespoSalle().getPrenom());
            journal.setRole("Responsable de Salle");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

