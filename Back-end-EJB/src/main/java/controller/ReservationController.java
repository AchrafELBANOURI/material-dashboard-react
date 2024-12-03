package controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

import metier.IReservationLocal;
import metier.entities.Reservation;

@Path("/Reservation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservationController {

    @Inject
    private IReservationLocal Reservationervice;

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReservation(Reservation Reservation) {
        try {
            Reservation newReservation = Reservationervice.addReservation(Reservation);
            return Response.status(Response.Status.CREATED).entity(newReservation).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservation(@PathParam("id") int id_Reservation) {
        System.out.println("this is the id "+id_Reservation);
        try {
            Reservation Reservation = Reservationervice.getReservation(id_Reservation);
            return Response.ok(Reservation).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> listReservation() {
        return Reservationervice.listReservation();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateReservation(Reservation Reservation) {
        try {
            Reservationervice.updateReservation(Reservation);
            return Response.ok().entity(Reservation).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteReservation(@PathParam("id") int id_Reservation) {
        try {
            Reservationervice.deleteReservation(id_Reservation);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}