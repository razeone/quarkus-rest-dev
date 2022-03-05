package com.ibm;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/health")
public class HealthResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response health() {
        return Response.ok(Status.STATUS_STRING).build();
    }
    
    public static class Status {
        
        private Status() {
        }

        public static final String STATUS_STRING = "{\"status\":\"UP\"}";
    }
    
}
