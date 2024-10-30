package persona.presentacion;

import persona.dao.IPersonaDao;
import persona.dao.PersonaDao;
import persona.dominio.Persona;

import java.sql.Date;
import java.util.Scanner;

public class PersonaApp {
    public static void main(String[] args) {
        PersonaApp();
    }

    public static void PersonaApp() {
        var salir = false;
        var consola =  new Scanner(System.in);
        var personaDao = new PersonaDao();
        while (!salir){
            try {
                var opcion = menu(consola);
                System.out.println("Selecciono : " + opcion);
                salir = cargaOpciones(consola, opcion, personaDao);

            } catch (Exception e){
                System.out.println("Error  al ejecutar opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int menu(Scanner consola){
        System.out.println("""
                --- Mantención Personas ---
                1. Listar Personas
                2. Buscar Persona
                3. Agregar Persona
                4. Modificar Persona
                5. Eliminar Persona
                6. Salir
                Elije una opción: \s """);
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean cargaOpciones(Scanner consola, int opcion, IPersonaDao personaDao){
        var salir = false;
        switch (opcion){
            case 1 -> {
                //1. Listar personas
                System.out.println("--- Personas ---");
                var personas = personaDao.listarPersona();
                personas.forEach(System.out::println);
            }
            case 2 ->{
                //2. Buscar por id
                System.out.println("Ingresar persona: ");
                var idPersona = Integer.parseInt(consola.nextLine());
                var persona = new Persona(idPersona);
                var found = personaDao.buscarPersonaById(persona);
                if (found){
                    System.out.println("Persona encontrada: " + persona);
                } else {
                    System.out.println("No se encontró persona: " + persona.getId());
                }
            }
            case 3 ->{
                //3. Agregar por id
                System.out.println("Agregar persona");
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();

                System.out.println("Apellido: ");
                var apellido = consola.nextLine();

                System.out.println("Email: ");
                var email = consola.nextLine();

                var persona = new Persona(nombre, apellido, email, new Date(124, 9, 30));
                var agregar = personaDao.agregarPersona(persona);
                if (agregar){
                    System.out.println("Persona agregada: " + persona);
                } else {
                    System.out.println("No se agregó persona: " + persona.getId());
                }
            }
            case 4 ->{
                //4. Modificar por id
                System.out.println("Modificar persona");
                System.out.println("Id: ");
                var idPersona = Integer.parseInt(consola.nextLine());

                System.out.println("Nombre: ");
                var nombre = consola.nextLine();

                System.out.println("Apellido: ");
                var apellido = consola.nextLine();

                System.out.println("Email: ");
                var email = consola.nextLine();

                var persona = new Persona(idPersona, nombre, apellido, email, new Date(124, 9, 30));
                var mod = personaDao.modificarPersona(persona);
                if (mod){
                    System.out.println("Persona modificada: " + persona);
                } else {
                    System.out.println("No se modificó persona: " + persona.getId());
                }
            }
            case 5 ->{
                //5. Eliminar por id
                System.out.println("Eliminar persona");
                System.out.println("Ingresar id persona a eliminar: ");
                var idPersona = Integer.parseInt(consola.nextLine());
                var persona = new Persona(idPersona);
                var found = personaDao.borrarPersona(persona);
                if (found){
                    System.out.println("Persona eliminada: " + persona);
                } else {
                    System.out.println("No se encontró persona: " + persona.getId());
                }
            }
            case 6 -> {
                System.out.println("Saliendo");
                salir = true;
            }
            default -> {
                System.out.println("Opción no válida... Reingrese.");
            }
        }
        return  salir;
    }
}
