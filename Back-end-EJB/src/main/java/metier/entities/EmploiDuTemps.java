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
public class EmploiDuTemps {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	
	private String semestre ; 
	private String anneeUniversitaire ;

	@ManyToOne
	@JoinColumn(name = "filiere_id")
	private Filiere filiere ;

	@OneToMany(mappedBy="emploidutemps",fetch = FetchType.EAGER)
	@JsonbTransient
	private List<Seance> listSeances ;
}