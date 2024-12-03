package metier;

import java.util.List;

import jakarta.ejb.Remote;
import metier.entities.CoordinateurFiliere;
import metier.entities.Filiere;

@Remote
public interface IFiliereRemote {
	Filiere addFiliere( Filiere filiere ) ;
	Filiere getFiliere( int id_filiere ) ;
	void updateFiliere( Filiere filiere ) ;
	void deleteFiliere( int id_filiere ) ;
	List<Filiere> listFilieresByCoordFiliere(CoordinateurFiliere coordFiliere) ;
}
