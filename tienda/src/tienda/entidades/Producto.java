
package tienda.entidades;

/**
 *
 * @author Sebas
 */
public class Producto {
    private int codigo;
    private String nombre;
    private double precio;
    private Fabricante codigo_fabricante;

    public Producto() {
    }

    public Producto(int codigo, String nombre, double precio, Fabricante codigo_fabricante) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.codigo_fabricante = codigo_fabricante;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Fabricante getCodigo_fabricante() {
        return codigo_fabricante;
    }

    public void setCodigo_fabricante(Fabricante codigo_fabricante) {
        this.codigo_fabricante = codigo_fabricante;
    }

    @Override
    public String toString() {
        return String.format("Producto: %d, %s, %.2d, %s", codigo,nombre,precio,codigo_fabricante);
    }
    
    
    
}
