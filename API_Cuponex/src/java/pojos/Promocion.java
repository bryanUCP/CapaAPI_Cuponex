
package pojos;


/**
 *
 * @author DELL
 */
public class Promocion {
    
    private Integer idPromocion;
    private String nombre;
    private String foto;
    private String descripcion;
    private String fechaInicio;
    private String fechaFinal;
    private String restriccion;
    private Integer idTipoPromocion;
    private String porcentajeDescuento;
    private String costoPromocion;
    private Integer idCategoria;
    private Integer idEstatus;
    private Integer idEmpresa;
    
    private String nombreTipoPromocion;
    private String nombreCategoria;
    private String nombreEstatus;
    private String nombreEmpresa;

    private String datoBuscar;

public Promocion(){
    
}

    public Promocion(Integer idPromocion, String nombre, String foto, String descripcion, String fechaInicio, String fechaFinal, String restriccion, Integer idTipoPromocion, String porcentajeDescuento, String costoPromocion, Integer idCategoria, Integer idEstatus, Integer idEmpresa, String nombreTipoPromocion ,String nombreCategoria, String nombreEstatus, String datoBuscar, String nombreEmpresa) {
        this.idPromocion = idPromocion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.restriccion = restriccion;
        this.idTipoPromocion = idTipoPromocion;
        this.porcentajeDescuento = porcentajeDescuento;
        this.costoPromocion = costoPromocion;
        this.idCategoria = idCategoria;
        this.idEstatus = idEstatus;
        this.idEmpresa = idEmpresa;
        
        this.nombreTipoPromocion=nombreTipoPromocion;
        this.nombreCategoria = nombreCategoria;
        this.nombreEstatus = nombreEstatus;
        this.nombreEmpresa = nombreEmpresa;
        
        this.datoBuscar = datoBuscar;
    }

    public Integer getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Integer idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getRestriccion() {
        return restriccion;
    }

    public void setRestriccion(String restriccion) {
        this.restriccion = restriccion;
    }

    public Integer getIdTipoPromocion() {
        return idTipoPromocion;
    }

    public void setIdTipoPromocion(Integer idTipoPromocion) {
        this.idTipoPromocion = idTipoPromocion;
    }

    public String getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(String porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getCostoPromocion() {
        return costoPromocion;
    }

    public void setCostoPromocion(String costoPromocion) {
        this.costoPromocion = costoPromocion;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    
    

    public String getNombreTipoPromocion() {
        return nombreTipoPromocion;
    }

    public void setNombreTipoPromocion(String nombreTipoPromocion) {
        this.nombreTipoPromocion = nombreTipoPromocion;
    }
    
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreEstatus() {
        return nombreEstatus;
    }

    public void setNombreEstatus(String nombreEstatus) {
        this.nombreEstatus = nombreEstatus;
    }

    public String getDatoBuscar() {
        return datoBuscar;
    }

    public void setDatoBuscar(String datoBuscar) {
        this.datoBuscar = datoBuscar;
    }
    
}
