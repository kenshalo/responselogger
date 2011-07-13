package squareup.responselogger;

import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/locations")
@Produces("text/plain")
public class LocationResource {

	@GET
	public String listLocations() {
		return "List Locations...";
	}

	@GET
	@Path("{id}")
	public Response getLocation(@Context HttpServletResponse resp,
			@PathParam("id") int id) {
		return Response.status(genResponse(id)).build();
	}

	@PUT
	@Path("{id}")
	public Response createLocation(@PathParam("id") int id) {
		return Response.status(genResponse(id)).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteLocation(@PathParam("id") int id) {
		return Response.status(genResponse(id)).build();
	}
	
	private int genResponse(int id) {
		int c = 0;
		Random r = new Random(System.currentTimeMillis());
		if (id == 200) {
			c = 200 + r.nextInt(100);
		} else if (id == 300) {
			c = 300 + r.nextInt(100);
		} else if (id == 400) {
			c = 400 + r.nextInt(100);
		} else if (id == 500) {
			c = 500 + r.nextInt(100);
		}
		
		return c;
	}
}