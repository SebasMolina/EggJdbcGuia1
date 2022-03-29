package jdbc.persistence;

import java.util.ArrayList;
import java.util.List;
import jdbc.entity.Person;

public class PersonDAO extends DAO {

    public void savePerson(Person person) throws Exception {
        try {
            if (person == null) {
                throw new Exception("La persona no puede ser nula");
            }

            String template = "INSERT INTO person VALUES (NULL, '%s', '%s', '%s');";
            String sql = String.format(template, person.getFirstName(), person.getLastName(), person.getEmail());

            System.out.println("STATEMENT");
            System.out.println(sql);

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al guardar persona");
        }
    }

    public void modifyPerson(Person person) throws Exception {
        try {
            if (person == null) {
                throw new Exception("La persona no puede ser nula");
            }

            String template = "UPDATE person SET first_name = '%s', last_name = '%s' WHERE person_id = %s;";
            String sql = String.format(template, person.getFirstName(), person.getLastName(), person.getPersonId());

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al modificar persona");
        }
    }

    public void deletePerson(Integer personId) throws Exception {
        try {
            String sql = "DELETE FROM person WHERE person_id = " + personId + ";";

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al eliminar persona");
        }
    }

    public List<Person> getPeople() throws Exception {
        try {
            String sql = "SELECT * FROM person;";

            queryDatabase(sql);

            List<Person> persons = new ArrayList<>();
            Person person;

            while (resultSet.next()) {
                person = new Person();

                person.setPersonId(resultSet.getInt(1));
                person.setFirstName(resultSet.getString(2));
                person.setLastName(resultSet.getString(3));
                person.setEmail(resultSet.getString(4));

                persons.add(person);
            }

            return persons;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener personas");
        } finally {
            disconnectDatabase();
        }
    }
}
