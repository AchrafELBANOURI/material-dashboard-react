package metier.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Data @Getter @Setter @NoArgsConstructor
public class Liberation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @ManyToOne
    @JoinColumn(name = "prof_id")
    private Proffesseur proffesseur ;

    @ManyToOne
    @JoinColumn(name = "nomSalle")
    private Salle salle ;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere ;

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    private Matiere matiere ;

    private LocalDateTime dateLiberation ;

    @Enumerated(EnumType.STRING)
    private TypeLiberation typeLiberation ;

    private LocalDateTime periodeLiberation ;
}
