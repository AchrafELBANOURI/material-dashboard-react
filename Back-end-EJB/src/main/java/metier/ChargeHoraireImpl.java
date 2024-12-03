package metier;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import metier.entities.*;
import metier.entities.ChargeHoraire;

@Stateless
public class ChargeHoraireImpl implements IChargeHoraireLocal, IChargeHoraireRemote {
    @PersistenceContext(unitName="SalleEJB")
    private EntityManager em;


    @Override
    public ChargeHoraire addChargeHoraire(ChargeHoraire chargeHoraire) {
        // Check if the entity already exists in the persistence context by finding it in the database
        int matiere_id = chargeHoraire.getMatiere().getMatiere_id();
        int filiere_id = chargeHoraire.getFiliere().getFiliere_id();
        Categorie categorie = chargeHoraire.getCategorie();

        // Retrieve managed Matiere
        // Retrieve managed Filiere
        Filiere filiere = em.find(Filiere.class, chargeHoraire.getFiliere().getFiliere_id());
        if (filiere == null) {
            throw new IllegalArgumentException("Filiere with ID " + chargeHoraire.getFiliere().getFiliere_id() + " does not exist.");
        }
        chargeHoraire.setFiliere(filiere);
        Matiere matiere = em.find(Matiere.class, chargeHoraire.getMatiere().getMatiere_id());
        if (matiere == null) {
            throw new IllegalArgumentException("Matiere with ID " + chargeHoraire.getMatiere().getMatiere_id() + " does not exist.");
        }

        chargeHoraire.setMatiere(matiere);
        ChargeHoraire_PK pk = new ChargeHoraire_PK() ;
        pk.setMatiere(matiere_id);
        pk.setFiliere(filiere_id);
        pk.setCategorie(categorie);

        ChargeHoraire existingChargeHoraire = em.find(ChargeHoraire.class, pk);

        if (existingChargeHoraire != null) {
            throw new RuntimeException("Matiere existe deja avec ce charge Horaire");
        } else {
            try {
                em.persist(chargeHoraire);
                Journal journal = new Journal();
                journal.setOperation("Definir Charge Horaire de la Matiere id="+matiere.getMatiere_id());
                journal.setTime(LocalDateTime.now());
                journal.setNom(filiere.getCoordFiliere().getNom());
                journal.setPrenom(filiere.getCoordFiliere().getPrenom());
                journal.setRole("Coordonateur de Filiere");
                em.persist(journal);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return chargeHoraire;
        }
    }

    public ChargeHoraire getChargeHoraire(ChargeHoraire_PK pk) {
        ChargeHoraire pr = (ChargeHoraire) this.em.find(ChargeHoraire.class, pk);
        if (pr == null) {
            throw new RuntimeException("ChargeHoraire introuvable");
        } else {
            return pr;
        }
    }

    public List<ChargeHoraire> listChargeHoraires() {
        return em.createQuery("select ch from ChargeHoraire ch", ChargeHoraire.class).getResultList();
    }

    @Override
    public void updateChargeHoraire(ChargeHoraire ChargeHoraire) {
        try {
            em.merge(ChargeHoraire);
            Journal journal = new Journal();
            journal.setOperation("UPDATE charge Horaire de la Matiere id="+ChargeHoraire.getMatiere().getMatiere_id());
            journal.setTime(LocalDateTime.now());
            journal.setNom(ChargeHoraire.getFiliere().getCoordFiliere().getNom());
            journal.setPrenom(ChargeHoraire.getFiliere().getCoordFiliere().getPrenom());
            journal.setRole("Coordonateur de Filiere");
            em.persist(journal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteChargeHoraire(ChargeHoraire_PK pk) {
        try {
            ChargeHoraire chargeHoraire = em.find(ChargeHoraire.class, pk);
            em.remove(chargeHoraire);
            Journal journal = new Journal();
            journal.setOperation("DELETE charge Horaire de la matiere id="+chargeHoraire.getMatiere().getMatiere_id());
            journal.setTime(LocalDateTime.now());
            journal.setNom(chargeHoraire.getFiliere().getCoordFiliere().getNom());
            journal.setPrenom(chargeHoraire.getFiliere().getCoordFiliere().getPrenom());
            journal.setRole("Coordonateur de Filiere");
            em.persist(journal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

