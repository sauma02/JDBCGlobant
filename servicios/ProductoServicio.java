/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.servicios;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDAO;

/**
 *
 * @author Admin
 */
public class ProductoServicio {
    private ProductoDAO dao;

    public ProductoServicio(ProductoDAO dao) {
        this.dao = new ProductoDAO();
    }

    public ProductoServicio() {
        
    }
    
    public void listarProductos() throws Exception{
        try {
            dao = new ProductoDAO();
            Collection<Producto> listarProductos = new ArrayList();
            listarProductos.add((Producto) dao.listarProductos());
        } catch (Exception e) {
        throw e;
        
        }
    }
}
