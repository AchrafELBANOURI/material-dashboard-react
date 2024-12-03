package metier;

import jakarta.ejb.Local;
import metier.entities.Liberation;

import java.util.List;

@Local
public interface ILiberationLocal {
    Liberation addLiberation(Liberation Liberation ) ;
    Liberation getLiberation( int id_Liberation ) ;
    void updateLiberation( Liberation Liberation );
    void deleteLiberation( int id_Liberation );
    List<Liberation> listLiberation() ;
}
