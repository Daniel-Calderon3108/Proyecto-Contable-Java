package modelVO;

public class EmpresaVO {
    //Se crean las variables
    public String IdEmpresa, Nombre, Nit, Logo, Direccion, FechaCreacion, Estado;

    //Se crea un primer constructor
    public EmpresaVO() {
    }
    //Se realiza sobrecarga de contructores
    public EmpresaVO(String IdEmpresa) {
        this.IdEmpresa = IdEmpresa;
    }

    public EmpresaVO(String Nombre, String Logo, String Direccion, String FechaCreacion, String Estado) {
        this.Nombre = Nombre;
        this.Logo = Logo;
        this.Direccion = Direccion;
        this.FechaCreacion = FechaCreacion;
        this.Estado = Estado;
    }

    public EmpresaVO(String IdEmpresa, String Nombre, String Nit, String Logo, String Direccion, String FechaCreacion, String Estado) {
        this.IdEmpresa = IdEmpresa;
        this.Nombre = Nombre;
        this.Nit = Nit;
        this.Logo = Logo;
        this.Direccion = Direccion;
        this.FechaCreacion = FechaCreacion;
        this.Estado = Estado;
    }
    //Se crean los getter y setter correspondientes a cada una de las variables creadas anteriormente
    public String getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(String IdEmpresa) {
        this.IdEmpresa = IdEmpresa;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getNit() {
        return Nit;
    }

    public void setNit(String Nit) {
        this.Nit = Nit;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String Logo) {
        this.Logo = Logo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
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
