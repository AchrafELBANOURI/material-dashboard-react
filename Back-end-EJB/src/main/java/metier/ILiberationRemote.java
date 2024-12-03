package metier;

import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import metier.entities.Liberation;

import java.util.List;

@Remote
public interface ILiberationRemote {
    Liberation addLiberation(Liberation Liberation ) ;
    Liberation getLiberation( int id_Liberation ) ;
    void updateLiberation( Liberation Liberation );
    void deleteLiberation( int id_Liberation );
    List<Liberation> listLiberation() ;
}
