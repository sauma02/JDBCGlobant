/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.persistencia;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Admin
 */
public abstract class DAO {
    
    protected Connection conexion = null;
    protected Statement sentencia = null;
    protected ResultSet resultado = null;
        //Se crea el usuario, password, database y e driver a usar
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "tienda";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
        //Se llama un try catch para la conexion a la base de datos o que muestre error

    
    protected void conectarBase() throws ClassNotFoundException, SQLException{
        try {
                        //Para utilizar el driver se utiliza la clase Class con el metodo forName

            Class.forName(DRIVER);
            String urDB = "jdbc:mysql://localhost:3307/"+DATABASE+"?useSSL=false";
            //Se usa la clase Driver manager con el metodo get connection
            conexion = DriverManager.getConnection(urDB, USER, PASSWORD);
            
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }
    protected void desconectarBase() throws Exception{
                //Se corta la conexion a la base de datoa despues de finalizar todas las conexiones
        try {
            if (resultado != null) {
                //Se usa el metodo close para finalizar la conexion
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (resultado != null) {
                resultado.close();
                
            }
        
        
        
        } catch (Exception e) {
        throw e;
        }
    }
    protected void insertarModificarEliminar(String sql) throws Exception{
        try {
         //Se llama al metodo conectar base para realizar la conexion a la base de datos
         //ya que anteriormente se desconectar despues de realizar cada consulta    
        conectarBase();
         //Se le dice a sentencia mediante el metodo create statement se usa para pareparar para la crracion de la sentencia
         sentencia = conexion.createStatement();
         //Se le dice despues que ejecute la query con el execute update
         sentencia.executeUpdate(sql);

        
        } catch (ClassNotFoundException | SQLException e) {
            //El rollback se utiliza para volver hacia atras y revertir las inserciones en caso de error, si algo no salio bien
            //se pone el commit en auto commit SET autocommit = 1; COMMIT;
            //conexion.rollback();
        throw e;
        
        }finally{
            desconectarBase();
        }
    }
    protected void consultarBase(String sql) throws Exception{
        try {
            conectarBase();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        
        } catch (Exception e) {
        throw e;
        }
    }
}
