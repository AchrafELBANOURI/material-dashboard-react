package metier;

import jakarta.ejb.Remote;
import metier.entities.ChargeHoraire;
import metier.entities.ChargeHoraire_PK;
import metier.entities.Matiere;

import java.util.List;

@Remote
public interface IChargeHoraireRemote {
    ChargeHoraire addChargeHoraire(ChargeHoraire chargeHoraire ) ;
    ChargeHoraire getChargeHoraire(ChargeHoraire_PK pk) ;
    void updateChargeHoraire( ChargeHoraire chargeHoraire );
    void deleteChargeHoraire(ChargeHoraire_PK pk );
    List<ChargeHoraire> listChargeHoraires();
}
