package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.TransportationTypeFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

@Path("transportationtype")
public class TransportationTypeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final TransportationTypeFacade FACADE =  TransportationTypeFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id){
        return Response.ok().entity(GSON.toJson(FACADE.getTransportationTypeById(id))).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll(){
        return Response.ok().entity(GSON.toJson(FACADE.getAllTranportationType())).build();
    }

}
