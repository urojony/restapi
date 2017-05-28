package pl.amu.service.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import pl.amu.service.rest.model.Cat;
import pl.amu.service.rest.model.User;
import pl.amu.service.rest.exception.UsersAppExceptions;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pl.amu.service.rest.model.ErrorMessage;
/**
 * Created by win10 on 21.05.2017.
 */
@Api
@Path("/users/{login}/observedcats")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ObservingResource {
    @Autowired
    private CatsService catsservice;
    @Autowired
    private UsersService usersservice;
    @GET
    @ApiResponses({
            @ApiResponse(code = 404, message = "Returned when user with given login doesn't exist", response = ErrorMessage.class)
    })
    @ApiOperation("Shows all cats observed by user.")
    public Response getmycats(@PathParam("login") final String login) throws UsersAppExceptions {
        User user=usersservice.findByLogin(login.toLowerCase());
        if (user == null) {
            throw new UsersAppExceptions("Użytkownik " + login + " nie znaleziony", "USER_NOT_FOUND", Response.Status.NOT_FOUND);
        }
        return Response.ok(user.getObservedcats())
                .build();
    }
    @Path("/{Id}")
    @POST
    @ApiOperation("Starts observing a cat.")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Returned when user or cat doesn't exist", response = ErrorMessage.class),
            @ApiResponse(code = 409, message = "Returned when cat is already observed", response = ErrorMessage.class)
    })
    public Response addcat(@PathParam("login") final String login,@PathParam("Id") String Id) throws UsersAppExceptions{
        User user=usersservice.findByLogin(login.toLowerCase());
        if (user == null) {
            throw new UsersAppExceptions("Użytkownik " + login + " nie znaleziony", "USER_NOT_FOUND", Response.Status.NOT_FOUND);
        }
        Cat cat=catsservice.findById(Id);
        if(cat==null){
            throw new UsersAppExceptions("Kot o id " + Id + " nie znaleziony", "CAT_NOT_FOUND", Response.Status.NOT_FOUND);
        }
        if(user.observedcats.contains(cat)){
            throw new UsersAppExceptions("Kot o id " + Id + " jest już obserwowany.", "CAT_ALREADY_OBSERVED", Response.Status.CONFLICT);
        }
        usersservice.nextversion();
        catsservice.nextversion();
        user.nextversion();
        cat.nextversion();
        user.observedcats.add(Id);
        cat.hoomans.add(login.toLowerCase());
        return Response.ok(cat)
                .status(201)
                .build();
    }
    @Path("/{Id}")
    @DELETE
    @ApiOperation("Stop observing a cat.")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Returned when user or cat doesn't exist", response = ErrorMessage.class),
            @ApiResponse(code = 409, message = "Returned when cat is not observed", response = ErrorMessage.class)
    })
    public Response removecat(@PathParam("login") String login, @PathParam("Id") String Id) throws UsersAppExceptions{
        login=login.toLowerCase();
        Id=Id.toLowerCase();
        User user=usersservice.findByLogin(login.toLowerCase());
        if (user == null) {
            throw new UsersAppExceptions("Użytkownik " + login + " nie znaleziony", "USER_NOT_FOUND", Response.Status.NOT_FOUND);
        }
        if(!user.observedcats.remove(Id.toLowerCase())){
            throw new UsersAppExceptions("Kot o id " + Id + " nie znaleziony", "CAT_NOT_FOUND", Response.Status.NOT_FOUND);
        }
        Cat cat=catsservice.findById(Id.toLowerCase());
        if(!cat.hoomans.remove(login)){
            throw new UsersAppExceptions("Kot o id " + Id + " nie jest obserwowany.", "CAT_ALREADY_OBSERVED", Response.Status.CONFLICT);
        }

        return Response.ok(Id)
                .build();
    }
}
