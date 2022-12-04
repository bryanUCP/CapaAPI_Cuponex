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
public class Catalogo {
    
    private Integer IdCatalogo;
    private String nombre;
    private Integer IdCategoria;
    
    public Catalogo(){
        
    }

    public Catalogo(Integer IdCatalogo, String nombre, Integer IdCategoria) {
        this.IdCatalogo = IdCatalogo;
        this.nombre = nombre;
        this.IdCategoria = IdCategoria;
    }

    public Integer getIdCatalogo() {
        return IdCatalogo;
    }

    public void setIdCatalogo(Integer IdCatalogo) {
        this.IdCatalogo = IdCatalogo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Integer getIdCategoria() {
        return IdCategoria;
    }

    public void setIdcategoria(Integer IdCategoria) {
        this.IdCategoria= IdCategoria;
    }
    
    
    
}
