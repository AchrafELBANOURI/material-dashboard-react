package metier.entities;

import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data @Getter @Setter @NoArgsConstructor
public class Seance {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int seance_id ;
	
	private LocalTime debut ;
	private LocalTime fin ;

	private String jourdeSemaine ;
	
	@ManyToOne
	@JoinColumn(name = "matiere_id")
	private Matiere matiere ;
	
	@ManyToOne
	@JoinColumn(name = "nomSalle")
	private Salle salle ;

	@ManyToOne
	@JoinColumn(name="prof_id")
	private Proffesseur proffesseur ;
	
	@ManyToOne
	private EmploiDuTemps emploidutemps ;
	
}
