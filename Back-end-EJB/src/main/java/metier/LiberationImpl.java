package metier;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

import metier.entities.*;
import metier.entities.Liberation;

@Stateless
public class LiberationImpl implements ILiberationLocal, ILiberationRemote {
    @PersistenceContext(unitName="SalleEJB")
    private EntityManager em;


    @Override
    public Liberation addLiberation(Liberation Liberation) {

        Liberation existingLiberation = em.find(Liberation.class, Liberation.getId());
        if (existingLiberation != null) {
            throw new RuntimeException("Liberation already exists");
        } else {
            try {
                Liberation.setDateLiberation(LocalDateTime.now());
                em.persist(Liberation);
                Journal journal = new Journal();
                journal.setOperation("Add Liberation id="+Liberation.getId());
                journal.setTime(LocalDateTime.now());
                journal.setNom(Liberation.getProffesseur().getNom());
                journal.setPrenom(Liberation.getProffesseur().getPrenom());
                journal.setRole("Proffesseur");
                em.persist(journal);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return Liberation;
        }
    }

    public Liberation getLiberation( int id_Liberation ) {
        Liberation pr = this.em.find(Liberation.class, id_Liberation );
        if (pr == null) {
            throw new RuntimeException("Liberation introuvable");
        } else {
            return pr;
        }
    }

    public List<Liberation> listLiberation() {
        return em.createQuery("select edt from Liberation edt",Liberation.class).getResultList();
    }

    @Override
    public void updateLiberation(Liberation Liberation) {
        try {
            em.merge(Liberation);
            Journal journal = new Journal();
            journal.setOperation("Update Liberation id="+Liberation.getId());
            journal.setTime(LocalDateTime.now());
            journal.setNom(Liberation.getProffesseur().getNom());
            journal.setPrenom(Liberation.getProffesseur().getPrenom());
            journal.setRole("Proffesseur");
            em.persist(journal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteLiberation(int id_Liberation) {
        try {
            Liberation Liberation = em.find(Liberation.class, id_Liberation);
            em.remove(Liberation);
            Journal journal = new Journal();
            journal.setOperation("DELETE Liberation id="+Liberation.getId());
            journal.setTime(LocalDateTime.now());
            journal.setNom(Liberation.getProffesseur().getNom());
            journal.setPrenom(Liberation.getProffesseur().getPrenom());
            journal.setRole("Proffesseur");
            em.persist(journal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

