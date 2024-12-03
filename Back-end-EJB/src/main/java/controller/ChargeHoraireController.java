package controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import metier.IChargeHoraireLocal;
import metier.entities.ChargeHoraire;
import metier.entities.ChargeHoraire_PK;

@Path("/chargeHoraire")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChargeHoraireController {

    @Inject
    private IChargeHoraireLocal ChargeHoraireService;

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addChargeHoraire(ChargeHoraire chargeHoraire) {
        try {
            ChargeHoraire newChargeHoraire = ChargeHoraireService.addChargeHoraire(chargeHoraire);
            return Response.status(Response.Status.CREATED).entity(newChargeHoraire).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<ChargeHoraire> listChargeHoraires() {
        return ChargeHoraireService.listChargeHoraires();
    }

    // JSON Text Format To Delete
//    {
//            "filiere": 1,
//            "matiere": 1,
//            "categorie": "COURS"
//    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ChargeHoraire getChargeHoraire( ChargeHoraire_PK pk ) {
        return ChargeHoraireService.getChargeHoraire( pk );
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMatiere(ChargeHoraire chargeHoraire) {
        try {
            ChargeHoraireService.updateChargeHoraire(chargeHoraire);
            return Response.ok().entity(chargeHoraire).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    // JSON Text Format To Delete
//    {
//        "filiere": 1,
//            "matiere": 1,
//            "categorie": "COURS"
//    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMatiere(ChargeHoraire_PK pk) {
        try {
            ChargeHoraireService.deleteChargeHoraire(pk);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}