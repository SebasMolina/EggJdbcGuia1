
package tienda.persistencia;

import java.util.ArrayList;
import java.util.List;
import tienda.entidades.Fabricante;

/**
 *
 * @author Sebas
 */
public class FabricanteDAO extends DAO{
    
    public void guardarFabricante(Fabricante fabricante) throws Exception {
        try{
            if (fabricante == null){
                throw new Exception("El fabricante no puede ser nulo");
            }
            String template = "INSERT INTO fabricante VALUES (NULL, '%s');";
            String sql = String.format(template, fabricante.getNombre());
            System.out.println("Consulta:");        
            System.out.println(sql);
            
            insertarModificarEliminar(sql);
                    
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Hubo un error al guardar el fabricante");
        }
    }
    
    public void modificarFabricante(Fabricante fabricante) throws Exception {
        try{
            if (fabricante == null){
                throw new Exception("El fabricante no puede ser nulo");
            }
            String template = "UPDATE fabricante set nombre= '%s' WHERE codigo = '%s';";
            String sql = String.format(template, fabricante.getNombre(), fabricante.getCodigo());
            insertarModificarEliminar(sql);
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Hubo un error al modificar el fabricante");
        }
    }
    
    public void eliminarFabricante(Fabricante fabricante) throws Exception {
        try{
            if (fabricante == null){
                throw new Exception("El fabricante no puede ser nulo");
            }
            String template = "DELETE FROM fabricante WHERE codigo = '%s';";
            String sql = String.format(template, fabricante.getCodigo());
            insertarModificarEliminar(sql);
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Hubo un error al eliminar el fabricante");
        }
    }
    
    public List<Fabricante> obtenerFabricante() throws Exception {
        try {
            String sql = "SELECT * FROM fabricante;";

            consultarDatabase(sql);

            List<Fabricante> fabricantes = new ArrayList<>();
            Fabricante fabricante;

            while (resultSet.next()) {
                fabricante = new Fabricante();

                fabricante.setCodigo(resultSet.getInt(1));
                fabricante.setNombre(resultSet.getString(2));
                fabricantes.add(fabricante);
            }

            return fabricantes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener fabricantes");
        } finally {
            desconectarDatabase();
        }
    }
}
