package nl.qb.pagila.delivery.ws.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import nl.qb.pagila.domain.dto.FilmInfo;

/**
 * Service endpoint interface for FilmRent webservice.
 * example url : http://localhost:8080/pagila-delivery-ws/services/films/1
 */
@Path("/films")
@Produces("application/json")
public interface FilmRentalWebservice {
    /**
     * Get film info by film id.
     * @param filmId film id
     */
    @GET
    @Path("/{id: \\d+}")
    FilmInfo getFilmInfo(@PathParam("id") int filmId);
}
