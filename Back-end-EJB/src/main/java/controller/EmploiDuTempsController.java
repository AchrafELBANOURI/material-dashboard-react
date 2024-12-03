package controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

import metier.IEmploiDuTempsLocal;
import metier.entities.EmploiDuTemps;

@Path("/EmploiDuTemps")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmploiDuTempsController {

    @Inject
    private IEmploiDuTempsLocal EmploiDuTempservice;

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmploiDuTemps(EmploiDuTemps EmploiDuTemps) {
        try {
            EmploiDuTemps newEmploiDuTemps = EmploiDuTempservice.addEmploiDuTemps(EmploiDuTemps);
            return Response.status(Response.Status.CREATED).entity(newEmploiDuTemps).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmploiDuTemps(@PathParam("id") int id_EmploiDuTemps) {
        System.out.println("this is the id "+id_EmploiDuTemps);
        try {
            EmploiDuTemps EmploiDuTemps = EmploiDuTempservice.getEmploiDuTemps(id_EmploiDuTemps);
            return Response.ok(EmploiDuTemps).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmploiDuTemps> listEmploiDuTemps() {
        return EmploiDuTempservice.listEmploiDuTemps();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmploiDuTemps(EmploiDuTemps EmploiDuTemps) {
        try {
            EmploiDuTempservice.updateEmploiDuTemps(EmploiDuTemps);
            return Response.ok().entity(EmploiDuTemps).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmploiDuTemps(@PathParam("id") int id_EmploiDuTemps) {
        try {
            EmploiDuTempservice.deleteEmploiDuTemps(id_EmploiDuTemps);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}