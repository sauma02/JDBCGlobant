/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Fabricante;
import tienda.entidades.Producto;

/**
 *
 * @author Admin
 */
public final class ProductoDAO extends DAO {
       public void guardarProducto(Producto producto) throws Exception{
       try {
            //Se verifica si el usuario es nulo y lanza un error
            if(producto == null){
                throw new Exception("Debe ingresar un fabricante");
            }
             //Se lanza el insertar para mandar la orden, en este caso, el usuario
            //Se agrega comillas simples para el varchar de SQL
            String sql = "INSERT INTO Fabricante(codigo, nombre, precio, codigo_fabricante)"
                     +"VALUES(null, "+producto.getNombre()+", "+producto.getPrecio()+", "+producto.getCodigoFabricante()+");";
            insertarModificarEliminar(sql);

       } catch (Exception e) {
            throw e;
       }
   }
       public void modificarProducto(Producto producto) throws Exception{
       try {
           //Se verifica si el usuario es nulo y lanza un error
           if(producto == null){
               throw new Exception("Debe seleccionar el producto que desea modificar");
           }
            //Se lanza el insertar para mandar la orden, en este caso, el usuario
            //Se agrega comillas simples para el varchar de SQL
            //Se lanza la consulta de actualizar
            String sql = "UPDATE Fabricante SET"+
                    "nombre="+producto.getNombre()+", precio="+producto.getPrecio()+" WHERE codigo="+producto.getCodigo()+";";
            insertarModificarEliminar(sql);
       } catch (Exception e) {
       throw e;
       }
   }
       public void eliminarProducto(Integer codigo) throws Exception{
                  try {
                    //Eliminar usuario por codigo
             String sql = "DELETE FROM Producto WHERE codigo="+codigo+";";
             insertarModificarEliminar(sql);
       } catch (Exception e) {
           throw e;
       }
       }
       public Producto buscarPorductoPorId(Integer codigo) throws Exception{
        try{
    String sql = "SELECT * FROM Producto WHERE codigo="+codigo+";";
     //Se llama al metodo consultar base para consulta la query
     consultarBase(sql);
     Producto producto =  null;
       while (resultado.next()) {
            //Para rellenar el objeto usuario se declara un bucle while indicando que si la variable
            //resultado capta algo con el metodo next()
            producto = new Producto();
            producto.setCodigo(resultado.getInt(1));
            producto.setNombre(resultado.getString(2));
            producto.setPrecio(resultado.getDouble(3));
            producto.setCodigoFabricante((Fabricante)resultado.getObject(4));
              //Se le asigna el id con el metodo getInt 1 que seria la primera columna, con el metodo getInt o getString se 
                //coloca el numero de la columna empezando en 
           
       }
         //Se desconecta de la base despues de recibir el resultado y haber generado la consulta
            //despues de retorna el usuario
            desconectarBase();
            return producto;
       }catch(Exception e){
                //Si se encuentra algun error se desconecta de la base de datos
            desconectarBase();
            throw e;
       }
   }
       public Collection<Producto> listarProductos() throws Exception{
       try {
           String sql = "SELECT codigo, nombre, precio, codigo_fabricante FROM Producto;";
           consultarBase(sql);
           Producto producto = null;
           Collection<Producto> productos = new ArrayList();
           while (resultado.next()) {
           producto = new Producto();
           producto.setCodigo(resultado.getInt(1));
           producto.setNombre(resultado.getString(2));
           producto.setPrecio(resultado.getDouble(3));
           producto.setCodigoFabricante((Fabricante)resultado.getObject(4));
           productos.add(producto);
               
           }
           desconectarBase();
           return productos;
       
       
       } catch (Exception e) {
       e.printStackTrace();
        desconectarBase();
        throw new Exception("Error de sistema");
       }
   } 
       public Collection<Producto> listarNombreProductos () throws Exception{
           try {
            String sql = "SELECT nombre FROM Producto;";
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {                   
            producto = new Producto();
            producto.setNombre(resultado.getString(1));
            productos.add(producto);
                
                
            }
            desconectarBase();
            return productos;
           
           
           } catch (Exception e) {
           e.printStackTrace();
           desconectarBase();
           throw new Exception("Error en el sistema");
           
           }
       }
       public Collection<Producto> listarNombreYPrecioProductos() throws Exception{
           try {
           String sql = "SELECT nombre, precio FROM Productos;";
           consultarBase(sql);
           Producto producto = null;
           Collection<Producto> productos = new ArrayList();
               while (resultado.next()) {                   
                   producto = new Producto();
                   producto.setNombre(resultado.getString(1));
                   producto.setPrecio(resultado.getDouble(2));
                   productos.add(producto);
               }
               desconectarBase();
               return productos;
               
               
           } catch (Exception e) {
               e.printStackTrace();
               desconectarBase();
               throw new Exception("Error en el sistema");
           }
       }
       public Collection<Producto> listarPrecioProductos()throws Exception{
           try {
           String sql ="SELECT precio FROM Producto WHERE precio >= 120 AND precio <=202;";    
           consultarBase(sql);
           Producto producto = null;
           Collection<Producto> productos = new ArrayList();
               while (resultado.next()) {                   
                   producto = new Producto();
                   producto.setPrecio(resultado.getDouble(1));
                   productos.add(producto);
               }
               desconectarBase();
               return productos;
               } catch (Exception e) {
           e.printStackTrace();
           desconectarBase();
           throw new Exception("Error en el sistema");
           }
       }
       
}
