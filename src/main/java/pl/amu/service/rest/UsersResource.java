package pl.amu.service.rest;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import pl.amu.service.rest.model.Cat;
import pl.amu.service.rest.model.ErrorMessage;
import pl.amu.service.rest.model.User;
import pl.amu.service.rest.exception.UsersAppExceptions;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Api
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    @Autowired
    private UsersService usersService;
    @Autowired
    private CatsService catsservice;
    private static Logger logger = LoggerFactory.getLogger(UsersResource.class);

    @GET
    public Response getUsers() {
        logger.info("GET /users");
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(30);
       /* UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("User name:  {}", userDetails.getUsername());

        logger.info("User password:  {}", userDetails.getPassword());

        logger.info("Account NonExpired:  {}", userDetails.isAccountNonExpired());

        logger.info("User granted roles:  {}", userDetails.getAuthorities());*/

        return Response.ok(usersService.getUsers())
                .cacheControl(cacheControl)
                .build();
    }

    @POST
    @ApiOperation("Creates new user")
    @ApiResponses({
            @ApiResponse(code = 409, message = "Returned when user with given login is already registered", response = ErrorMessage.class)
    })
    public Response registerUser(@Valid User user) throws UsersAppExceptions {
        if (usersService.findByLogin(user.getLogin()) == null) {
            logger.info("POST /users");
            usersService.nextversion();
            return Response
                    .ok(usersService.save(user))
                    .status(201)
                    .build();
        }

        throw new UsersAppExceptions("Użytkownik " + user.getLogin() + " już jest zarejestrowany", "USER_ALREADY_REGISTERED", Response.Status.CONFLICT);
    }

    @ApiOperation("Updates existing user data")
    @PUT()
    @ApiResponses({
            @ApiResponse(code = 404, message = "Returned when user with given login doesn't exist", response = ErrorMessage.class)
    })
    @Path("/{login}")
    public Response updateUser(@PathParam("login") String login, User user) throws UsersAppExceptions {
        User user0=usersService.findByLogin(login);
        if (user0 == null) {
            throw new UsersAppExceptions("Użytkownik " + login + " nie znaleziony", "USER_NOT_FOUND", Response.Status.NOT_FOUND);
        }
        logger.info("PUT /users/" + login);
        usersService.nextversion();
        user.setLogin(login);
        user0.nextversion();
        return Response.ok(usersService.save(user)).build();
    }

    @ApiOperation("Return user with given login")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Returned when user with given login doesn't exist", response = ErrorMessage.class)
    })
    @GET
    @Path("/{login}")
    public Response getUser(@PathParam("login") final String login, @Context  Request request) throws UsersAppExceptions {
        logger.info("GET /users/" + login);

        EntityTag dbTag = new EntityTag(String.valueOf(usersService.findByLogin(login).showversion()));
        if (dbTag != null) {
            Response.ResponseBuilder responseBuilder = request.evaluatePreconditions(dbTag);

            if (responseBuilder == null) {
                logger.info("reading from database");
                User user = usersService.findByLogin(login);

                if (user == null) {
                    throw new UsersAppExceptions("Użytkownik " + login + " nie znaleziony", "USER_NOT_FOUND", Response.Status.NOT_FOUND);
                }

                responseBuilder = Response.ok(user).tag(dbTag);
            }
            return responseBuilder.build();
        }
        else {
            throw new UsersAppExceptions(
                    "Użytkownik " + login + " nie znaleziony",
                    "USER_NOT_FOUND", Response.Status.NOT_FOUND);
        }

    }

    @ApiOperation("Removes user from database")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Returned when user with given login doesn't exist", response = ErrorMessage.class)
    })
    @DELETE
    @Path("/{login}")
    public Response deleteUser(@PathParam("login") String login) throws UsersAppExceptions {
        login=login.toLowerCase();
        User user = usersService.remove(login);
        logger.info("DELETE /users/" + login);
        if (user == null) {
            throw new UsersAppExceptions("Użytkownik " + login + " nie znaleziony", "USER_NOT_FOUND", Response.Status.NOT_FOUND);
        }
        usersService.nextversion();
        catsservice.nextversion();
        Cat cat;
        if(user.observedcats.size()>0) {
            for (String Id : user.observedcats) {
                cat = catsservice.findById(Id);
                cat.nextversion();
                cat.hoomans.remove(login);

            }
        }
        return Response.ok(user)
                .build();
    }


}
