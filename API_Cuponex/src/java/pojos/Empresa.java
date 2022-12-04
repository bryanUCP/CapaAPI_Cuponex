/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

/**
 *
 * @author DELL
 */
public class Empresa {
    private Integer idEmpresa;
    private String nombre;
    private String nombreComercial;
    private String nombreRepresentante;
    private String email;
    private String calle;
    private String numero;
    private String codigoPostal;
    private String ciudad;
    private String telefono;
    private String paginaWeb;
    private String RFC;
    private Integer idEstatus;
    
    private String datoBuscar;
    
    public Empresa(){
        
    }

    public Empresa(Integer idEmpresa, String nombre, String nombreComercial, String nombreRepresentante, String email, String calle, String numero, String codigoPostal, String ciudad, String telefono, String paginaWeb, String RFC, Integer idEstatus, String datoBuscar) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.nombreComercial = nombreComercial;
        this.nombreRepresentante = nombreRepresentante;
        this.email = email;
        this.calle = calle;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.paginaWeb = paginaWeb;
        this.RFC = RFC;
        this.idEstatus = idEstatus;
        
        this.datoBuscar = datoBuscar;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public String getDatoBuscar() {
        return datoBuscar;
    }

    public void setDatoBuscar(String datoBuscar) {
        this.datoBuscar = datoBuscar;
    }
    
    
    
}
