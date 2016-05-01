package com.example.rest;

import com.example.arithmeticexpression.JsonExpression;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Admin on 29.04.2016.
 */
@Path("/expressions")
public class ExpressionService {

        @GET
        @Path("{expression}")
        @Produces(MediaType.APPLICATION_JSON)
        public String getTree(@PathParam("expression") String expression) throws IOException {
                JsonExpression jsonExpression = new JsonExpression();
                return jsonExpression.getJsonTreeFromExpression(expression).toString();
        }
}
