
package ws;

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
import pojos.Respuesta;
import pojos.Sucursal;

/**
 *
 * @author DELL
 */
@Path("sucursales")
public class SucursalWS {
    
    @Context
    private UriInfo context;
    
    public SucursalWS(){
        
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursal> buscarTodos(){
        List<Sucursal> sucursales = null;
        SqlSession conn = mybatis.MybatisUtil.getSession();
        if(conn !=null){
            try{
                sucursales = conn.selectList("sucursal.getAllSucursales");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return sucursales;
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //PARA ESCRIBIR LOS KEY EN EL POSTMAN, ejemplo ApellidoPaterno
    public Respuesta registrar(@FormParam("nombre") String nombre,
                            @FormParam("calle") String calle,
                            @FormParam("numero") String numero,
                            @FormParam("codigoPostal") String codigoPostal,
                            @FormParam("colonia") String colonia,
                            @FormParam("ciudad") String ciudad,
                            @FormParam("telefono") String telefono,
                            @FormParam("latitud") String latitud,
                            @FormParam("longitud") String longitud,
                            @FormParam("nombreEncargado") String nombreEncargado,
			    @FormParam("idEmpresa") Integer idEmpresa){
        
        //metimos todos estos datos en este objeto porque el mapper solo acepta un dato, pero ya siendo 
        //un objeto, puedo sacar cualquier dato que necesite
        
        //LOS MANDO AL MAPPER
        Sucursal sucursalRegistro = new Sucursal();
        sucursalRegistro.setNombre(nombre);
        sucursalRegistro.setCalle(calle);
        sucursalRegistro.setNumero(numero);
        sucursalRegistro.setCodigoPostal(codigoPostal);
        sucursalRegistro.setColonia(colonia);
        sucursalRegistro.setCiudad(ciudad);
        sucursalRegistro.setTelefono(telefono);
        sucursalRegistro.setLatitud(latitud);
        sucursalRegistro.setLongitud(longitud);
        sucursalRegistro.setNombreEncargado(nombreEncargado);
        sucursalRegistro.setIdEmpresa(idEmpresa);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                int resultadoMapper = conexionBD.insert("sucursal.registrar", sucursalRegistro); //dirige al MedicoMapper
                conexionBD.commit(); //para plasmar los cambios que se hayan realizado, esto a nivel BD
                if(resultadoMapper>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Sucursal registrada correctamente");
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
    public Respuesta modificar(@FormParam("idSucursal") Integer idSucursal,
                            @FormParam("nombre") String nombre,
                            @FormParam("calle") String calle,
                            @FormParam("numero") String numero,
                            @FormParam("codigoPostal") String codigoPostal,
                            @FormParam("colonia") String colonia,
                            @FormParam("ciudad") String ciudad,
                            @FormParam("telefono") String telefono,
                            @FormParam("latitud") String latitud,
                            @FormParam("longitud") String longitud,
                            @FormParam("nombreEncargado") String nombreEncargado,
			    @FormParam("idEmpresa") Integer idEmpresa){
        
        //metimos todos estos datos en este objeto porque el mapper solo acepta un dato, pero ya siendo 
        //un objeto, puedo sacar cualquier dato que necesite
        
        //LOS MANDO AL MAPPER
        Sucursal sucursalModificar = new Sucursal();
        
        sucursalModificar.setIdSucursal(idSucursal);
        sucursalModificar.setNombre(nombre);
        sucursalModificar.setCalle(calle);
        sucursalModificar.setNumero(numero);
        sucursalModificar.setCodigoPostal(codigoPostal);
        sucursalModificar.setColonia(colonia);
        sucursalModificar.setCiudad(ciudad);
        sucursalModificar.setTelefono(telefono);
        sucursalModificar.setLatitud(latitud);
        sucursalModificar.setLongitud(longitud);
        sucursalModificar.setNombreEncargado(nombreEncargado);
        sucursalModificar.setIdEmpresa(idEmpresa);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                int resultadoMapper = conexionBD.update("sucursal.modificar", sucursalModificar); //dirige al MedicoMapper
                conexionBD.commit(); //para plasmar los cambios que se hayan realizado, esto a nivel BD
                if(resultadoMapper>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Sucursal modificado correctamente");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("No se pudo modificar la sucursal correctamente");
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
    public Respuesta eliminar(@FormParam("idSucursal") Integer idSucursal){
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                int resultadoMapper = conexionBD.delete("sucursal.eliminar", idSucursal); //dirige al MedicoMapper
                conexionBD.commit(); //para plasmar los cambios que se hayan realizado, esto a nivel BD
                if(resultadoMapper>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Sucursal eliminado correctamente");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("El identificador de la sucursal enviado, no existe");
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
    public List<Sucursal> buscarByID(@PathParam("datoBuscar") String datoBuscar){ //Aqui usamos PathParam porque recibimos el datos por la URL
        //Empresa empresaResultado = null;
        List<Sucursal> empresaResultado = null;
        SqlSession conn = mybatis.MybatisUtil.getSession();
        if(conn !=null){
            try{
                empresaResultado = conn.selectList("sucursal.buscar", datoBuscar);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return empresaResultado;  
    }
    
}
