/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tienda;

import tienda.servicios.ProductoServicio;

/**
 *
 * @author Admin
 */
public class Tienda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
       ProductoServicio ps = new ProductoServicio();
        try {
         ps.listarProductos();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        
    }
    
}
