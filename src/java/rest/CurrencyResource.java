/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import facades.CurrencyFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Adam
 */
@Path("currency")
public class CurrencyResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CurrencyResource
     */
    public CurrencyResource() {
    }

    /**
     * Retrieves representation of an instance of rest.CurrencyResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path ("/dailyrates")
    public String getAll() {
        Gson gson = new Gson();
        CurrencyFacade cf = new CurrencyFacade();
        //System.out.println();
        return gson.toJson(cf.getComplete());
    }
    
    @GET

    /**
     * PUT method for updating or creating an instance of CurrencyResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
