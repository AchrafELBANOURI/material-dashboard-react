package metier.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data @Getter @Setter @NoArgsConstructor
public class ChargeHoraire_PK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int filiere;
    private int matiere;
    private Categorie categorie;
}
