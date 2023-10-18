/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;



/**
 *
 * @author Admin
 */
public abstract class DAO {
    //DATA ACCESS OBJECT
    protected Connection conexion = null;
    protected ResultSet resultado = null;
    protected Statement sentencia = null;
    //Se crea el usuario, password, database y e driver a usar
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "base1";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //Se llama un try catch para la conexion a la base de datos o que muestre error
    protected void conectarBase() throws ClassNotFoundException, SQLException{
        try {
            Class.forName(DRIVER);
            String urlBaseDeDatos = "jdbc:mysql://localhost:3307/"+DATABASE+"?useSSL=false";
            conexion = DriverManager.getConnection(urlBaseDeDatos, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }
    
    protected void desconectarBase() throws Exception{
        //Se corta la conexion a la base de datoa despues de finalizar todas las conexiones
        try{
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (conexion != null) {
                conexion.close();
            }
            
        } catch (Exception e) {
            throw e;
        }
        
    }
    //Este metodo va a insertar, modificar o eliminar algo en la base datos
    protected void insertarModificarEliminar(String sql) throws Exception {
        try {
            //Se llama al metodo conectar base para realizar la conexion a la base de datos
            //ya que anteriormente se desconectar despues de realizar cada consulta
            conectarBase();
            //Se le dice a sentencia mediante el metodo create statement se usa para pareparar para la crracion de la sentencia
            
            sentencia = conexion.createStatement();
            //Se le dice despues que ejecute la query con el execute update
            sentencia.executeUpdate(sql);
            //Se añade la exception sql y class not found con el |
        } catch (SQLException | ClassNotFoundException  ex) {
            //El rollback se utiliza para volver hacia atras y revertir las inserciones en caso de error, si algo no salio bien
            //se pone el commit en auto commit SET autocommit = 1; COMMIT;
            //conexion.rollback();
            throw ex;
        }finally{
            //Se desconecta de la base despues de ejecutar la consulta, independientemente si se hizo o no
            desconectarBase();
        }
        
    }
    protected void consultarBase(String sql) throws Exception{
        try {
            conectarBase();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        } catch (Exception ex) {
        throw ex;
        }
    }
}
