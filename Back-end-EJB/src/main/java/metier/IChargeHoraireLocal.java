package metier;

import jakarta.ejb.Local;
import metier.entities.ChargeHoraire;
import metier.entities.ChargeHoraire_PK;

import java.util.List;

@Local
public interface IChargeHoraireLocal {
    ChargeHoraire addChargeHoraire(ChargeHoraire chargeHoraire ) ;
    ChargeHoraire getChargeHoraire(ChargeHoraire_PK pk) ;
    void updateChargeHoraire( ChargeHoraire chargeHoraire );
    void deleteChargeHoraire(ChargeHoraire_PK pk );
    List<ChargeHoraire> listChargeHoraires();
}
