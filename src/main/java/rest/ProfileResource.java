package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ProfileDto;
import entities.User;
import facades.ProfileFacade;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("profile")
public class ProfileResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final ProfileFacade FACADE =  ProfileFacade.getInstance(EMF);

    private static final UserFacade USERFACADE =  UserFacade.getUserFacade(EMF);


    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProfile(String content) {
        ProfileDto profileDtoFromJSON = GSON.fromJson(content, ProfileDto.class);
         ProfileDto profileDto = FACADE.createProfile(profileDtoFromJSON);

         return Response.ok().entity(GSON.toJson(profileDto)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllProfiles() {
        String response = "Oi";

        return Response.ok().entity(GSON.toJson(response)).build();
    }

    @DELETE
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProfiles(@PathParam("username") String username){
        return Response.ok().entity(GSON.toJson(USERFACADE.deleteUser(username))).build();


    }
}
