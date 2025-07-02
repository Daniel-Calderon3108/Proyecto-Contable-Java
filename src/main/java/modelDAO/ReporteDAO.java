package modelDAO;

import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import modelVO.VentaVO;

public class ReporteDAO extends Conexion {

    public ReporteDAO() {
    }
    
    private Connection conn = null;
    private PreparedStatement puente = null;
    private ResultSet mensajero = null;
    
    public String sql;
    
   public String idUsuario="",idVenta="",idProducto="",producto="",usuario="",
           numeroVenta="",fechaCreacion="",estado="",total="",observacion="",
           precioUnidad="",cantidad="",descuento="";
   
   public ReporteDAO(VentaVO ventaVO) {
       super();
       conn = this.obtenerConexion();
       idUsuario = ventaVO.getIdUsuario();
       idVenta = ventaVO.getIdVenta();
       idProducto = ventaVO.getIdProducto();
       producto = ventaVO.getProducto();
       usuario = ventaVO.getUsuario();
       numeroVenta = ventaVO.getNumeroVenta();
       fechaCreacion = ventaVO.getFechaCreacion();
       estado = ventaVO.getEstado();
       total = ventaVO.getTotal();
       observacion = ventaVO.getObservacion();
   }
   
   public ArrayList<VentaVO> listar (String idUsuario, String idEmpresa,
           String desde, String hasta, String idProducto) {
       conn = this.obtenerConexion();
       ArrayList<VentaVO> listarReporte = new ArrayList<>();
       
       try {
           
           String condicion = "";
           String fechaDesde = desde + " 00:00:00";
           String fechaHasta = hasta + " 23:59:59";
           
           
           if (!idUsuario.equals("")) {
               condicion += " AND v.idUsuario ="+idUsuario;
           }
           
           if (!idProducto.equals("")) {
               condicion += " AND dv.idProducto ="+idProducto;
           }
           
           
           sql = "SELECT DISTINCT v.idVenta,u.nombre as usuario,"
                 + "v.numeroVenta,v.fechaCreacion,v.estado,v.total,v.observacion "
                 + "FROM venta v INNER JOIN usuario u USING(idUsuario) "
                 + "INNER JOIN detalleventa dv USING(idVenta) "
                 + "INNER JOIN producto p ON dv.idProducto = p.idProducto "
                 + "WHERE u.idEmpresa = ? AND v.fechaCreacion BETWEEN ? AND ?"+condicion;
           puente = conn.prepareStatement(sql);
           puente.setString(1, idEmpresa);
           puente.setString(2, fechaDesde);
           puente.setString(3, fechaHasta);
           mensajero = puente.executeQuery();
           
           while (mensajero.next()) {
               String idVenta = mensajero.getString("idVenta");
               String usuario = mensajero.getString("usuario");
               String numeroVenta = mensajero.getString("numeroVenta");
               String fechaCreacion = mensajero.getString("fechaCreacion");
               String estado = mensajero.getString("estado");
               String total = mensajero.getString("total");
               String observacion = mensajero.getString("observacion");
               
               listarReporte.add(new VentaVO(idVenta, numeroVenta, fechaCreacion, 
                       estado, total, observacion, usuario));
           }
           
       } catch (SQLException ex) {
           ex.printStackTrace();
       } finally {
           this.cerrarConexion();
       }
       return listarReporte;
   }
}
