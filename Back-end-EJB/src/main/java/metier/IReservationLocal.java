package metier;

import jakarta.ejb.Local;
import metier.entities.Reservation;

import java.util.List;

@Local
public interface IReservationLocal {
    Reservation addReservation(Reservation Reservation ) ;
    Reservation getReservation( int id_Reservation ) ;
    void updateReservation( Reservation Reservation );
    void deleteReservation( int id_Reservation );
    List<Reservation> listReservation() ;
}
