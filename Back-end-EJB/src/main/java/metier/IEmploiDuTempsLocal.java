package metier;

import jakarta.ejb.Local;
import metier.entities.EmploiDuTemps;

import java.util.List;

@Local
public interface IEmploiDuTempsLocal {
    EmploiDuTemps addEmploiDuTemps(EmploiDuTemps EmploiDuTemps ) ;
    EmploiDuTemps getEmploiDuTemps( int id_EmploiDuTemps ) ;
    void updateEmploiDuTemps( EmploiDuTemps EmploiDuTemps );
    void deleteEmploiDuTemps( int id_EmploiDuTemps );
    List<EmploiDuTemps> listEmploiDuTemps() ;
}
