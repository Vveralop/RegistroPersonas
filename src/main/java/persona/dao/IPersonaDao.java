package persona.dao;

import persona.dominio.Persona;

import java.util.List;

public interface IPersonaDao  {
    List<Persona> listarPersona();
    boolean buscarPersonaById(Persona persona);
    boolean agregarPersona(Persona persona);
    boolean modificarPersona(Persona persona);
    boolean borrarPersona(Persona persona);
}
