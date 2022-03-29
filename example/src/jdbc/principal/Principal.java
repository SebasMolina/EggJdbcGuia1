package jdbc.principal;

import jdbc.service.PersonService;

public class Principal {

    public static void main(String[] args) {
        PersonService personService = new PersonService();

        try {
            personService.printPeople();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
