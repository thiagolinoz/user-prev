package br.com.linoz.api;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.com.linoz.api.entity.AuthRequest;
import br.com.linoz.api.entity.AuthResponse;
import br.com.linoz.api.entity.User;
import br.com.linoz.api.util.PBKDF2Encoder;
import br.com.linoz.api.util.TokenUtils;

@Path("/dash")
public class AuthResource {
	
	@Inject
	PBKDF2Encoder passwordEncoder;
	@Inject
	TokenUtils tokenGenerator;
	
	@ConfigProperty(name = "br.com.linoz.user-prev.jwt.duration") public Long duration;
	@ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;
	private static final Logger LOG = Logger.getLogger(AuthResource.class);
	
	@PermitAll
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(AuthRequest authRequest) {
		User u = User.findByUserName(authRequest.getUsername());
		if (u != null && u.getPassword().equals(passwordEncoder.encode(authRequest.getPassword()))) {
			try {
				return Response.ok(new AuthResponse(tokenGenerator.generateToken(
						u.getUsername(), u.getRoles(), duration, issuer))).build();
			} catch (Exception exception) {
				LOG.error(exception.getMessage());
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} 
		
		return Response.status(Status.UNAUTHORIZED).build();
	
	}
}
