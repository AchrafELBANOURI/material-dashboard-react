package controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import metier.IFiliereLocal;
import metier.entities.CoordinateurFiliere;
import metier.entities.Filiere;

@Path("/filieres")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FiliereController {

    @Inject
    private IFiliereLocal filiereService;

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFiliere(Filiere filiere) {
        try {
            Filiere newFiliere = filiereService.addFiliere(filiere);
            return Response.status(Response.Status.CREATED).entity(newFiliere).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFiliere(@PathParam("id") int id) {
    	System.out.println("this is the id "+id);
        try {
            Filiere filiere = filiereService.getFiliere(id);
            return Response.ok(filiere).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Filiere> listFilieresByCoordFiliere(CoordinateurFiliere coordFiliere) {
        return filiereService.listFilieresByCoordFiliere( coordFiliere );
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFiliere(Filiere filiere) {
        try {
            filiereService.updateFiliere(filiere);
            return Response.ok().entity(filiere).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFiliere(@PathParam("id") int id) {
        try {
            filiereService.deleteFiliere(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}