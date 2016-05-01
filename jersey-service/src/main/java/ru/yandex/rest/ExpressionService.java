package ru.yandex.rest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import ru.yandex.core.JsonExpressionHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by Admin on 29.04.2016.
 */
@Path("/expressions")
public class ExpressionService {

        private final static Logger logger = Logger.getLogger(ExpressionService.class);
        @GET
        @Path("{expression}")
        @Produces(MediaType.APPLICATION_JSON)
        public String getTree(@PathParam("expression") String expression) throws IOException {
                try {
                        JsonExpressionHandler jsonExpressionHandler = JsonExpressionHandler.getInstance();
                        return jsonExpressionHandler.getJsonTreeFromExpression(expression).toString();
                } catch (Exception e) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("error", "400: Bad Request" + e.getMessage());
                        logger.error("400: Bad Request" + e.getMessage());
                        return jsonObject.toString();
                }
        }
}
