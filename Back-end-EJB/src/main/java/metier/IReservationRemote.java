package metier;

import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import metier.entities.Reservation;

import java.util.List;

@Remote
public interface IReservationRemote {
    Reservation addReservation(Reservation Reservation ) ;
    Reservation getReservation( int id_Reservation ) ;
    void updateReservation( Reservation Reservation );
    void deleteReservation( int id_Reservation );
    List<Reservation> listReservation() ;
}
