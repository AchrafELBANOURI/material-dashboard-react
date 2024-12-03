package metier;

import jakarta.ejb.Local;
import metier.entities.ResponsableDeSalle;
import metier.entities.Salle;

import java.util.List;

@Local
public interface ISalleLocal {
    Salle addSalle(Salle Salle ) ;
    Salle getSalle( String nomSalle ) ;
    void updateSalle( Salle Salle );
    void deleteSalle( String nomSalle );
    List<Salle> listSallesByRespoSalle(ResponsableDeSalle respoSalle);
}
