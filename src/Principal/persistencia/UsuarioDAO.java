/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal.persistencia;

import Principal.dominio.usuario.Usuario;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Admin
 */
//Se le póne el final para que no se pueda heredar de esta clase y se extiende de DAO para heredar los metodos
public final class UsuarioDAO extends DAO {
    public void guardarUsuario(Usuario usuario) throws Exception{
        try {
            //Se verifica si el usuario es nulo y lanza un error
            if (usuario == null) {
                throw new Exception("Debe indicar un usuario");
            }
            //Se lanza el insertar para mandar la orden, en este caso, el usuario
            //Se agrega comillas simples para el varchar de SQL
            
            String sql = "INSERT INTO Usuario(id, correoElectronico, clave) VALUES (null, "+usuario.getCorreoElectronico()+", "+usuario.getClave()+");"; 
            //Se llama el metodo del padre DAO insertarModificarEliminar
            insertarModificarEliminar(sql);
            
            
            
        } catch (Exception e) {
            throw e;
        }
    }
    public void modificarUsuario(Usuario usuario) throws Exception{
        try {
            //Se verifica si el usuario es nulo y lanza un error
            if (usuario == null) {
                throw new Exception("Debe indicar el usuario que desea modificar");
            }
            //Se lanza el insertar para mandar la orden, en este caso, el usuario
            //Se agrega comillas simples para el varchar de SQL
            //Se lanza la consulta de actualizar
            String sql = "UPDATE Usuario SET"+
                    "clave ='"+usuario.getClave()+"'WHERE correoElectronico = '"+usuario.getCorreoElectronico()+"');"; 
            //Se llama el metodo del padre DAO insertarModificarEliminar
            insertarModificarEliminar(sql);
            
            
        } catch (Exception e) {
            throw e;
        }
    }
    public void eliminarUsuario(String correoElectronico) throws Exception{
        try {
            
            //Eliminar usuario por correo electronico
            String sql = "DELETE FROM Usuario WHERE correoElectronico = '"+correoElectronico+"'";
                    
            //Se llama el metodo del padre DAO insertarModificarEliminar
            insertarModificarEliminar(sql);
            
            
            
        } catch (Exception e) {
            throw e;
        }
    }
    public Usuario buscarUsuarioPorCorreo(String correoElectronico) throws Exception{
        try {
            String sql = "SELECT * FROM Usuario WHERE correoElectronico = "+correoElectronico+";";
            //Se llama al metodo consultar base para consulta la query
            consultarBase(sql);
            
            Usuario usuario = null;
            //Para rellenar el objeto usuario se declara un bucle while indicando que si la variable
            //resultado capta algo con el metodo next()
            while (resultado.next()) {                
                usuario = new Usuario();
                //Se le asigna el id con el metodo getInt 1 que seria la primera columna, con el metodo getInt o getString se 
                //coloca el numero de la columna empezando en 1
                usuario.setId(resultado.getInt(1));
                usuario.setCorreoElectronico(resultado.getString(2));
                usuario.setClave(resultado.getString(3));
            }
            //Se desconecta de la base despues de recibir el resultado y haber generado la consulta
            //despues de retorna el usuario
            desconectarBase();
            return usuario;
        } catch (Exception e) {
            //Si se encuentra algun error se desconecta de la base de datos
            desconectarBase();
            throw e;
        }
    }
    public Collection<Usuario> listarUsuarios() throws Exception{
        try {
            String sql = "SELECT correoElectronico, clave FROM Usuario ";
            consultarBase(sql);
            
            Usuario usuario = null;
            //Se crea una collection list, para meter a los usuarios
            Collection<Usuario> usuarios = new ArrayList();
            while (resultado.next()) {
                //Se crea el objeto usuario y se le ingresan los datos
                usuario = new Usuario();
                //En este caso se pone 1 el correo en la columna porque en la consulta esta como primer parametro
                usuario.setCorreoElectronico(resultado.getString(1));
                usuario.setClave(resultado.getString(2));
                //Se añaden al array list
                usuarios.add(usuario);
            }
            //Se desconecta de la base y se retorna a los usuarios
            desconectarBase();
            return usuarios;
        } catch (Exception e) {
        e.printStackTrace();
        desconectarBase();
        throw new Exception("Error de sistema");
        }
    }
}
