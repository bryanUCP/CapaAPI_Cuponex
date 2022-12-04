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
import pojos.Respuesta;
import pojos.Usuario;

/**
 *
 * @author DELL
 */
@Path("usuarios")
public class UsuarioWS {
    
    @Context
    private UriInfo context;
    
    public UsuarioWS(){
        
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> buscarTodos(){
        List<Usuario> usuarios = null;
        SqlSession conn = mybatis.MybatisUtil.getSession();
        if(conn !=null){
            try{
                usuarios = conn.selectList("usuario.getAllUsuarios");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return usuarios;
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //PARA ESCRIBIR LOS KEY EN EL POSTMAN, ejemplo ApellidoPaterno
    public Respuesta registrar(@FormParam("nombre") String nombre,
                            @FormParam("apellidoPaterno") String apellidoPaterno,
                            @FormParam("apellidoMaterno") String apellidoMaterno,
                            @FormParam("telefono") String telefono,
                            @FormParam("correo") String correo,
                            @FormParam("calle") String calle,
                            @FormParam("numero") String numero,
                            @FormParam("fechaNacimiento") String fechaNacimiento,
                            @FormParam("contrasena") String contrasena){
        
        //metimos todos estos datos en este objeto porque el mapper solo acepta un dato, pero ya siendo 
        //un objeto, puedo sacar cualquier dato que necesite
        
        //LOS MANDO AL MAPPER
        Usuario usuarioRegistro = new Usuario();
        usuarioRegistro.setNombre(nombre);
        usuarioRegistro.setApellidoPaterno(apellidoPaterno);
        usuarioRegistro.setApellidoMaterno(apellidoMaterno);
        usuarioRegistro.setTelefono(telefono);
        usuarioRegistro.setCorreo(correo);
        usuarioRegistro.setCalle(calle);
        usuarioRegistro.setNumero(numero);
        usuarioRegistro.setFechaNacimiento(fechaNacimiento);
        usuarioRegistro.setContrasena(contrasena);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                int resultadoMapper = conexionBD.insert("usuario.registrar", usuarioRegistro); //dirige al MedicoMapper
                conexionBD.commit(); //para plasmar los cambios que se hayan realizado, esto a nivel BD
                if(resultadoMapper>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Usuario registrado correctamente");
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
    public Respuesta modificar(@FormParam("idUsuario") Integer idUsuario,
                            @FormParam("nombre") String nombre,
                            @FormParam("apellidoPaterno") String apellidoPaterno,
                            @FormParam("apellidoMaterno") String apellidoMaterno,
                            @FormParam("telefono") String telefono,
                            @FormParam("calle") String calle,
                            @FormParam("numero") String numero,
                            @FormParam("fechaNacimiento") String fechaNacimiento,
                            @FormParam("contrasena") String contrasena){
        
        //metimos todos estos datos en este objeto porque el mapper solo acepta un dato, pero ya siendo 
        //un objeto, puedo sacar cualquier dato que necesite
        
        //LOS MANDO AL MAPPER
        Usuario usuarioModificar = new Usuario();
        
        usuarioModificar.setIdUsuario(idUsuario);
        usuarioModificar.setNombre(nombre);
        usuarioModificar.setApellidoPaterno(apellidoPaterno);
        usuarioModificar.setApellidoMaterno(apellidoMaterno);
        usuarioModificar.setTelefono(telefono);
        usuarioModificar.setCalle(calle);
        usuarioModificar.setNumero(numero);
        usuarioModificar.setFechaNacimiento(fechaNacimiento);
        usuarioModificar.setContrasena(contrasena);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                int resultadoMapper = conexionBD.update("usuario.modificar", usuarioModificar); //dirige al MedicoMapper
                conexionBD.commit(); //para plasmar los cambios que se hayan realizado, esto a nivel BD
                if(resultadoMapper>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Usuario modificado correctamente");
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
    public Respuesta eliminar(@FormParam("idUsuario") Integer idUsuario){
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                int resultadoMapper = conexionBD.delete("usuario.eliminar", idUsuario); //dirige al MedicoMapper
                conexionBD.commit(); //para plasmar los cambios que se hayan realizado, esto a nivel BD
                if(resultadoMapper>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Usuario eliminado correctamente");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("El identificador del usuario enviado, no existe");
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
