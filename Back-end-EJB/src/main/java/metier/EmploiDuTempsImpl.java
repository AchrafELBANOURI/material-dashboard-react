package metier;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

import metier.entities.*;
import metier.entities.EmploiDuTemps;

@Stateless
public class EmploiDuTempsImpl implements IEmploiDuTempsLocal, IEmploiDuTempsRemote {
    @PersistenceContext(unitName="SalleEJB")
    private EntityManager em;


    @Override
    public EmploiDuTemps addEmploiDuTemps(EmploiDuTemps emploiDuTemps) {

        EmploiDuTemps existingEmploiDuTemps = em.find(EmploiDuTemps.class, emploiDuTemps.getId());
        if (existingEmploiDuTemps != null) {
            throw new RuntimeException("EmploiDuTemps already exists");
        } else {
            try {
                em.persist(emploiDuTemps);
                Journal journal = new Journal();
                journal.setOperation("Add Emploi du Temps id="+emploiDuTemps.getId());
                journal.setTime(LocalDateTime.now());
                journal.setNom(emploiDuTemps.getFiliere().getCoordFiliere().getNom());
                journal.setPrenom(emploiDuTemps.getFiliere().getCoordFiliere().getNom());
                journal.setRole("Coordinateur de Filiere");
                em.persist(journal);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return emploiDuTemps;
        }
    }

    public EmploiDuTemps getEmploiDuTemps( int id_EmploiDuTemps ) {
        EmploiDuTemps pr = this.em.find(EmploiDuTemps.class, id_EmploiDuTemps );
        if (pr == null) {
            throw new RuntimeException("EmploiDuTemps introuvable");
        } else {
            return pr;
        }
    }

    public List<EmploiDuTemps> listEmploiDuTemps() {
        return em.createQuery("select edt from EmploiDuTemps edt",EmploiDuTemps.class).getResultList();
    }

    @Override
    public void updateEmploiDuTemps(EmploiDuTemps EmploiDuTemps) {
        try {
            em.merge(EmploiDuTemps);
            Journal journal = new Journal();
            journal.setOperation("UPDATE Emploi du Temps id="+EmploiDuTemps.getId());
            journal.setTime(LocalDateTime.now());
            journal.setNom(EmploiDuTemps.getFiliere().getCoordFiliere().getNom());
            journal.setPrenom(EmploiDuTemps.getFiliere().getCoordFiliere().getNom());
            journal.setRole("Coordinateur de Filiere");
            em.persist(journal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEmploiDuTemps(int id_EmploiDuTemps) {
        try {
            EmploiDuTemps EmploiDuTemps = em.find(EmploiDuTemps.class, id_EmploiDuTemps);
            em.remove(EmploiDuTemps);
            Journal journal = new Journal();
            journal.setOperation("DELETE Emploi du Temps id="+EmploiDuTemps.getId());
            journal.setTime(LocalDateTime.now());
            journal.setNom(EmploiDuTemps.getFiliere().getCoordFiliere().getNom());
            journal.setPrenom(EmploiDuTemps.getFiliere().getCoordFiliere().getNom());
            journal.setRole("Coordinateur de Filiere");
            em.persist(journal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

