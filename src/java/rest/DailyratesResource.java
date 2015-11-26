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
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author marcj_000
 */
@Path("dailyrates")
public class DailyratesResource {

    @Context
    private UriInfo context;
    
    CurrencyFacade cf = new CurrencyFacade();
    Gson gson = new Gson();
    /**
     * Creates a new instance of DailyratesResource
     */
    public DailyratesResource() {
    }

    /**
     * Retrieves representation of an instance of rest.DailyratesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {

       return gson.toJson(cf.getComplete());
    }

}
