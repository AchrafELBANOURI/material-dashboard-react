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
public class Salle {
	@Id
	private String nomSalle ;
	private String localisation ; 
	private int nombredePlaces ;
	
	@Enumerated(EnumType.STRING)
	private Categorie typedeSalle ;
	
	@OneToMany(mappedBy="salle")
	@JsonbTransient
	private List<Seance> listSeances ;
	
	@OneToMany(mappedBy="salle")
	@JsonbTransient
	private List<Reservation> listReservations ; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "respoSalle_id")
	private ResponsableDeSalle respoSalle ;
}
