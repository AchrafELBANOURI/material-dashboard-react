package metier;

import jakarta.ejb.Remote;
import metier.entities.ResponsableDeSalle;
import metier.entities.Salle;

import java.util.List;

@Remote
public interface ISalleRemote {
    Salle addSalle(Salle Salle ) ;
    Salle getSalle( String nomSalle ) ;
    void updateSalle( Salle Salle );
    void deleteSalle( String nomSalle );
    List<Salle> listSallesByRespoSalle(ResponsableDeSalle respoSalle);
}