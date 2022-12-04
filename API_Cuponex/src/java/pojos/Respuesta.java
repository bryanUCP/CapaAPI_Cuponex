
package pojos;

public class Respuesta {
    
    private Boolean error;
    private String mensaje;

    public Respuesta() {
    }

    public Respuesta(Boolean error, String mensaje) {
        this.error = error;
        this.mensaje = mensaje;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
}
