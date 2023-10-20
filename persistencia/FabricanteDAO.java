/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Fabricante;

/**
 *
 * @author Admin
 */
public final class FabricanteDAO extends DAO{
   public void guardarFabricante(Fabricante fabricante) throws Exception{
       try {
            //Se verifica si el usuario es nulo y lanza un error
            if(fabricante == null){
                throw new Exception("Debe ingresar un fabricante");
            }
             //Se lanza el insertar para mandar la orden, en este caso, el usuario
            //Se agrega comillas simples para el varchar de SQL
            String sql = "INSERT INTO Fabricante(codigo, nombre)"
                     +"VALUES(null, "+fabricante.getNombre()+");";
            insertarModificarEliminar(sql);

       } catch (Exception e) {
            throw e;
       }
   }
   public void modificarFabricante(Fabricante fabricante) throws Exception{
       try {
           //Se verifica si el usuario es nulo y lanza un error
           if(fabricante == null){
               throw new Exception("Debe seleccionar el fabricante que desea modificar");
           }
            //Se lanza el insertar para mandar la orden, en este caso, el usuario
            //Se agrega comillas simples para el varchar de SQL
            //Se lanza la consulta de actualizar
            String sql = "UPDATE Fabricante SET"+
                    "nombre="+fabricante.getNombre()+" WHERE codigo="+fabricante.getCodigo()+";";
            insertarModificarEliminar(sql);
       } catch (Exception e) {
       throw e;
       }
   }
   public void eliminarFabricante(Integer codigo) throws Exception{
       try {
                    //Eliminar usuario por codigo
             String sql = "DELETE FROM Fabricante WHERE codigo="+codigo+";";
             insertarModificarEliminar(sql);
       } catch (Exception e) {
           throw e;
       }
   } 
   public Fabricante buscarFabricantePorId(Integer codigo) throws Exception{
        try{
    String sql = "SELECT * FROM Fabricante WHERE codigo="+codigo+";";
     //Se llama al metodo consultar base para consulta la query
     consultarBase(sql);
     Fabricante fabricante =  null;
       while (resultado.next()) {
            //Para rellenar el objeto usuario se declara un bucle while indicando que si la variable
            //resultado capta algo con el metodo next()
            fabricante = new Fabricante();
            fabricante.setCodigo(resultado.getInt(1));
            fabricante.setNombre(resultado.getString(2));
              //Se le asigna el id con el metodo getInt 1 que seria la primera columna, con el metodo getInt o getString se 
                //coloca el numero de la columna empezando en 
           
       }
         //Se desconecta de la base despues de recibir el resultado y haber generado la consulta
            //despues de retorna el usuario
            desconectarBase();
            return fabricante;
       }catch(Exception e){
                //Si se encuentra algun error se desconecta de la base de datos
            desconectarBase();
            throw e;
       }
   }
   public Collection<Fabricante> listarFabricantes() throws Exception{
       try {
           String sql = "SELECT codigo, nombre FROM Fabricante;";
           consultarBase(sql);
           Fabricante fabricante = null;
           Collection<Fabricante> fabricantes = new ArrayList();
           while (resultado.next()) {
           fabricante = new Fabricante();
           fabricante.setCodigo(resultado.getInt(1));
           fabricante.setNombre(resultado.getString(2));
           //Se añaden al array list
           fabricantes.add(fabricante);
               
           }
           desconectarBase();
           return fabricantes;
       
       
       } catch (Exception e) {
       e.printStackTrace();
        desconectarBase();
        throw new Exception("Error de sistema");
       }
   }     
}
