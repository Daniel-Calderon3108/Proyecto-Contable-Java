package modelDAO;
//Se importan las librerias y archivos que se requieren
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelVO.EmpresaVO;
import modelVO.PerfilVO;
//Se carga la clase conexion y la interfaz de CRUD para poder usarlas
public class EmpresaDAO extends Conexion {
    
    public EmpresaDAO(){
    
    }
    //Se inicializan las variables requeridas para realizar operaciones en la base de datos
    private Connection conn = null;
    private PreparedStatement puente = null;
    private ResultSet mensajero = null;
    
    public boolean operaciones = false;
    public String sql;
    public String IdEmpresa="", Nombre="",Nit="", Logo="", Direccion="", FechaCreacion="", Estado="";
    
    public EmpresaDAO(EmpresaVO empreVO, PerfilVO perVO){
        super();
        conn = this.obtenerConexion();
        IdEmpresa = empreVO.getIdEmpresa();
        Nombre    = empreVO.getNombre();
        Nit       = empreVO.getNit();
        Logo      = empreVO.getLogo();
        Direccion = empreVO.getDireccion();
        FechaCreacion = empreVO.getFechaCreacion();
        Estado    = empreVO.getEstado();
    }
    //Metodo que se crea para listar todas las empresas que estan en la bd
    public ArrayList<EmpresaVO> listar(){
        //Se obtiene la conexion a la bd
        conn = this.obtenerConexion();
        //se crea un arraylist donde se va a guardar la info de cada una de las empresas
        ArrayList<EmpresaVO> listarEmpresa = new ArrayList<>(); 
        
        try {
            //Se pre-crea la consulta que se va a realizar a la bd
            sql = "SELECT IdEmpresa, Nombre, Nit, Logo, Direccion, FechaCreacion, Estado "
                   + "FROM empresa ORDER BY Nombre asc";
            //Se prepara la consulta
            puente = conn.prepareStatement(sql);
            //Se ejecuta la consulta
            mensajero = puente.executeQuery();
            //Se hace un recorrido para obtener los resultados que arrojo la bd
            while(mensajero.next()){
                String idempresa = mensajero.getString("IdEmpresa");
                String nombre = mensajero.getString("Nombre");
                String nit = mensajero.getString("Nit");
                String logo = mensajero.getString("Logo");
                String direccion = mensajero.getString("Direccion");
                String fechacreacion = mensajero.getString("FechaCreacion");
                String estado = mensajero.getString("Estado");
                
                listarEmpresa.add( new EmpresaVO(idempresa,  nombre, nit, logo, 
                        direccion, fechacreacion, estado));
            }
                    } catch (SQLException ex) {
                        ex.printStackTrace(System.out);
        } finally {
            this.cerrarConexion();
        }
        return listarEmpresa;
    }   
}
