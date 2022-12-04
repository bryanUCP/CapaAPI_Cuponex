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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojos.Empresa;
import pojos.Respuesta;


/**
 *
 * @author DELL
 */
@Path("empresas")
public class EmpresaWS {
    
    @Context
    private UriInfo context;
    
    public EmpresaWS(){
        
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empresa> buscarTodos(){
        List<Empresa> empresas = null;
        SqlSession conn = mybatis.MybatisUtil.getSession();
        if(conn !=null){
            try{
                empresas = conn.selectList("empresa.getAllEmpresas");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return empresas;
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //PARA ESCRIBIR LOS KEY EN EL POSTMAN, ejemplo ApellidoPaterno
    public Respuesta registrar(@FormParam("nombre") String nombre,
                            @FormParam("nombreComercial") String nombreComercial,
                            @FormParam("nombreRepresentante") String nombreRepresentante,
                            @FormParam("email") String email,
                            @FormParam("calle") String calle,
                            @FormParam("numero") String numero,
                            @FormParam("codigoPostal") String codigoPostal,
                            @FormParam("ciudad") String ciudad,
                            @FormParam("telefono") String telefono,
                            @FormParam("paginaWeb") String paginaWeb,
                            @FormParam("RFC") String RFC,
                            @FormParam("idEstatus") Integer idEstatus){
        
        //metimos todos estos datos en este objeto porque el mapper solo acepta un dato, pero ya siendo 
        //un objeto, puedo sacar cualquier dato que necesite
        
        //LOS MANDO AL MAPPER
        Empresa empresaRegistro = new Empresa();
        empresaRegistro.setNombre(nombre);
        empresaRegistro.setNombreComercial(nombreComercial);
        empresaRegistro.setNombreRepresentante(nombreRepresentante);
        empresaRegistro.setEmail(email);
        empresaRegistro.setCalle(calle);
        empresaRegistro.setNumero(numero);
        empresaRegistro.setCodigoPostal(codigoPostal);
        empresaRegistro.setCiudad(ciudad);
        empresaRegistro.setTelefono(telefono);
        empresaRegistro.setPaginaWeb(paginaWeb);
        empresaRegistro.setRFC(RFC);
        empresaRegistro.setIdEstatus(idEstatus);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                int resultadoMapper = conexionBD.insert("empresa.registrar", empresaRegistro); //dirige al MedicoMapper
                conexionBD.commit(); //para plasmar los cambios que se hayan realizado, esto a nivel BD
                if(resultadoMapper>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Empresa registrada correctamente");
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
    public Respuesta modificar(@FormParam("idEmpresa") Integer idEmpresa,
                            @FormParam("nombre") String nombre,
                            @FormParam("nombreComercial") String nombreComercial,
                            @FormParam("nombreRepresentante") String nombreRepresentante,
                            @FormParam("email") String email,
                            @FormParam("calle") String calle,
                            @FormParam("numero") String numero,
                            @FormParam("codigoPostal") String codigoPostal,
                            @FormParam("ciudad") String ciudad,
                            @FormParam("telefono") String telefono,
                            @FormParam("paginaWeb") String paginaWeb,
                            @FormParam("idEstatus") Integer idEstatus){
        
        //metimos todos estos datos en este objeto porque el mapper solo acepta un dato, pero ya siendo 
        //un objeto, puedo sacar cualquier dato que necesite
        
        //LOS MANDO AL MAPPER
        Empresa empresaModificar = new Empresa();
        
        empresaModificar.setIdEmpresa(idEmpresa);
        empresaModificar.setNombre(nombre);
        empresaModificar.setNombreComercial(nombreComercial);
        empresaModificar.setNombreRepresentante(nombreRepresentante);
        empresaModificar.setEmail(email);
        empresaModificar.setCalle(calle);
        empresaModificar.setNumero(numero);
        empresaModificar.setCodigoPostal(codigoPostal);
        empresaModificar.setCiudad(ciudad);
        empresaModificar.setTelefono(telefono);
        empresaModificar.setPaginaWeb(paginaWeb);
        empresaModificar.setIdEstatus(idEstatus);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                int resultadoMapper = conexionBD.update("empresa.modificar", empresaModificar); //dirige al MedicoMapper
                conexionBD.commit(); //para plasmar los cambios que se hayan realizado, esto a nivel BD
                if(resultadoMapper>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Empresa modificada correctamente");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("No se pudo modificar la empresa correctamente");
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
    public Respuesta eliminar(@FormParam("idEmpresa") Integer idEmpresa){
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MybatisUtil.getSession();
        if(conexionBD !=null){
            try{
                int resultadoMapper = conexionBD.delete("empresa.eliminar", idEmpresa); //dirige al MedicoMapper
                conexionBD.commit(); //para plasmar los cambios que se hayan realizado, esto a nivel BD
                if(resultadoMapper>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Empresa eliminada correctamente");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("El identificador de la empresa enviada, no existe");
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
    public List<Empresa> buscarByID(@PathParam("datoBuscar") String datoBuscar){ //Aqui usamos PathParam porque recibimos el datos por la URL
        //Empresa empresaResultado = null;
        List<Empresa> empresaResultado = null;
        SqlSession conn = mybatis.MybatisUtil.getSession();
        if(conn !=null){
            try{
                empresaResultado = conn.selectList("empresa.buscar", datoBuscar);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return empresaResultado;  
    }
}
