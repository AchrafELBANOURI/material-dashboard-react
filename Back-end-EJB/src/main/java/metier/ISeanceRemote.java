package metier;

import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import metier.entities.EmploiDuTemps;
import metier.entities.Seance;

import java.util.List;

@Remote
public interface ISeanceRemote {
    Seance addSeance(Seance Seance ) ;
    Seance getSeance( int id_seance ) ;
    void updateSeance( Seance Seance );
    void deleteSeance( int id_seance );
    List<Seance> listSeancesByEmploiduTemps( EmploiDuTemps emploiDuTemps ) ;
}
