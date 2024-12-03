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
public class ResponsableDeSalle {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int respoSalle_id ; 
	
	private String nom ; 
	private String prenom ; 
	
	@OneToMany(mappedBy="respoSalle")
	@JsonbTransient
	private List<Salle> listSalles ; 
}
