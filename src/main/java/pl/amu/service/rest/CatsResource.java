package pl.amu.service.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import pl.amu.service.rest.model.Cat;
import pl.amu.service.rest.model.ErrorMessage;
import pl.amu.service.rest.exception.UsersAppExceptions;
import pl.amu.service.rest.model.User;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * Created by win10 on 14.05.2017.
 */
@Api
@Path("/cats")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CatsResource {
    @Autowired
    private CatsService catsService;
    @Autowired
    private UsersService usersService;
    private static Logger logger = LoggerFactory.getLogger(CatsResource.class);

    @GET
    @ApiOperation("Shows all cats")
    public Response getAllCats() {
        logger.info("GET /cats");
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(30);
        return Response.ok(catsService.getCats())
                .cacheControl(cacheControl)
                .build();
    }

    @POST
    @ApiResponses({
            @ApiResponse(code = 409, message = "Returned when cat with given id is already in database", response = ErrorMessage.class)
    })
    @ApiOperation("Adds a cat")
    public Response createCat(@Valid Cat cat) throws UsersAppExceptions {
        if (catsService.findById(cat.getId().toLowerCase()) == null) {
            logger.info("POST /cats");
            catsService.nextversion();
            return Response
                    .ok(catsService.save(cat))
                    .status(201)
                    .build();
        }

        throw new UsersAppExceptions("Kot " + cat.getId() + " już jest w bazie", "USER_ALREADY_REGISTERED", Response.Status.CONFLICT);
    }

    @ApiOperation("Updates a cat")
    @PUT()
    @ApiResponses({
            @ApiResponse(code = 404, message = "Returned when cat with given id doesn't exist", response = ErrorMessage.class)
    })
    @Path("/{id}")
    public Response updateCat(@PathParam("id") String Id, Cat cat) throws UsersAppExceptions {
        logger.info("PUT /cats/" + Id);
        Cat cat0=catsService.findById(Id);
        if (cat0 == null) {
            throw new UsersAppExceptions("Kot o id " + Id + " nie znaleziony", "USER_NOT_FOUND", Response.Status.CONFLICT);
        }
        catsService.nextversion();
        cat0.nextversion();
        cat.setId(Id);
        return Response.ok(catsService.save(cat)).build();
    }

    @ApiOperation("Returns cat with given id")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Returned when cat with given id doesn't exist", response = ErrorMessage.class)
    })
    @GET
    @Path("/{id}")
    public Response getCat(@PathParam("id") final String Id, @Context Request request) throws UsersAppExceptions {
        logger.info("GET /cats/" + Id);

        EntityTag dbTag = new EntityTag(String.valueOf(catsService.findById(Id).showversion()));
        if (dbTag != null) {
            Response.ResponseBuilder responseBuilder = request.evaluatePreconditions(dbTag);

            if (responseBuilder == null) {
                logger.info("reading from database");
                Cat cat = catsService.findById(Id);

                if (cat == null) {
                    throw new UsersAppExceptions("Kot " + Id + " nie znaleziony", "USER_NOT_FOUND", Response.Status.NOT_FOUND);
                }

                responseBuilder = Response.ok(cat).tag(dbTag);
            }
            return responseBuilder.build();
        }
        else {
            throw new UsersAppExceptions(
                    "Kot " + Id + " nie znaleziony",
                    "USER_NOT_FOUND", Response.Status.NOT_FOUND);
        }
    }

    @ApiOperation("Removes cat from database")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Returned when cat with given id doesn't exist", response = ErrorMessage.class)
    })
    @DELETE
    @Path("/{id}")
    public Response deleteCat(@PathParam("id") String Id) throws UsersAppExceptions {
        Id=Id.toLowerCase();
        logger.info("DELETE /cats/" + Id);
        Cat cat = catsService.remove(Id);
        if (cat == null) {
            throw new UsersAppExceptions("Kot o id " + Id + " nie znaleziony", "CAT_NOT_FOUND", Response.Status.NOT_FOUND);
        }
        catsService.nextversion();
        usersService.nextversion();
        for(String hooman:cat.hoomans){
            User user0=usersService.findByLogin(hooman);
            user0.observedcats.remove(Id);
            user0.nextversion();
        }
        return Response.ok(cat)
                .build();
    }
}
