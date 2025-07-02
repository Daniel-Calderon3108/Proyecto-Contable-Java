package modelVO;

public class UsuarioVO {
    //Se crean las variables
    public String idEmpresa,idUsuario,Nombre,Correo,Clave,Telefono,Direccion,
            FechaCreacion,Estado,idPerfil,Empresa,Perfil;
    //Se crea un primer constructor
    public UsuarioVO() {
    }
    //Se realiza sobrecarga de contructores
    public UsuarioVO(String idUsuario, String Nombre, String Correo, String Telefono, String Direccion, String FechaCreacion, String Estado, String Empresa, String Perfil) {
        this.idUsuario = idUsuario;
        this.Nombre = Nombre;
        this.Correo = Correo;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
        this.FechaCreacion = FechaCreacion;
        this.Estado = Estado;
        this.Empresa = Empresa;
        this.Perfil = Perfil;
    }

    public UsuarioVO(String idEmpresa, String idUsuario, String Nombre, String Correo, String Clave, String Telefono, String Direccion, String FechaCreacion, String Estado, String idPerfil, String Empresa, String Perfil) {
        this.idEmpresa = idEmpresa;
        this.idUsuario = idUsuario;
        this.Nombre = Nombre;
        this.Correo = Correo;
        this.Clave = Clave;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
        this.FechaCreacion = FechaCreacion;
        this.Estado = Estado;
        this.idPerfil = idPerfil;
        this.Empresa = Empresa;
        this.Perfil = Perfil;
    }
    //Se crean los getter y setter correspondientes a cada una de las variables creadas anteriormente
    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Clave;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
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

    public String getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(String idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String Empresa) {
        this.Empresa = Empresa;
    }

    public String getPerfil() {
        return Perfil;
    }

    public void setPerfil(String Perfil) {
        this.Perfil = Perfil;
    }
}
