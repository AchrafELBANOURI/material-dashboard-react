package metier.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data @Getter @Setter @NoArgsConstructor
public class Journal {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private String operation;
    private LocalDateTime time;
    private String nom;
    private String prenom;
    private String role;
}
