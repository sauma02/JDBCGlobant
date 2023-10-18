/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Principal;

import Principal.dominio.usuario.UsuarioServicio;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UsuarioServicio us = new UsuarioServicio();
        //Se utiliza el bloque try para probar el servicio
        try {
            us.crearUsuario("'chiquito@chiquito'", "'asdasdasda'");
            
        } catch (Exception ex) {
           Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
