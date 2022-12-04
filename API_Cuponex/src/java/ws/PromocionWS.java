/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojos.Promocion;
import pojos.Respuesta;

/**
 *
 * @author DELL
 */
@Path("promociones")
public class PromocionWS {
    
    @Context
    private UriInfo context;
    
    public PromocionWS(){
       
    }
    
    public static String fechaActual(){
        Date fecha=new Date();
        //SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/YYYY");
        SimpleDateFormat formatoFecha=new SimpleDateFormat("YYYY/MM/dd");
        return formatoFecha.format(fecha);
    }
    
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Promocion> buscarTodos(){
        List<Promocion> promociones = null;
        SqlSession conn = mybatis.MybatisUtil.getSession();
        if(conn !=null){
            try{
                promociones = conn.selectList("promocion.getAllPromociones");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return promociones;
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //PARA ESCRIBIR LOS KEY EN EL POSTMAN, ejemplo ApellidoPaterno
    public Respuesta registrar(@FormParam("nombre") String nombre,
                            @FormParam("descripcion") String descripcion,
                            @FormParam("fechaInicio") String fechaInicio,
                            @FormParam("fechaFinal") String fechaFinal,
                            @FormParam("restriccion") String restriccion,
                            @FormParam("idTipoPromocion") Integer idTipoPromocion,
                            @FormParam("porcentajeDescuento") String porcentajeDescuento,
                            @FormParam("costoPromocion") String costoPromocion,
                            @FormParam("idCategoria") Integer idCategoria,
                            @FormParam("idEstatus") Integer idEstatus,
                            @FormParam("idEmpresa") Integer idEmpresa){
        
        //metimos todos estos datos en este objeto porque el mapper solo acepta un dato, pero ya siendo 
        //un objeto, puedo sacar cualquier dato que necesite
        
        //LOS MANDO AL MAPPER
        Promocion promocionRegistro = new Promocion();
        
        promocionRegistro.setNombre(nombre);
        promocionRegistro.setDescripcion(descripcion);
        promocionRegistro.setFechaInicio(fechaInicio);
        promocionRegistro.setFechaFinal(fechaFinal);
        promocionRegistro.setRestriccion(restriccion);
        promocionRegistro.setIdTipoPromocion(idTipoPromocion);
        promocionRegistro.setPorcentajeDescuento(porcentajeDescuento);
        promocionRegistro.setCostoPromocion(costoPromocion);
        promocionRegistro.setIdCategoria(idCategoria);
        promocionRegistro.setIdEstatus(idEstatus);
        promocionRegistro.setIdEmpresa(idEmpresa);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                int resultadoMapper = conexionBD.insert("promocion.registrar", promocionRegistro); //dirige al MedicoMapper
                conexionBD.commit(); //para plasmar los cambios que se hayan realizado, esto a nivel BD
                if(resultadoMapper>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Promocion registrada correctamente");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("No se pudo guardar la promocion enviada");
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
    public Respuesta modificar(@FormParam("idPromocion") Integer idPromocion,
                            @FormParam("nombre") String nombre,
                            @FormParam("descripcion") String descripcion,
                            @FormParam("restriccion") String restriccion,
                            @FormParam("idTipoPromocion") Integer idTipoPromocion,
                            @FormParam("porcentajeDescuento") String porcentajeDescuento,
                            @FormParam("costoPromocion") String costoPromocion,
                            @FormParam("idCategoria") Integer idCategoria,
                            @FormParam("idEstatus") Integer idEstatus,
                            @FormParam("idEmpresa") Integer idEmpresa){
        
        //metimos todos estos datos en este objeto porque el mapper solo acepta un dato, pero ya siendo 
        //un objeto, puedo sacar cualquier dato que necesite
        
        //LOS MANDO AL MAPPER
        Promocion promocionModificar = new Promocion();
        
        promocionModificar.setIdPromocion(idPromocion);
        promocionModificar.setNombre(nombre);
        promocionModificar.setDescripcion(descripcion);
        promocionModificar.setRestriccion(restriccion);
        promocionModificar.setIdTipoPromocion(idTipoPromocion);
        promocionModificar.setPorcentajeDescuento(porcentajeDescuento);
        promocionModificar.setCostoPromocion(costoPromocion);
        promocionModificar.setIdCategoria(idCategoria);
        promocionModificar.setIdEstatus(idEstatus);
        promocionModificar.setIdEmpresa(idEmpresa);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                int resultadoMapper = conexionBD.update("promocion.modificar", promocionModificar); //dirige al MedicoMapper
                conexionBD.commit(); //para plasmar los cambios que se hayan realizado, esto a nivel BD
                if(resultadoMapper>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Promocion modificada correctamente");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("No se pudo modificar la promocion correctamente");
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
    public Respuesta eliminar(@FormParam("idPromocion") Integer idPromocion){
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                int resultadoMapper = conexionBD.delete("promocion.eliminar", idPromocion); //dirige al MedicoMapper
                conexionBD.commit(); //para plasmar los cambios que se hayan realizado, esto a nivel BD
                if(resultadoMapper>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Promocion eliminada correctamente");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("El identificador de la promocion enviada, no existe");
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
    
    @Path("buscar/{datoBuscar}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Promocion> buscarByID(@PathParam("datoBuscar") String datoBuscar){ //Aqui usamos PathParam porque recibimos el datos por la URL
        //Empresa empresaResultado = null;
        List<Promocion> promocionResultado = null;
        SqlSession conn = mybatis.MybatisUtil.getSession();
        if(conn !=null){
            try{
                promocionResultado = conn.selectList("promocion.buscar", datoBuscar);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return promocionResultado;  
    }
    
    @Path("eliminar_estatus")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminar_estatus(@FormParam("idPromocion") Integer idPromocion){
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                int resultadoMapper = conexionBD.update("promocion.eliminar_estatus", idPromocion); //dirige al MedicoMapper
                conexionBD.commit(); //para plasmar los cambios que se hayan realizado, esto a nivel BD
                if(resultadoMapper>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Promocion eliminado correctamente (Inactivo)");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("El identificador de la promocion enviada, no existe");
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
    
    @Path("listaPromociones")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Promocion> listaPromociones(){
        List<Promocion> promociones = null;
        SqlSession conn = mybatis.MybatisUtil.getSession();
        if(conn !=null){
            try{
                promociones = conn.selectList("promocion.listaPromociones", fechaActual());
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        System.out.println(fechaActual());
        return promociones;
    }
    
    
}
