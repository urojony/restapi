package pl.amu.service.rest;

import io.swagger.annotations.Api;
import pl.amu.service.rest.model.Cat;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by grzegorz on 16.05.17.
 */
@Api
@Path("/ttt")
public class TResource {
    @GET
    public Response fun() {
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat sdf2=new SimpleDateFormat("dd.MM.yyyy");
        Date dt1=null,dt2=null,dt3=null,dt4=null;
        try {
            dt1=sdf1.parse("1995.11.11");
            dt2=sdf2.parse("12.11.1995");
        }
        catch(Exception e){}
        try {
            dt3=sdf1.parse("11.11.1995");
        } catch (ParseException e) {
        }
        try{
            dt4=sdf2.parse("1995.11.11");
        }
        catch(Exception e){}
        return Response.ok(dt1.toString()+new SimpleDateFormat("yyyy-MM-dd").format(dt1))
                .build();
    }
    @POST
    public Response kotkot(Cat cat)
    {
        return Response.ok(cat.toString())
                .build();
    }
}
