package com.example.servico;

import com.example.Dao.UsuarioDAO;
import com.example.Dto.UsuarioDTO;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.springframework.http.MediaType;

@Path("/authentication")
public class ServicoLogin {
    
    UsuarioDAO usuarioDAO;

    public ServicoLogin(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public Response authenticateUser(UsuarioDTO usuario) {

        try {
            String username = usuario.getUser();
            String password = usuario.getSenha();
            // Authenticate the user using the credentials provided
            authenticate(username, password);

            // Issue a token for the user
            String token = issueToken(username);

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }      
    }

    private void authenticate(String username, String password) throws Exception {
       UsuarioDTO usuario = usuarioDAO.getByUser(username).get(0);
       
       if(!password.equals(usuario.getSenha())){
           throw new Exception("Nao foi possivel efetuar login");
       }
       
    }

    private String issueToken(String username) {
        Random random = new SecureRandom();
        String token = new BigInteger(130, random).toString();
        usuarioDAO.getAlls().stream().filter(u -> u.getUser().equals(username)).findAny().get().setToken(token);
        return token;
    }
}
