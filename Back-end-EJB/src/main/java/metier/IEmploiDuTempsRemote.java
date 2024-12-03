package metier;

import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import metier.entities.EmploiDuTemps;

import java.util.List;

@Remote
public interface IEmploiDuTempsRemote {
    EmploiDuTemps addEmploiDuTemps(EmploiDuTemps EmploiDuTemps ) ;
    EmploiDuTemps getEmploiDuTemps( int id_EmploiDuTemps ) ;
    void updateEmploiDuTemps( EmploiDuTemps EmploiDuTemps );
    void deleteEmploiDuTemps( int id_EmploiDuTemps );
    List<EmploiDuTemps> listEmploiDuTemps() ;
}
