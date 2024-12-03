package metier;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

import metier.entities.*;
import metier.entities.Reservation;

@Stateless
public class ReservationImpl implements IReservationLocal, IReservationRemote {
    @PersistenceContext(unitName="SalleEJB")
    private EntityManager em;


    @Override
    public Reservation addReservation(Reservation Reservation) {

        Reservation existingReservation = em.find(Reservation.class, Reservation.getId());
        if (existingReservation != null) {
            throw new RuntimeException("Reservation already exists");
        } else {
            try {
                em.persist(Reservation);
                Journal journal = new Journal();
                journal.setOperation("Add Reservation id="+Reservation.getId());
                journal.setTime(LocalDateTime.now());
                journal.setNom(Reservation.getProffesseur().getNom());
                journal.setPrenom(Reservation.getProffesseur().getPrenom());
                journal.setRole("Proffesseur");
                em.persist(journal);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return Reservation;
        }
    }

    public Reservation getReservation( int id_Reservation ) {
        Reservation pr = this.em.find(Reservation.class, id_Reservation );
        if (pr == null) {
            throw new RuntimeException("Reservation introuvable");
        } else {
            return pr;
        }
    }

    public List<Reservation> listReservation() {
        return em.createQuery("select edt from Reservation edt",Reservation.class).getResultList();
    }

    @Override
    public void updateReservation(Reservation Reservation) {
        try {
            em.merge(Reservation);
            Journal journal = new Journal();
            journal.setOperation("Update Reservation id="+Reservation.getId());
            journal.setTime(LocalDateTime.now());
            journal.setNom(Reservation.getProffesseur().getNom());
            journal.setPrenom(Reservation.getProffesseur().getPrenom());
            journal.setRole("Proffesseur");
            em.persist(journal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteReservation(int id_Reservation) {
        try {
            Reservation Reservation = em.find(Reservation.class, id_Reservation);
            em.remove(Reservation);
            Journal journal = new Journal();
            journal.setOperation("DELETE Reservation id="+Reservation.getId());
            journal.setTime(LocalDateTime.now());
            journal.setNom(Reservation.getProffesseur().getNom());
            journal.setPrenom(Reservation.getProffesseur().getPrenom());
            journal.setRole("Proffesseur");
            em.persist(journal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

