package metier.entities;

import java.time.LocalDateTime;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Data @Getter @Setter @NoArgsConstructor
public class Reservation {
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
	
	private LocalDateTime debut ; 
	private LocalDateTime fin ;
}
