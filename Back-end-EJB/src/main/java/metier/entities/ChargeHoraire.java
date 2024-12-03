package metier.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data @Getter @Setter @NoArgsConstructor
@IdClass(ChargeHoraire_PK.class)
public class ChargeHoraire {
    @Id
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;

    @Id
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn(name = "matiere_id")
    private Matiere matiere;

    @Id
    private Categorie categorie; 

    private int hours;
}
