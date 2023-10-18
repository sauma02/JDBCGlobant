/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Principal.dominio.usuario;


import Principal.persistencia.UsuarioDAO;

/**
 *
 * @author Admin
 */
public class UsuarioServicio {
    private UsuarioDAO dao;

    public UsuarioServicio() {
        // Se inicializa el usuario dao dentro del constructor del servicio
        this.dao = new UsuarioDAO();
    }
    public void crearUsuario(String correoElectronico, String clave) throws Exception{
        //Se realiza la validacion dentro del catch
        
        try {
            //Si el correo es nulo o esta vacio que se verifica con los metodos trim() y isEmpty() 
            if(correoElectronico == null || correoElectronico.trim().isEmpty()){
                throw new Exception("Debe indicar el correo electronico");
            }
            if(correoElectronico.contains("@") == false){
                throw new Exception("El correo electronico es incorrecto");
            }
            if(clave == null || clave.trim().isEmpty()){
                throw new Exception("Debe indicar la clave");
            }
            if(clave.length() < 8){
                throw new Exception("La clave no puede tener menos de 8 caracteres");
            }
            if(dao.buscarUsuarioPorCorreo(correoElectronico) != null){
                throw new Exception("Ya existe un usuario con el correo electronico indicado "+correoElectronico);
            }
            //Se crea el usuario
            Usuario usuario = new Usuario();
            usuario.setCorreoElectronico(correoElectronico);
            usuario.setClave(clave);
            //Se guarda el usuario en la coleccion
            dao.guardarUsuario(usuario);

        } catch (Exception e) {
            throw e; 
        
        }
    }
    
    
}
