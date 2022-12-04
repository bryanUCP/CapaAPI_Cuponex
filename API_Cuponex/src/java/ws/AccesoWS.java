/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.apache.ibatis.session.SqlSession;
import pojos.Administrador;
import pojos.Usuario;
import pojos.respuestaLogin;

/**
 *
 * @author DELL
 */
@Path("acceso")
public class AccesoWS {
    
    @Context
    private UriInfo context;
    
    public AccesoWS(){
        
    }
    
    @Path("escritorio")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public respuestaLogin iniciarSesionEscritorio(@FormParam("correo") String correo,
                                                  @FormParam("contrasena") String contrasena){
            respuestaLogin resp = new respuestaLogin();
            
            Administrador administradorLogin = new Administrador();
            administradorLogin.setCorreo(correo);
            administradorLogin.setContrasena(contrasena);
            
            Administrador administradorResultado = null;
            SqlSession conn = mybatis.MybatisUtil.getSession();
            if(conn != null){
                try{
                    administradorResultado = conn.selectOne("administrador.loginEscritorio",administradorLogin);
                    if(administradorResultado==null){
                        resp.setError(true);
                        resp.setMensaje("Administrador y/o contraseña incorrectas");
                    }else{
                        resp.setError(false);
                        resp.setMensaje("Administrador encontrado...!!!");
                        resp.setNombre(administradorResultado.getNombre());
                        resp.setApellidoPaterno(administradorResultado.getApellidoPaterno());
                        resp.setApellidoMaterno(administradorResultado.getApellidoMaterno());
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    conn.close();
                }
            }
            /*if(noPersonal.equals("admin") && password.equals("12345")){
                resp.setError(false);
                resp.setMensaje("Usuario Encontrado...");
                resp.setNombre("Javier");
                resp.setApellidoPaterno("Perez");
                resp.setApellidoMaterno("Hernandez");
                
            }else{
                resp.setError(true);
                resp.setMensaje("Numero de personal y/o contraseña incorrectos...");
            }*/
            return resp;
        
    }
    
    @Path("movil")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public respuestaLogin iniciarSesionMovil(@FormParam("correo") String correo,
                                             @FormParam("contrasena") String contrasena){
        respuestaLogin respuesta = new respuestaLogin();
        Usuario usuarioLogin = new Usuario();
        usuarioLogin.setCorreo(correo);
        usuarioLogin.setContrasena(contrasena);
            
            Usuario usuarioResultado = null;
            SqlSession conn = mybatis.MybatisUtil.getSession();
            if(conn != null){
                try{
                    usuarioResultado = conn.selectOne("usuario.loginMovil",usuarioLogin);
                    if(usuarioResultado==null){
                        respuesta.setError(true);
                        respuesta.setMensaje("Usuario y/o contraseña incorrectas");
                    }else{
                        respuesta.setError(false);
                        respuesta.setMensaje("Usuario encontrado...!!!");
                        respuesta.setNombre(usuarioResultado.getNombre());
                        respuesta.setApellidoPaterno(usuarioResultado.getApellidoPaterno());
                        respuesta.setApellidoMaterno(usuarioResultado.getApellidoMaterno());
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    conn.close();
                }
            }
            
        /*Temporal
        if(usuario.equals("bryan") && password.equals("12345")){
            respuesta.setError(false);
            respuesta.setMensaje("Paciente encontrado...");
            respuesta.setNombre("bryan");
            respuesta.setApellidoPaterno("catalino");
            respuesta.setApellidoMaterno("piquet");
            
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("No existe registro de paciente con esas credenciales...");
        }*/
        return respuesta;
    }
}
