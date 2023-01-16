package com.exece.oq.rest;
import com.exece.oq.core.ControllerAccesorio; 
import com.exece.oq.model.Accesorio; 
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author medin
 */
@Path("accesorio")
public class RESTAccesorio{

@POST
@Path("save")
@Produces(MediaType.APPLICATION_JSON)
//FormParam es para cuando usamos POST
public Response save(@FormParam("datosAccesorio") @DefaultValue("") String jsonAccesorio) throws Exception{
    String out = null;
    Gson gson = null;
    Accesorio acc = null;
    ControllerAccesorio ca = null;
    
     try {
            gson = new Gson();
            //Convierte la claase Empleado a JSON y luego a GSON
            acc = gson.fromJson(jsonAccesorio, Accesorio.class);
            ca = new ControllerAccesorio();
            //Si el Id del empleado es igual a 0 entonces se inserta el empleado
            //Si el Id del empleado NO es igual a 0 entonces se actualiza
            if (acc.getIdAccesorio()== 0) {
                ca.insert(acc);
            } else {
                ca.update(acc);
            }
            out = gson.toJson(acc);
        } catch (JsonParseException jpe) {
            jpe.printStackTrace();
            out = "{\"exception\":\"Error en los datos introducidos o de formato.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"Error interno del servidor.\"}";
        }
   return Response.status(Response.Status.OK).entity(out).build();
}

@GET
@Path("getAll")
@Produces(MediaType.APPLICATION_JSON)
public Response getAll(@QueryParam("filtro") @DefaultValue("") String filtro){
    String out = null;
    ControllerAccesorio ca = null;
    List<Accesorio> accesorios = null;
    
    try{
        ca = new ControllerAccesorio();
        accesorios = ca.getAll(filtro);
        out = new Gson().toJson(accesorios);
}
    catch(Exception e){
        e.printStackTrace();
        out = "{\"exception\":\"Error interno del servidor.\"}";
    }
    return Response.status(Response.Status.OK).entity(out).build();
}

@GET
@Path("deleteAccesorio")
@Produces(MediaType.APPLICATION_JSON)
public Response borrarAccesorio(@QueryParam("idAccesorio") int id) throws Exception{
    
    ControllerAccesorio ca = new ControllerAccesorio();
    String out = " ";
    
    int respuesta = ca.delete(id);
    
    if(respuesta == 1){
        out = """
              {"message": "accesory has been delete"}
              """;
    }
    else{
        out = """
              {"message": "hasn't been deleted"}
              """;
    }
    return Response.status(Response.Status.OK).entity(out).build();
}
}
