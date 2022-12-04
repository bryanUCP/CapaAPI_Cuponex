
package ws;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.apache.ibatis.session.SqlSession;
import pojos.Catalogo;


@Path("catalogos")
public class CatalogoWS {
    
    @Context
    private UriInfo context;
    
    /*http://localhost:8084/API_Fit_Nutrition/ws/catalogos/bycategoria/3
    @Path("bycategoria/{idCategoria}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Catalogo> getCatalogoId(@PathParam("idCategoria") Integer idCategoria){
        List<Catalogo> catalogos = null;
        SqlSession conn = mybatis.MybatisUtil.getSession();
        if(conn !=null){
            try{
                catalogos = conn.selectList("catalogo.bycategoria", idCategoria);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return catalogos;
    }*/
    
    //http://localhost:8084/API_Cuponex/ws/catalogos/bycategoria
    @Path("bycategoria")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Catalogo> getCatalogoId(){
        List<Catalogo> catalogos = null;
        SqlSession conn = mybatis.MybatisUtil.getSession();
        if(conn !=null){
            try{
                catalogos = conn.selectList("catalogo.bycategoria");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        return catalogos;
    }
    
}
