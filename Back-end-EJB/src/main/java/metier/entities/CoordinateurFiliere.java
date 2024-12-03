package metier.entities;

import java.util.List;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;

@Entity

@Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CoordinateurFiliere {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int coordFiliere_id ;
	
	private String nom ;
	private String prenom ;
	
    @OneToMany(mappedBy = "coordFiliere",fetch = FetchType.EAGER)
	@JsonbTransient
    private List<Filiere> listFilieres ;
}