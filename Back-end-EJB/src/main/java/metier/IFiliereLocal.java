package metier;

import java.util.List;

import jakarta.ejb.Local;
import metier.entities.CoordinateurFiliere;
import metier.entities.Filiere;

@Local
public interface IFiliereLocal {
	Filiere addFiliere( Filiere filiere ) ;
	Filiere getFiliere( int id_filiere ) ;
	void updateFiliere( Filiere filiere ) ;
	void deleteFiliere( int id_filiere ) ;
	List<Filiere> listFilieresByCoordFiliere(CoordinateurFiliere coordFiliere) ;
}
