package tienda.principal;

import tienda.servicios.FabricanteServicio;
import tienda.servicios.PrincipalServicio;
import tienda.servicios.ProductoServicio;

/**
 *
 * @author Sebas
 */
public class Principal {

    
    public static void main(String[] args) {
        PrincipalServicio principalServicio = new PrincipalServicio();
        try {
            principalServicio.menuPrincipal();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
