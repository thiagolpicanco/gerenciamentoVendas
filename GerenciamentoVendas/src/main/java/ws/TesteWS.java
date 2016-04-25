package ws;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@WebService
@Path("/teste")
public class TesteWS {

	
	@GET
	@Produces("text/plain")
	public String showHelloWorld() {
		return "Olá mundo!";
	}
	
	
	
}
