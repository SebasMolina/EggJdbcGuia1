
package tienda.servicios;

import java.util.List;
import tienda.entidades.Fabricante;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDAO;

/**
 *
 * @author Sebas
 */
public class ProductoServicio {
    
    private final ProductoDAO productoDAO;

    public ProductoServicio() {
        productoDAO = new ProductoDAO();
    }
    
    public void crearProducto(String nombre, Double precio, Fabricante fabricante) throws Exception{
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre es obligatorio");
            }
            if (precio == null) {
                throw new Exception("El precio es obligatorio");
            }
            if (fabricante == null) {
                throw new Exception("El precio es obligatorio");
            }
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCodigo_fabricante(fabricante);
            
            productoDAO.guardarProducto(producto);
        } catch (Exception e) {
            throw e;
        } 
    }
    
    public void imprimirProductos() throws Exception {
        try {
            List<Producto> productos = productoDAO.obtenerProducto();

            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("LISTA DE PRODUCTOS");
                System.out.printf("%-10s%-35s%-10s%-5s\n", "CODIGO", "NOMBRE", "PRECIO","FABRICANTE");
                for (Producto producto : productos) {
                    System.out.printf("%-10d%-35s%-10.2f%-5s\n", producto.getCodigo(),producto.getNombre(), producto.getPrecio(),producto.getCodigo_fabricante().getNombre());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    public void imprimirProductosPorNombre() throws Exception {
        try {
            
            List<String> productos = productoDAO.obtenerProductoNombre();
            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("LISTA DE PRODUCTOS");
                System.out.printf("%-10s\n", "NOMBRE");
                for (String producto : productos) {
                    System.out.printf("%-10s\n", producto);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    public void imprimirProductosRangoPrecio() throws Exception {
        try {
            List<Producto> productos = productoDAO.obtenerProductoPorRangoPrecio();

            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("LISTA DE PRODUCTOS");
                System.out.printf("%-35s%-10s%-5s\n","NOMBRE", "PRECIO","FABRICANTE");
                for (Producto producto : productos) {
                    System.out.printf("%-35s%-10.2f%-5s\n", producto.getNombre(), producto.getPrecio(),producto.getCodigo_fabricante().getNombre());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
