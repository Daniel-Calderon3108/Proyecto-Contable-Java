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
//Se carga la clase conexion  para poder usarla
public class PerfilDAO extends Conexion {
    
    public PerfilDAO(){
        
    }
    //Se inicializan las variables requeridas para realizar operaciones en la base de datos
    private Connection conn = null;
    private PreparedStatement puente = null;
    private ResultSet mensajero = null;
    public boolean operaciones = false;
    public String sql;
    
    public String IdPerfil="", Nombre="", FechaCreacion="", Estado="";
    
    public PerfilDAO(PerfilVO perfilVO, EmpresaVO empreVO){
        super();
        
        conn = this.obtenerConexion();
        IdPerfil = perfilVO.getIdPerfil();
        Nombre = perfilVO.getNombre();
        FechaCreacion = perfilVO.getFechaCreacion();
        Estado =perfilVO.getEstado();
    }
    //Metodo para listar los perfiles de la bd, resibe el id del perfil
    public ArrayList<PerfilVO> listar(String perfil){
        PerfilVO perfilVO = null;
        conn = this.obtenerConexion();
        //Se inicializa una variable condicion
        String condition = "";
        //Si el perfil enviado es diferente de vacio, se traen todos los perfiles, excepto el perfil recibido
        if (!"".equals(perfil)) {
            condition = " WHERE IdPerfil <> "+perfil;
        }
        //se crea un arraylist donde se va a guardar la info de cada uno de los perfiles
        ArrayList<PerfilVO> listarperfil = new ArrayList<>();
        
        try {
             //Se pre-crea la consulta que se va a realizar a la bd, se agrega la variable condicion
            sql = "SELECT IdPerfil, Nombre, FechaCreacion, Estado FROM perfil"+
                    condition + " ORDER BY Nombre asc";
            //Se prepara la consulta
            puente = conn.prepareStatement(sql);
            //Se ejecuta la consulta
            mensajero = puente.executeQuery();
            //Se hace un recorrido para obtener los resultados que arrojo la bd
            while(mensajero.next()){
                String idperfil = mensajero.getString("IdPerfil");
                String nombre = mensajero.getString("Nombre");
                String fechacreacion = mensajero.getString("FechaCreacion");
                String estado = mensajero.getString("Estado");
                
                listarperfil.add(new PerfilVO (idperfil, nombre, fechacreacion, estado));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return listarperfil;
    }
}
