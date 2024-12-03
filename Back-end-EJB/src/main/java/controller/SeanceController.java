package controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import metier.ISeanceLocal;
import metier.entities.EmploiDuTemps;
import metier.entities.Seance;

@Path("/Seances")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SeanceController {

    @Inject
    private ISeanceLocal SeanceService;

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSeance(Seance Seance) {
        try {
            Seance newSeance = SeanceService.addSeance(Seance);
            return Response.status(Response.Status.CREATED).entity(newSeance).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeance(@PathParam("id") int id_seance) {
        System.out.println("this is the id "+id_seance);
        try {
            Seance Seance = SeanceService.getSeance(id_seance);
            return Response.ok(Seance).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Seance> listSeancesByEmploiDuTemps(EmploiDuTemps emploiDuTemps) {
        return SeanceService.listSeancesByEmploiduTemps( emploiDuTemps );
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSeance(Seance seance) {
        try {
            SeanceService.updateSeance(seance);
            return Response.ok().entity(seance).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSeance(@PathParam("id") int id_seance) {
        try {
            SeanceService.deleteSeance(id_seance);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}