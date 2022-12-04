/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojos.Administrador;
import pojos.Respuesta;



/**
 *
 * @author DELL
 */
@Path("administradores")
public class AdministradorWS {
    
    @Context
    private UriInfo context;
    
    public AdministradorWS(){
        
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Administrador> buscarTodos(){
        List<Administrador> administradores = null;
        SqlSession conn = mybatis.MybatisUtil.getSession();
        if(conn !=null){
            try{
                administradores = conn.selectList("administrador.getAllAdministradores");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return administradores;
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //PARA ESCRIBIR LOS KEY EN EL POSTMAN, ejemplo ApellidoPaterno
    public Respuesta registrar(@FormParam("nombre") String nombre,
                            @FormParam("apellidoPaterno") String apellidoPaterno,
                            @FormParam("apellidoMaterno") String apellidoMaterno,
                            @FormParam("correo") String correo,
                            @FormParam("contrasena") String contrasena){
        
        //metimos todos estos datos en este objeto porque el mapper solo acepta un dato, pero ya siendo 
        //un objeto, puedo sacar cualquier dato que necesite
        
        //LOS MANDO AL MAPPER
        Administrador administradorRegistro = new Administrador();
        administradorRegistro.setNombre(nombre);
        administradorRegistro.setApellidoPaterno(apellidoPaterno);
        administradorRegistro.setApellidoMaterno(apellidoMaterno);
        administradorRegistro.setCorreo(correo);
        administradorRegistro.setContrasena(contrasena);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                int resultadoMapper = conexionBD.insert("administrador.registrar", administradorRegistro); //dirige al MedicoMapper
                conexionBD.commit(); //para plasmar los cambios que se hayan realizado, esto a nivel BD
                if(resultadoMapper>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Administrador registrado correctamente");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("No se pudo guardar el registro enviado");
                }
            }catch(Exception e){
                respuestaWS.setError(true);
                respuestaWS.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("Sin conexion con el sistema");
        }
        return respuestaWS;
        
    }
    
    @Path("modificar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta modificar(@FormParam("idAdministrador") Integer idAdministrador,
                            @FormParam("nombre") String nombre,
                            @FormParam("apellidoPaterno") String apellidoPaterno,
                            @FormParam("apellidoMaterno") String apellidoMaterno,
                            @FormParam("contrasena") String contrasena){
        
        //metimos todos estos datos en este objeto porque el mapper solo acepta un dato, pero ya siendo 
        //un objeto, puedo sacar cualquier dato que necesite
        
        //LOS MANDO AL MAPPER
        Administrador administradorModificar = new Administrador();
        
        administradorModificar.setIdAdministrador(idAdministrador);
        administradorModificar.setNombre(nombre);
        administradorModificar.setApellidoPaterno(apellidoPaterno);
        administradorModificar.setApellidoMaterno(apellidoMaterno);
        administradorModificar.setContrasena(contrasena);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                int resultadoMapper = conexionBD.update("administrador.modificar", administradorModificar); //dirige al MedicoMapper
                conexionBD.commit(); //para plasmar los cambios que se hayan realizado, esto a nivel BD
                if(resultadoMapper>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Administrador modificado correctamente");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("No se pudo modificar el registro correctamente");
                }
            }catch(Exception e){
                respuestaWS.setError(true);
                respuestaWS.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("Sin conexion con el sistema");
        }
       return respuestaWS;

    }
    
    @Path("eliminar")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminar(@FormParam("idAdministrador") Integer idAdministrador){
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                int resultadoMapper = conexionBD.delete("administrador.eliminar", idAdministrador); //dirige al MedicoMapper
                conexionBD.commit(); //para plasmar los cambios que se hayan realizado, esto a nivel BD
                if(resultadoMapper>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Administrador eliminado correctamente");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("El identificador del administrador enviado, no existe");
                }
            }catch(Exception e){
                respuestaWS.setError(true);
                respuestaWS.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("Sin conexion con el sistema");
        }
        return respuestaWS;
        
        
    }
    
}
