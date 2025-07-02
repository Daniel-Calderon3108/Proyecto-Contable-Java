package modelVO;

public class InventarioVO {
    //Se crean las variables
    String IdInventario;
    String numerolote;
    String FechaCreacion;
    String Estado;
    String cantidad;
    String idProducto;
    String producto;
    //Se crea un primer constructor
    public InventarioVO() {
    }
    //Se realiza sobrecarga de contructores
    public InventarioVO(String IdInventario) {
        this.IdInventario = IdInventario;
    }

    public InventarioVO(String numerolote, String FechaCreacion, String Estado, String cantidad, String idProducto, String producto) {
        this.numerolote = numerolote;
        this.FechaCreacion = FechaCreacion;
        this.Estado = Estado;
        this.cantidad = cantidad;
        this.idProducto = idProducto;
        this.producto = producto;
    }

    public InventarioVO(String IdInventario, String numerolote, String FechaCreacion, String Estado, String cantidad, String idProducto, String producto) {
        this.IdInventario = IdInventario;
        this.numerolote = numerolote;
        this.FechaCreacion = FechaCreacion;
        this.Estado = Estado;
        this.cantidad = cantidad;
        this.idProducto = idProducto;
        this.producto = producto;
    }
    //Se crean los getter y setter correspondientes a cada una de las variables creadas anteriormente
    public String getIdInventario() {
        return IdInventario;
    }

    public void setIdInventario(String IdInventario) {
        this.IdInventario = IdInventario;
    }

    public String getNumerolote() {
        return numerolote;
    }

    public void setNumerolote(String numerolote) {
        this.numerolote = numerolote;
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

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
}
