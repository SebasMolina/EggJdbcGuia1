package jdbc.service;

import java.util.List;
import jdbc.entity.Person;
import jdbc.persistence.PersonDAO;

public class PersonService {

    private final PersonDAO personDAO;

    public PersonService() {
        personDAO = new PersonDAO();
    }

    public void createPerson(String firstName, String lastName, String email) throws Exception {
        try {
            if (firstName == null || firstName.trim().isEmpty()) {
                throw new Exception("El nombre es obligatorio");
            }

            if (lastName == null || lastName.trim().isEmpty()) {
                throw new Exception("El apellido es obligatorio");
            }

            if (email == null || email.trim().isEmpty()) {
                throw new Exception("El correo es obligatorio");
            }

            Person person = new Person();

            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setEmail(email);

            personDAO.savePerson(person);
        } catch (Exception e) {
            throw e;
        }
    }

    public void printPeople() throws Exception {
        try {
            List<Person> persons = personDAO.getPeople();

            if (persons.isEmpty()) {
                throw new Exception("No existen personas");
            } else {
                System.out.println("LIST PEOPLE");
                System.out.printf("%-10s%-15s%-15s%-20s\n", "ID", "FIRST NAME", "LAST NAME", "EMAIL");
                for (Person person : persons) {
                    System.out.printf("%-10s%-15s%-15s%-20s\n", person.getPersonId(), person.getFirstName(), person.getLastName(), person.getEmail());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
