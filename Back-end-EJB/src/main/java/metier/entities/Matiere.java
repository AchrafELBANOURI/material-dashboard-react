package metier.entities;

import java.util.List;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data @Getter @Setter @NoArgsConstructor
public class Matiere {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int matiere_id;
    private String nom;
    
    @OneToMany(mappedBy="matiere",fetch = FetchType.LAZY)
    @JsonbTransient
    private List<ChargeHoraire> listChargeHoraire ;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonbTransient
    private List<Filiere> listFilieres;
    
    @OneToMany(mappedBy = "matiere",fetch = FetchType.LAZY)
    @JsonbTransient
    private List<Seance> listSeances;

    @OneToMany(mappedBy = "matiere" , fetch = FetchType.LAZY)
    @JsonbTransient
    private List<Reservation> listReservations;
}
