package modelDAO;

import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import modelVO.VentaVO;

public class VentaDAO extends Conexion {

    public VentaDAO() {
    }

    private Connection conn = null;
    private PreparedStatement puente = null;
    private ResultSet mensajero = null;

    public boolean operaciones = false;
    public String sql;

    public String idVenta = "", numeroVenta = "", fechaCreacion = "", estado = "", total = "",
            observacion = "", idUsuario = "", idProducto = "", idDetalleVenta = "",
            producto = "", usuario = "";
    public int precioUnidad = 0, cantidad = 0, descuento = 0;

    public VentaDAO(VentaVO ventaVO) {
        super();

        try {
            conn = this.obtenerConexion();
            idVenta = ventaVO.getIdVenta();
            numeroVenta = ventaVO.getNumeroVenta();
            fechaCreacion = ventaVO.getFechaCreacion();
            estado = ventaVO.getEstado();
            total = ventaVO.getTotal();
            observacion = ventaVO.getObservacion();
            idUsuario = ventaVO.getIdUsuario();
            idProducto = ventaVO.getIdProducto();
            idDetalleVenta = ventaVO.getIdDetalleVenta();
            precioUnidad = ventaVO.getPrecioUnidad();
            cantidad = ventaVO.getCantidad();
            descuento = ventaVO.getDescuento();
            producto = ventaVO.getProducto();
            usuario = ventaVO.getUsuario();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Inicio metodo para listar las ventas
    public ArrayList<VentaVO> listar(String idEmpresa) {
        conn = this.obtenerConexion();
        ArrayList<VentaVO> listarVenta = new ArrayList<>();
        try {
            sql = "SELECT v.IdVenta,v.NumeroVenta,v.total,v.observacion,v.FechaCreacion,"
                    + "v.Estado,u.Nombre as usuario FROM venta v "
                    + "INNER JOIN usuario u USING (IdUsuario) WHERE v.Estado <> '2' AND u.idEmpresa = ?";
            puente = conn.prepareStatement(sql);
            puente.setString(1, idEmpresa);
            mensajero = puente.executeQuery();

            while (mensajero.next()) {
                String NumeroVenta = mensajero.getString("NumeroVenta");
                String FechaCreacion = mensajero.getString("FechaCreacion");
                String Estado = mensajero.getString("Estado");
                String Total = mensajero.getString("total");
                String Observacion = mensajero.getString("observacion");
                String IdVenta = mensajero.getString("IdVenta");
                String Usuario = mensajero.getString("usuario");

                listarVenta.add(new VentaVO(IdVenta, NumeroVenta,
                        FechaCreacion, Estado, Total,
                        Observacion, Usuario));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.cerrarConexion();
        }
        return listarVenta;
    }

    public ArrayList<VentaVO> detalleVenta(String id) {
        conn = this.obtenerConexion();
        ArrayList<VentaVO> listarDetalleVenta = new ArrayList<>();
        try {
            sql = "SELECT v.NumeroVenta,v.FechaCreacion,v.Estado,v.total,"
                    + "v.observacion,p.Nombre as producto,dv.preciounidad,dv.cantidad,"
                    + "dv.descuento,dv.IdVenta,u.Nombre as usuario,p.precio, p.idProducto "
                    + "FROM detalleventa dv "
                    + "INNER JOIN venta v USING(IdVenta) "
                    + "INNER JOIN producto p USING(IdProducto) "
                    + "INNER JOIN usuario u ON v.IdUsuario = u.IdUsuario WHERE idVenta = ?";
            puente = conn.prepareStatement(sql);
            puente.setString(1, id);
            mensajero = puente.executeQuery();
            while (mensajero.next()) {
                String NumeroVenta = mensajero.getString("NumeroVenta");
                String FechaCreacion = mensajero.getString("FechaCreacion");
                String Estado = mensajero.getString("Estado");
                String Total = mensajero.getString("total");
                String Observacion = mensajero.getString("observacion");
                String IdVenta = mensajero.getString("IdVenta");
                String Usuario = mensajero.getString("usuario");
                String producto = mensajero.getString("producto");
                int preciounidad = mensajero.getInt("preciounidad");
                int cantidad = mensajero.getInt("cantidad");
                int descuento = mensajero.getInt("descuento");
                int precio = mensajero.getInt("precio");
                String idProducto = mensajero.getString("idProducto");

                listarDetalleVenta.add(new VentaVO(IdVenta, NumeroVenta,
                        FechaCreacion, Estado, Total,
                        Observacion, preciounidad,
                        cantidad, descuento, producto, Usuario,precio,idProducto));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.cerrarConexion();
        }
        return listarDetalleVenta;
    }

    public static VentaVO infodetalleVenta(String id) {
        VentaVO ventaVO = null;
        Conexion conn = new Conexion();
        Connection conexion = conn.obtenerConexion();
        try {
            String sql = "SELECT v.IdVenta,v.NumeroVenta,v.total,v.observacion,v.FechaCreacion,"
                    + "v.Estado,u.Nombre as usuario FROM venta v "
                    + "INNER JOIN usuario u USING (IdUsuario) WHERE idVenta = ?";
            PreparedStatement puente = conexion.prepareStatement(sql);
            puente.setString(1, id);
            ResultSet mensajero = puente.executeQuery();
            while (mensajero.next()) {
                String NumeroVenta = mensajero.getString("NumeroVenta");
                String FechaCreacion = mensajero.getString("FechaCreacion");
                String Estado = mensajero.getString("Estado");
                String Total = mensajero.getString("total");
                String Observacion = mensajero.getString("observacion");
                String IdVenta = mensajero.getString("IdVenta");
                String Usuario = mensajero.getString("usuario");

                ventaVO = new VentaVO(IdVenta, NumeroVenta,
                        FechaCreacion, Estado, Total,
                        Observacion, Usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conn.cerrarConexion();
        }
        return ventaVO;
    }

    public String crearVenta(String regUsuario) {
        String numeroVenta = "";
        String idNuevaVenta = "";
        try {

            sql = "SELECT idVenta FROM venta ORDER BY 1 DESC LIMIT 1";
            puente = conn.prepareStatement(sql);
            mensajero = puente.executeQuery();

            while (mensajero.next()) {
                int idVenta = mensajero.getInt("idVenta");
                numeroVenta = "VEN-" + (idVenta + 1);
            }

            sql = "insert into venta (idUsuario,NumeroVenta,Estado) values (?,?,2)";
            puente = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            puente.setString(1, regUsuario);
            puente.setString(2, numeroVenta);
            puente.executeUpdate();

            mensajero = puente.getGeneratedKeys();

            if (mensajero.next()) {
                idNuevaVenta = String.valueOf(mensajero.getInt(1));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.cerrarConexion();
        }
        return idNuevaVenta;
    }

    public boolean registrarProductoVenta() {
        try {
            String id = "";
            sql = "SELECT idProducto FROM detalleventa WHERE idProducto = ? AND idVenta = ?";
            puente = conn.prepareStatement(sql);
            puente.setString(1, idProducto);
            puente.setString(2, idVenta);
            mensajero = puente.executeQuery();

            while (mensajero.next()) {
                id = mensajero.getString("idProducto");
            }

            if (id.equals("")) {
                sql = "insert into detalleventa (IdVenta,idProducto,cantidad,descuento) values(?,?,0,0)";
                puente = conn.prepareStatement(sql);
                puente.setString(1, idVenta);
                puente.setString(2, idProducto);
                puente.executeUpdate();
                operaciones = true;
            } else {
                operaciones = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.cerrarConexion();
        }
        return operaciones;
    }

    public boolean eliminarProductoVenta (){
        
        try {
            sql = "DELETE FROM detalleVenta WHERE idProducto = ? AND idVenta = ?";
            puente = conn.prepareStatement(sql);
            puente.setString(1, idProducto);
            puente.setString(2, idVenta);
            puente.executeUpdate();
            operaciones = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.cerrarConexion();
        }
        
        return operaciones;
    }
    
    public boolean actualizarVenta(){
        try {
            sql="update detalleVenta set preciounidad = ?, cantidad = ? WHERE idProducto = ? AND idVenta = ?";
            puente = conn.prepareStatement(sql);
            puente.setInt(1, precioUnidad);
            puente.setInt(2, cantidad);
            puente.setString(3, idProducto);
            puente.setString(4, idVenta);
            puente.executeUpdate();
            operaciones = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.cerrarConexion();
        }
        return operaciones;
    }
    
    public boolean guardarVenta(){
        try {
            sql="update venta set Estado = '1', total = ?, observacion = ? WHERE idVenta = ?";
            puente = conn.prepareStatement(sql);
            puente.setString(1, total);
            puente.setString(2, observacion);
            puente.setString(3, idVenta);
            puente.executeUpdate();
            operaciones = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.cerrarConexion();
        }
        return operaciones;
    }
    
}
