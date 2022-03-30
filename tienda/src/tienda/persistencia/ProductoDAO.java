
package tienda.persistencia;

import java.util.ArrayList;
import java.util.List;
import tienda.entidades.Fabricante;
import tienda.entidades.Producto;

/**
 *
 * @author Sebas
 */
public class ProductoDAO extends DAO{
    
    public void guardarProducto(Producto producto) throws Exception {
        try{
            if (producto == null){
                throw new Exception("El producto no puede ser nulo");
            }
            String template = "INSERT INTO producto VALUES (NULL, '%s','%s','%s');";
            String sql = String.format(template, producto.getNombre(), producto.getPrecio(),producto.getCodigo_fabricante().getCodigo());
            System.out.println("Consulta:");        
            System.out.println(sql);
            
            insertarModificarEliminar(sql);
                    
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Hubo un error al guardar el producto");
        }
    }
    
    public void modificarProducto(Producto producto) throws Exception {
        try{
            if (producto == null){
                throw new Exception("El producto no puede ser nulo");
            }
            String template = "UPDATE producto set nombre= '%s', precio= '%s', codigo_fabricante= '%s'  WHERE codigo = '%s';";
            String sql = String.format(template, producto.getNombre(), producto.getPrecio(),producto.getCodigo_fabricante().getCodigo(),producto.getCodigo());
            insertarModificarEliminar(sql);
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Hubo un error al modificar el producto");
        }
    }
    
    public void eliminarProducto(Producto producto) throws Exception {
        try{
            if (producto == null){
                throw new Exception("El producto no puede ser nulo");
            }
            String template = "DELETE FROM producto WHERE codigo = '%s';";
            String sql = String.format(template, producto.getCodigo());
            insertarModificarEliminar(sql);
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Hubo un error al eliminar el producto");
        }
    }
    
    public List<Producto> obtenerProducto() throws Exception {
        try {
            String sql = "SELECT * FROM producto p INNER JOIN fabricante f ON p.codigo_fabricante=f.codigo;";

            consultarDatabase(sql);

            List<Producto> productos = new ArrayList<>();
            Producto producto;
            Fabricante fabricante;
            while (resultSet.next()) {
                producto = new Producto();
                fabricante = new Fabricante();
                producto.setCodigo(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setPrecio(resultSet.getDouble(3));
                fabricante.setCodigo(resultSet.getInt(5));
                fabricante.setNombre(resultSet.getString(6));
                producto.setCodigo_fabricante(fabricante);
                productos.add(producto);
            }
            return productos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener productos");
        } finally {
            desconectarDatabase();
        }
    }
    
    public List<Producto> obtenerProductoPorRangoPrecio() throws Exception {
        try {
            String sql = "SELECT * FROM producto p INNER JOIN fabricante f ON p.codigo_fabricante=f.codigo WHERE precio BETWEEN 120 AND 202 ORDER BY p.nombre ASC;";

            consultarDatabase(sql);

            List<Producto> productos = new ArrayList<>();
            Producto producto;
            Fabricante fabricante;
            while (resultSet.next()) {
                producto = new Producto();
                fabricante = new Fabricante();
                producto.setCodigo(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setPrecio(resultSet.getDouble(3));
                fabricante.setCodigo(resultSet.getInt(5));
                fabricante.setNombre(resultSet.getString(6));
                producto.setCodigo_fabricante(fabricante);
                productos.add(producto);
            }
            return productos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener productos");
        } finally {
            desconectarDatabase();
        }
    }
    
    public List<String> obtenerProductoNombre() throws Exception {
        try {
            String sql = "SELECT nombre FROM producto ORDER BY nombre ASC;";

            consultarDatabase(sql);

            List<String> nombreProductos = new ArrayList<>();
            String nombreProducto;
            while (resultSet.next()) {
                nombreProducto= resultSet.getString(1);
                nombreProductos.add(nombreProducto);
            }
            return nombreProductos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener productos");
        } finally {
            desconectarDatabase();
        }
    }
    
}
