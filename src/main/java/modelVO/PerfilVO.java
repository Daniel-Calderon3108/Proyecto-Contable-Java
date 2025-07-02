package modelVO;

public class PerfilVO {
    //Se crean las variables
    public String IdPerfil, Nombre, FechaCreacion, Estado;
    //Se crea un primer constructor
    public PerfilVO() {
    }
    //Se realiza sobrecarga de contructores
    public PerfilVO(String IdPerfil) {
        this.IdPerfil = IdPerfil;
    }

    public PerfilVO(String Nombre, String FechaCreacion, String Estado) {
        this.Nombre = Nombre;
        this.FechaCreacion = FechaCreacion;
        this.Estado = Estado;
    }

    public PerfilVO(String IdPerfil, String Nombre, String FechaCreacion, String Estado) {
        this.IdPerfil = IdPerfil;
        this.Nombre = Nombre;
        this.FechaCreacion = FechaCreacion;
        this.Estado = Estado;
    }
    //Se crean los getter y setter correspondientes a cada una de las variables creadas anteriormente
    public String getIdPerfil() {
        return IdPerfil;
    }

    public void setIdPerfil(String IdPerfil) {
        this.IdPerfil = IdPerfil;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    } 
}
