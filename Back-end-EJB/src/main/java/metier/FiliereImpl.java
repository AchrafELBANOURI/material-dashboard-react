package metier;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

import metier.entities.CoordinateurFiliere;
import metier.entities.Filiere;
import metier.entities.Journal;

@Stateless
public class FiliereImpl implements IFiliereLocal, IFiliereRemote{
   @PersistenceContext(unitName="SalleEJB")
   private EntityManager em;

    public Filiere addFiliere(Filiere filiere) {
        // Check if the entity already exists in the persistence context by finding it in the database
        System.out.println("this is the id : "+filiere.getFiliere_id());
        System.out.println("this is the id_coord : "+filiere.getCoordFiliere().getCoordFiliere_id());
        System.out.println("this is the nom_coord : "+filiere.getCoordFiliere().getNom());
        System.out.println("this is the nom : "+filiere.getNom());

        Filiere existingFiliere = em.find(Filiere.class, filiere.getFiliere_id());

        if (existingFiliere != null) {
            // Entity exists, so merge it (it might be detached)
            System.out.println("this is the mergedid : "+existingFiliere.getFiliere_id());
            return em.merge(filiere);
        } else {
            // Entity doesn't exist, so persist it
            try{
                em.persist(filiere);
                Journal journal = new Journal();
                journal.setOperation("Add Filiere id="+filiere.getFiliere_id());
                journal.setTime(LocalDateTime.now());
                journal.setNom(filiere.getCoordFiliere().getNom());
                journal.setPrenom(filiere.getCoordFiliere().getPrenom());
                journal.setRole("Coordonateur de Filiere");
            }catch(Exception e){
                throw new RuntimeException(e.toString());
            }

            return filiere;
        }
    }



   public Filiere getFiliere(int id_filiere) {
      Filiere pr = this.em.find(Filiere.class, id_filiere);
      if (pr == null) {
         throw new RuntimeException("Filiere introuvable");
      } else {
         return pr;
      }
   }

   public List<Filiere> listFilieresByCoordFiliere(CoordinateurFiliere coordFiliere) {
        coordFiliere.setListFilieres(
                em.createQuery("select f from Filiere f where f.coordFiliere = :coordFiliere", Filiere.class)
                .setParameter("coordFiliere", coordFiliere)
                .getResultList()
        );
      return coordFiliere.getListFilieres();
   }

	@Override
	public void updateFiliere(Filiere filiere) {
		try{
            em.merge(filiere);
            Journal journal = new Journal();
            journal.setOperation("Update Filiere id="+filiere.getFiliere_id());
            journal.setTime(LocalDateTime.now());
            journal.setNom(filiere.getCoordFiliere().getNom());
            journal.setPrenom(filiere.getCoordFiliere().getPrenom());
            journal.setRole("Coordonateur de Filiere");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	@Override
	public void deleteFiliere(int id_filiere) {
        try {
            Filiere filiere = em.find(Filiere.class, id_filiere);
            em.remove(filiere);
            Journal journal = new Journal();
            journal.setOperation("DELETE Filiere id="+filiere.getFiliere_id());
            journal.setTime(LocalDateTime.now());
            journal.setNom(filiere.getCoordFiliere().getNom());
            journal.setPrenom(filiere.getCoordFiliere().getPrenom());
            journal.setRole("Coordonateur de Filiere");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

	}
}

