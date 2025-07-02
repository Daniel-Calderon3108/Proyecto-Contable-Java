package modelVO;

public class VentaVO {
    //Se crean las variables
    String idVenta,numeroVenta,fechaCreacion,estado,total,observacion,idUsuario,
            idProducto, idDetalleVenta, producto,
            usuario;
    int precioUnidad, cantidad, descuento,precio;
    //Se crea un primer constructor
    public VentaVO() {
    }
    //Se realiza sobrecarga de contructores
    public VentaVO(String idVenta, String numeroVenta, String fechaCreacion, 
            String estado, String total, String observacion, int precioUnidad, 
            int cantidad, int descuento, String producto, String usuario,int precio,
            String idProducto) {
        this.idVenta = idVenta;
        this.numeroVenta = numeroVenta;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.total = total;
        this.observacion = observacion;
        this.precioUnidad = precioUnidad;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.producto = producto;
        this.usuario = usuario;
        this.precio = precio;
        this.idProducto = idProducto;
    }

    public VentaVO(String idVenta, String numeroVenta, String fechaCreacion, String estado, String total, String observacion, String usuario) {
        this.idVenta = idVenta;
        this.numeroVenta = numeroVenta;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.total = total;
        this.observacion = observacion;
        this.usuario = usuario;
    }

    public VentaVO(String idVenta, String numeroVenta, String fechaCreacion, 
            String estado, String total, String observacion, String idUsuario, 
            String idProducto, String idDetalleVenta, int precioUnidad, 
            int cantidad, int descuento, String producto, String usuario) {
        this.idVenta = idVenta;
        this.numeroVenta = numeroVenta;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.total = total;
        this.observacion = observacion;
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.idDetalleVenta = idDetalleVenta;
        this.precioUnidad = precioUnidad;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.producto = producto;
        this.usuario = usuario;
    }
    //Se crean los getter y setter correspondientes a cada una de las variables creadas anteriormente
    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public String getNumeroVenta() {
        return numeroVenta;
    }

    public void setNumeroVenta(String numeroVenta) {
        this.numeroVenta = numeroVenta;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(String idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public int getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(int precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    } 
}
