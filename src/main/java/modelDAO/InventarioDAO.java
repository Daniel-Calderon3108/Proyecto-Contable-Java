package modelDAO;
//Se importan las librerias y archivos que se requieren
import conexion.Conexion;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelVO.InventarioVO;
//Se carga la clase conexion y la interfaz de CRUD para poder usarlas
public class InventarioDAO extends Conexion implements CRUD {

    public InventarioDAO() {

    }
    //Se inicializan las variables requeridas para realizar operaciones en la base de datos
    private Connection conn = null;
    private PreparedStatement puente = null;
    private ResultSet mensajero = null;

    public boolean operaciones = false;
    public String sql;

    String IdInventario = "";
    String numerolote = "";
    String FechaCreacion = "";
    String Estado = "";
    String cantidad = "";
    String idProducto = "";
    String producto = "";

    public InventarioDAO(InventarioVO InventVO) {
        super();
        conn = this.obtenerConexion();
        IdInventario = InventVO.getIdInventario();
        numerolote = InventVO.getNumerolote();
        FechaCreacion = InventVO.getFechaCreacion();
        Estado = InventVO.getEstado();
        cantidad = InventVO.getCantidad();
        idProducto = InventVO.getIdProducto();
        producto = InventVO.getProducto();

    }

    //Inicio del metodo para listar el inventario
    public ArrayList<InventarioVO> listar() {
        conn = this.obtenerConexion();
        //se crea un arraylist donde se va a guardar la info de cada uno de los inventarios
        ArrayList<InventarioVO> listarInventario = new ArrayList<>();

        try {
            //Se pre-crea la consulta que se va a realizar a la bd
            sql = "SELECT IdInventario, numerolote, i.FechaCreacion, i.Estado, cantidad, nombre FROM inventario i INNER JOIN producto USING (idProducto);";
            //Se prepara la consulta
            puente = conn.prepareStatement(sql);
            //Se ejecuta la consulta
            mensajero = puente.executeQuery();
            //Se hace un recorrido para obtener los resultados que arrojo la bd
            while (mensajero.next()) {
                String idInventario = mensajero.getString("IdInventario");
                String numeroLote = mensajero.getString("numerolote");
                String fechaCreacion = mensajero.getString("FechaCreacion");
                String estado = mensajero.getString("Estado");
                String cantidad = mensajero.getString("cantidad");
                String Producto = mensajero.getString("Nombre");

                listarInventario.add(new InventarioVO(idInventario, numeroLote, fechaCreacion, estado, cantidad, "", Producto));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.cerrarConexion();
        }
        return listarInventario;
    }

    @Override
    //Metodo que se obtiene de la interfaz de CRUD
    //Metodo que se usa para poder insertar un nuevo inventario
    public boolean agregarRegistro() {
        try {
            //Se pre-crea la consulta que se va a realizar a la bd
            sql = "INSERT INTO inventario (numerolote, Estado, cantidad, idProducto) VALUES (?, ?, ?, ?)";
            //Se prepara la consulta
            puente = conn.prepareCall(sql);
            //Se reemplazan los valores de los interrogantes, por los valores recibidos
            puente.setString(1, numerolote);
            puente.setString(2, Estado);
            puente.setString(3, cantidad);
            puente.setString(4, idProducto);
            //Se ejecuta la consulta
            puente.executeUpdate();

            operaciones = true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.cerrarConexion();
        }
        return operaciones;
    }

    @Override
    //Metodo que se obtiene de la interfaz de CRUD
    //Metodo que se encarga de actualizar los datos de un inventario
    public boolean actualizarRegistro() {
        try {
            //Se pre-crea la consulta que se va a realizar a la bd
            sql = "UPDATE inventario SET numerolote = ?, Estado = ?, cantidad = ?, idProducto = ? WHERE IdInventario = ? ";
            //Se prepara la consulta
            puente = conn.prepareStatement(sql);
            //Se reemplazan los valores de los interrogantes, por los valores recibidos
            puente.setString(1, numerolote);
            puente.setString(2, Estado);
            puente.setString(3, cantidad);
            puente.setString(4, idProducto);
            puente.setString(5, IdInventario);
            //Se ejecuta la consulta
            puente.executeUpdate();
            
            operaciones = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.cerrarConexion();
        }
        return operaciones;
    }
    //Metodo que se usa para consultar un inventario por el id
    public static InventarioVO consultarId(String Id) {
        //Al ser un metodo estatico no podemos usar las variables globales
        InventarioVO inventVO = null;
        Conexion conex = new Conexion();
        Connection conexion = conex.obtenerConexion();

        try {
            //Se pre-crea la consulta que se va a realizar a la bd
            String sql = "SELECT IdInventario, numerolote, i.FechaCreacion, i.Estado, cantidad, nombre, i.idProducto FROM inventario i "
                         + "INNER JOIN producto USING (idProducto) where IdInventario=?";
            //Se prepara la consulta
            PreparedStatement puente = conexion.prepareStatement(sql);
            //Se reemplazan los valores de los interrogantes, por el valor recibido
            puente.setString(1, Id);
            //Se ejecuta la consulta
            ResultSet mensajero = puente.executeQuery();
            //Se hace un recorrido para obtener los resultados que arrojo la bd
            while (mensajero.next()) {
                String idInventario = mensajero.getString("IdInventario");
                String numerolote = mensajero.getString("numerolote");
                String FechaCreacion = mensajero.getString("FechaCreacion");
                String estado = mensajero.getString("Estado");
                String cantidad = mensajero.getString("cantidad");
                String producto = mensajero.getString("nombre");
                String idProducto = mensajero.getString("idProducto");

                inventVO = new InventarioVO(idInventario, numerolote, FechaCreacion, estado, cantidad, idProducto, producto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conex.cerrarConexion();
        }
        return inventVO;
    }
}
