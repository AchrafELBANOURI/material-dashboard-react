package controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

import metier.ILiberationLocal;
import metier.entities.Liberation;

@Path("/Liberation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LiberationController {

    @Inject
    private ILiberationLocal Liberationervice;

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLiberation(Liberation Liberation) {
        try {
            Liberation newLiberation = Liberationervice.addLiberation(Liberation);
            return Response.status(Response.Status.CREATED).entity(newLiberation).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLiberation(@PathParam("id") int id_Liberation) {
        System.out.println("this is the id "+id_Liberation);
        try {
            Liberation Liberation = Liberationervice.getLiberation(id_Liberation);
            return Response.ok(Liberation).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Liberation> listLiberation() {
        return Liberationervice.listLiberation();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLiberation(Liberation Liberation) {
        try {
            Liberationervice.updateLiberation(Liberation);
            return Response.ok().entity(Liberation).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLiberation(@PathParam("id") int id_Liberation) {
        try {
            Liberationervice.deleteLiberation(id_Liberation);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}