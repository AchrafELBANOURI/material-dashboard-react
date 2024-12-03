package metier;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

import metier.entities.*;
import metier.entities.Seance;

@Stateless
public class SeanceImpl implements ISeanceLocal, ISeanceRemote {
    @PersistenceContext(unitName="SalleEJB")
    private EntityManager em;


    @Override
    public Seance addSeance(Seance seance) {

        Seance existingSeance = em.find(Seance.class, seance.getSeance_id());
        if (existingSeance != null) {
            throw new RuntimeException("Seance already exists");
        } else {
            try {
                em.persist(seance);
                Journal journal = new Journal();
                journal.setOperation("Modify Emploi du Temps id="+seance.getEmploidutemps().getId());
                journal.setTime(LocalDateTime.now());
                journal.setNom(seance.getEmploidutemps().getFiliere().getCoordFiliere().getNom());
                journal.setPrenom(seance.getEmploidutemps().getFiliere().getCoordFiliere().getPrenom());
                journal.setRole("Coordinateur de Filiere");
                em.persist(journal);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return seance;
        }
    }

    public Seance getSeance( int id_seance ) {
        Seance pr = this.em.find(Seance.class, id_seance );
        if (pr == null) {
            throw new RuntimeException("Seance introuvable");
        } else {
            return pr;
        }
    }

    public List<Seance> listSeancesByEmploiduTemps(EmploiDuTemps emploiDuTemps) {
        return em.createQuery("select s from Seance s where s.emploidutemps = :emploiDuTemps", Seance.class)
                .setParameter("emploiDuTemps", emploiDuTemps)
                .getResultList();
    }

    @Override
    public void updateSeance(Seance seance) {
        try {
            em.merge(seance);
            Journal journal = new Journal();
            journal.setOperation("Modify Emploi du Temps id="+seance.getEmploidutemps().getId());
            journal.setTime(LocalDateTime.now());
            journal.setNom(seance.getEmploidutemps().getFiliere().getCoordFiliere().getNom());
            journal.setPrenom(seance.getEmploidutemps().getFiliere().getCoordFiliere().getPrenom());
            journal.setRole("Coordinateur de Filiere");
            em.persist(journal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSeance(int id_seance) {
        try {
            Seance seance = em.find(Seance.class, id_seance);
            em.remove(seance);
            Journal journal = new Journal();
            journal.setOperation("Modify Emploi du Temps id="+seance.getEmploidutemps().getId());
            journal.setTime(LocalDateTime.now());
            journal.setNom(seance.getEmploidutemps().getFiliere().getCoordFiliere().getNom());
            journal.setPrenom(seance.getEmploidutemps().getFiliere().getCoordFiliere().getPrenom());
            journal.setRole("Coordinateur de Filiere");
            em.persist(journal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

