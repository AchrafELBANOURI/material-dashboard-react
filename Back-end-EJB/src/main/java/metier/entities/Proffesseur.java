package metier.entities;

import java.util.List;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Data @Getter @Setter @NoArgsConstructor
public class Proffesseur {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long prof_id ;
	
	private String nom ; 
	private String prenom ;
	
	@OneToMany(mappedBy="proffesseur")
	@JsonbTransient
	private List<Reservation> listReservations ;

	@OneToMany(mappedBy = "proffesseur")
	@JsonbTransient
	private List<Seance> listSeances ;
}