package controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import metier.ISalleLocal;
import metier.entities.ResponsableDeSalle;
import metier.entities.Salle;

@Path("/Salles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SalleController {

    @Inject
    private ISalleLocal SalleService;

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSalle(Salle Salle) {
        try {
            Salle newSalle = SalleService.addSalle(Salle);
            return Response.status(Response.Status.CREATED).entity(newSalle).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalle(@PathParam("id") String nomSalle) {
        System.out.println("this is the id "+nomSalle);
        try {
            Salle Salle = SalleService.getSalle(nomSalle);
            return Response.ok(Salle).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Salle> listSallesByRespoSalle(ResponsableDeSalle respoSalle) {
        return SalleService.listSallesByRespoSalle( respoSalle );
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSalle(Salle Salle) {
        try {
            SalleService.updateSalle(Salle);
            return Response.ok().entity(Salle).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSalle(@PathParam("id") String nomSalle) {
        try {
            SalleService.deleteSalle(nomSalle);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}