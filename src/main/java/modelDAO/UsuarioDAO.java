package modelDAO;

import conexion.Conexion;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelVO.UsuarioVO;

public class UsuarioDAO extends Conexion implements CRUD {
    public UsuarioDAO() {
    }
    
    private Connection conn = null;
    private PreparedStatement puente = null;
    private ResultSet mensajero = null;
    
    public boolean operaciones = false;
    public String sql;
    
    public String idEmpresa="",idUsuario="",Nombre="",Correo="",Clave="",
            Telefono="",Direccion="",FechaCreacion="",Estado="",idPerfil="",
            Empresa="",Perfil="";
    
    public UsuarioDAO (UsuarioVO usuVO) {
        super();
        
        try {
            conn = this.obtenerConexion();
            idEmpresa      = usuVO.getIdEmpresa();
            idUsuario      = usuVO.getIdUsuario();
            Nombre         = usuVO.getNombre();
            Correo         = usuVO.getCorreo();
            Clave          = usuVO.getClave();
            Telefono       = usuVO.getTelefono();
            Direccion      = usuVO.getDireccion();
            FechaCreacion  = usuVO.getFechaCreacion();
            Estado         = usuVO.getEstado();
            idPerfil       = usuVO.getIdPerfil();
            Empresa        = usuVO.getEmpresa();
            Perfil         = usuVO.getIdPerfil();
        } catch (Exception e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE,null,e);
        }        
    }
    
    public boolean IniciarSesion(String usuario,String clave){
        try {
            conn = this.obtenerConexion();
            sql ="select Nombre,clave from usuario where Nombre=? and clave=? and Estado = 1";
            puente = conn.prepareStatement(sql);
            puente.setString(1, usuario);
            puente.setString(2, clave);
            mensajero = puente.executeQuery();
            if(mensajero.next()){
                operaciones = true;
            }
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE,null,e);
        }finally{
            this.cerrarConexion();
        }
        return operaciones;
    }
    
    //Inicio del metodo para listar usuarios
    public ArrayList<UsuarioVO> listar(String idEmpresa) {

    conn = this.obtenerConexion();
    ArrayList<UsuarioVO> listarUsuario = new ArrayList<>();  
    try {
        sql = "SELECT u.IdUsuario, u.Nombre, u.correo, u.clave, u.telefono, u.direccion, "
            + "u.FechaCreacion, u.Estado, e.Nombre as empresa, p.Nombre as perfil FROM usuario u "
            + "inner join empresa e  USING(IdEmpresa) inner JOIN perfil p USING (IdPerfil) "
            + "WHERE idEmpresa = ? ORDER BY Nombre asc";
        
        puente = conn.prepareStatement(sql);
        puente.setString(1, idEmpresa);
        mensajero = puente.executeQuery();
        while(mensajero.next()){
            String idusuario = mensajero.getString("IdUsuario");
            String nombre = mensajero.getString("Nombre");
            String correo = mensajero.getString("correo");
            String telefono = mensajero.getString("telefono");
            String direccion = mensajero.getString("direccion");
            String fechacreacion = mensajero.getString("FechaCreacion");
            String estado = mensajero.getString("Estado");
            String empresa = mensajero.getString("empresa");
            String perfil = mensajero.getString("perfil");
            
            listarUsuario.add( new UsuarioVO(idusuario, 
                    nombre, correo,telefono, direccion,
                    fechacreacion, estado, empresa, perfil));
          }
        
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            this.cerrarConexion();
        }
        return listarUsuario;
    }
    //fin del metodo de listar usuarios

    // Metodo para consultar un usuario por medio del nombre
    public static UsuarioVO consultarUsuario(String usuario) {
        UsuarioVO usuarioVO = null;
        
        Conexion conexion = new Conexion();
        Connection conn = conexion.obtenerConexion();
        try {
             String sql = "SELECT u.IdUsuario, u.Nombre, u.correo, u.clave, u.telefono, u.direccion, "
            + "u.FechaCreacion, u.Estado, e.Nombre as empresa, p.Nombre as perfil, u.IdEmpresa, "
            + "u.idPerfil FROM usuario u "
            + "inner join empresa e  USING(IdEmpresa) inner JOIN perfil p USING (IdPerfil)"
                + " WHERE u.Nombre = ?";
            PreparedStatement puente = conn.prepareStatement(sql);
            puente.setString(1, usuario);
            ResultSet mensajero = puente.executeQuery();
            
            while (mensajero.next()) {
                String idusuario = mensajero.getString("IdUsuario");
                String nombre = mensajero.getString("Nombre");
                String correo = mensajero.getString("correo");
                String telefono = mensajero.getString("telefono");
                String direccion = mensajero.getString("direccion");
                String fechacreacion = mensajero.getString("FechaCreacion");
                String estado = mensajero.getString("Estado");
                String empresa = mensajero.getString("empresa");
                String perfil = mensajero.getString("perfil");
                String idEmpresa = mensajero.getString("IdEmpresa");
                String idPerfil = mensajero.getString("IdPerfil");
                
                usuarioVO = new UsuarioVO(idEmpresa, idusuario, nombre,
                        correo, "", telefono,
                        direccion, fechacreacion, estado,
                        idPerfil,empresa, perfil);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);        
        }finally{
            conexion.cerrarConexion();
        }
        return usuarioVO;
    }
    // fin del metodo para consultar usuario
    
    @Override
    public boolean agregarRegistro() {
        try {
            sql = "insert into usuario (Nombre,correo,clave,telefono,direccion,Estado,IdEmpresa,IdPerfil)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";
            puente = conn.prepareStatement(sql);
            puente.setString(1, Nombre);
            puente.setString(2, Correo);
            puente.setString(3, Clave);
            puente.setString(4, Telefono);
            puente.setString(5, Direccion);
            puente.setString(6, Estado);
            puente.setString(7, idEmpresa);
            puente.setString(8, idPerfil);
            puente.executeUpdate();
            operaciones = true;
            
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            this.cerrarConexion();
        }
        return operaciones;
    }

    @Override
    public boolean actualizarRegistro() {
        try {
            sql = "update usuario set Nombre=? ,correo=? ,clave=? ,telefono=? ,direccion=? "
                    + ",Estado=?,IdEmpresa=?,IdPerfil=? where IdUsuario = ?";

            puente = conn.prepareStatement(sql);

            puente.setString(1, Nombre);
            puente.setString(2, Correo);
            puente.setString(3, Clave);
            puente.setString(4, Telefono);
            puente.setString(5, Direccion);
            puente.setString(6, Estado);
            puente.setString(7, idEmpresa);
            puente.setString(8, idPerfil);
            puente.setString(9,idUsuario);
            puente.executeUpdate();
            operaciones = true;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            this.cerrarConexion();
        }
        return operaciones;
    }
    
    public static UsuarioVO consultarId(String Id) {
        UsuarioVO userVO = null;
        Conexion conex = new Conexion();
        Connection conexion = conex.obtenerConexion();
        try {
            String sql = "SELECT u.IdUsuario, u.Nombre, u.correo, u.clave, u.telefono, u.direccion, "
                    + "u.FechaCreacion, u.Estado, e.Nombre as empresa, p.Nombre as perfil, u.IdEmpresa, u.IdPerfil "
                    + "FROM usuario u "
                    + "inner join empresa e  USING(IdEmpresa) inner JOIN perfil p USING (IdPerfil)"
                    + " WHERE u.IdUsuario = ?";
            PreparedStatement puente = conexion.prepareStatement(sql);
            puente.setString(1, Id);
            ResultSet mensajero = puente.executeQuery();
            while (mensajero.next()) {
                String idusuario = mensajero.getString("IdUsuario");
                String nombre = mensajero.getString("Nombre");
                String correo = mensajero.getString("correo");
                String telefono = mensajero.getString("telefono");
                String direccion = mensajero.getString("direccion");
                String fechacreacion = mensajero.getString("FechaCreacion");
                String estado = mensajero.getString("Estado");
                String empresa = mensajero.getString("empresa");
                String perfil = mensajero.getString("perfil");
                String idEmpresa = mensajero.getString("IdEmpresa");
                String idPerfil = mensajero.getString("IdPerfil");
                String clave = mensajero.getString("clave");
                
                userVO = new UsuarioVO(idEmpresa, idusuario, nombre,
                        correo, clave, telefono,
                        direccion, fechacreacion, estado,
                        idPerfil, empresa, perfil);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conex.cerrarConexion();
        }
        return userVO;
    }
}
