package metier.entities ;

import java.util.List;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class Filiere {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int filiere_id;
    private String nom;
    private int effectif;

    @ManyToOne(fetch = FetchType.EAGER)  // EAGER loading is fine for serialization
    @JoinColumn(name = "coordFiliere_id")
    private CoordinateurFiliere coordFiliere;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonbTransient
    private List<Matiere> listMatieres;

    @OneToMany(mappedBy = "filiere", fetch = FetchType.LAZY)
    @JsonbTransient
    private List<EmploiDuTemps> listEmploiDuTemps;

    @OneToMany(mappedBy = "filiere", fetch = FetchType.LAZY)
    @JsonbTransient
    private List<ChargeHoraire> listChargeHoraire;

    @OneToMany(mappedBy = "filiere", fetch = FetchType.LAZY)
    @JsonbTransient
    private List<Reservation> listReservations;
}
