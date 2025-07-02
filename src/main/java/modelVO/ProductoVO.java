package modelVO;
public class ProductoVO {
    //Se crean las variables
    String IdProducto;
    String Nombre;
    String precio;
    String imagen;
    String descripcion;
    String FechaCreacion;
    String Estado;
    //Se crea un primer constructor
    public ProductoVO() {
    }
    //Se realiza sobrecarga de contructores
    public ProductoVO(String IdProducto) {
        this.IdProducto = IdProducto;
    }

    public ProductoVO(String Nombre, String precio, String imagen, String descripcion, String FechaCreacion, String Estado) {
        this.Nombre = Nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.FechaCreacion = FechaCreacion;
        this.Estado = Estado;
    }

    public ProductoVO(String IdProducto, String Nombre, String precio, String imagen, String descripcion, String FechaCreacion, String Estado) {
        this.IdProducto = IdProducto;
        this.Nombre = Nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.FechaCreacion = FechaCreacion;
        this.Estado = Estado;
    }
    //Se crean los getter y setter correspondientes a cada una de las variables creadas anteriormente
    public String getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(String IdProducto) {
        this.IdProducto = IdProducto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
