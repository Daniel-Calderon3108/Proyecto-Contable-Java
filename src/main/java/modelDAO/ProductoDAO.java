package modelDAO;

import conexion.Conexion;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelVO.ProductoVO;

public class ProductoDAO extends Conexion implements CRUD {

    public ProductoDAO() {
    }

    private Connection conn = null;
    private PreparedStatement puente = null;
    private ResultSet mensajero = null;

    public boolean operaciones = false;
    public String sql;

    String IdProducto = "";
    String Nombre = "";
    String precio = "";
    String imagen = "";
    String descripcion = "";
    String FechaCreacion = "";
    String Estado = "";

    public ProductoDAO(ProductoVO producVO) {
        super();
        conn = this.obtenerConexion();
        IdProducto = producVO.getIdProducto();
        Nombre = producVO.getNombre();
        precio = producVO.getPrecio();
        imagen = producVO.getImagen();
        descripcion = producVO.getDescripcion();
        FechaCreacion = producVO.getFechaCreacion();
        Estado = producVO.getEstado();
    }

    //Inicio del metodo para listar pruducto
    public ArrayList<ProductoVO> listar() {
        conn = this.obtenerConexion();
        ArrayList<ProductoVO> listarProducto = new ArrayList<>();

        try {
            sql = "SELECT IdProducto, Nombre, precio, imagen, descripcion, FechaCreacion, Estado FROM producto";

            puente = conn.prepareStatement(sql);
            mensajero = puente.executeQuery();
            while (mensajero.next()) {
                String idProducto = mensajero.getString("IdProducto");
                String nombre = mensajero.getString("Nombre");
                String precio = mensajero.getString("precio");
                String imagen = mensajero.getString("imagen");
                String descripcion = mensajero.getString("descripcion");
                String fechaCreacion = mensajero.getString("FechaCreacion");
                String estado = mensajero.getString("Estado");

                listarProducto.add(new ProductoVO(idProducto, nombre, precio, imagen,
                        descripcion, fechaCreacion, estado));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.cerrarConexion();
        }
        return listarProducto;
    }

    @Override
    public boolean agregarRegistro() {
        try {
            sql = "insert into producto (Nombre, precio, imagen, descripcion, Estado) "
              + "values (?, ?, ?, ?, ?)";
            
            puente = conn.prepareStatement(sql);
            puente.setString(1, Nombre);
            puente.setString(2, precio);
            puente.setString(3, imagen);
            puente.setString(4, descripcion);
            puente.setString(5, Estado);
            puente.executeUpdate();
            
            operaciones = true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.cerrarConexion();
        }
        return operaciones;
    }

    @Override
    public boolean actualizarRegistro() {
        
        String condicion = "";
        
        if (!imagen.equals("")) {
           condicion = ",imagen='"+imagen+"'";
        }
        
        try {
            sql =" update producto set Nombre=?, precio=?"+ condicion + ", descripcion=?, "
                + "Estado=? where idProducto = ?";
            
            puente = conn.prepareStatement(sql);
            
            puente.setString(1, Nombre);
            puente.setString(2, precio);
            puente.setString(3, descripcion);
            puente.setString(4, Estado);
            puente.setString(5, IdProducto);
            
            puente.executeUpdate();
            operaciones = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return operaciones;
    }
    
    public static ProductoVO consultarId(String Id){
        ProductoVO producVO = null;
        Conexion conex = new Conexion();
        Connection conexion = conex.obtenerConexion();
         
        try {
            String sql = "SELECT p.IdProducto, p.Nombre, p.precio, p.imagen, p.descripcion, "
                + "p.FechaCreacion, p.Estado FROM producto p WHERE p.IdProducto = ? ";
            
            PreparedStatement puente = conexion.prepareStatement(sql);
            puente.setString(1, Id);
            ResultSet mensajero = puente.executeQuery();
            
            while(mensajero.next()){
                String idproducto = mensajero.getString("IdProducto");
                String nombre = mensajero.getString("Nombre");
                String Precio = mensajero.getString("precio");
                String Imagen = mensajero.getString("imagen");
                String Descripcion = mensajero.getString("descripcion");
                String fechacreacion = mensajero.getString("FechaCreacion");
                String estado = mensajero.getString("Estado");
                
                producVO = new ProductoVO(idproducto, nombre, Precio, Imagen, Descripcion, fechacreacion, estado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            conex.cerrarConexion();
        }
        return producVO;
    }
}
