package persona.dao;

import persona.conexion.Conexion;
import persona.dominio.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static persona.conexion.Conexion.getConnection;

public class PersonaDao implements IPersonaDao{

    @Override
    public List<Persona> listarPersona() {
        List<Persona> personas = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        var sql = "select * from persona order by id";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                var persona = new Persona();
                persona.setId(rs.getInt("id"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setEmail(rs.getString("email"));
                persona.setFechaRegistro(rs.getDate("fechaRegistro"));
                personas.add(persona);
            }
        } catch (Exception e){
            System.out.println("Error al listar personas: " + e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
            }
        }
        return personas;
    }

    @Override
    public boolean buscarPersonaById(Persona persona) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        var sql = "select * from persona where id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, persona.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                persona.setId(rs.getInt("id"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setEmail(rs.getString("email"));
                persona.setFechaRegistro(rs.getDate("fechaRegistro"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al recuperar persona por id: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
            }
        }
        return false;
    }

    @Override
    public boolean agregarPersona(Persona persona) {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "insert into persona (nombre, apellido, email, fechaRegistro) values (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setString(3, persona.getEmail());
            ps.setDate(4, persona.getFechaRegistro());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al grabar persona: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión. " + e);
            }
        }

        return false;
    }

    @Override
    public boolean modificarPersona(Persona persona) {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "update persona set nombre = ?, apellido = ?, email = ?, fechaRegistro = ? where id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setString(3, persona.getEmail());
            ps.setDate(4, persona.getFechaRegistro());
            ps.setInt(5, persona.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al grabar persona: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión. " + e);
            }
        }
        return false;
    }

    @Override
    public boolean borrarPersona(Persona persona) {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "delete from persona where id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, persona.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al grabar persona: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión. " + e);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IPersonaDao personaDao = new PersonaDao();

        // Select all
        //System.out.println(" --- Clientes --- ");
        //var personas = personaDao.listarPersona();
        //personas.forEach(System.out::println);

        // Select by id
//        var persona1 = new Persona(12);
//        System.out.println("Persona antes de la búsqueda: " +  persona1);
//        var found = personaDao.buscarPersonaById(persona1);
//        if (found){
//            System.out.println("Persona encontrada: " + persona1);
//        } else {
//            System.out.println("No se encontró persona: " + persona1.getId());
//        }

        // Add Persona
//        var fecha = new Date(124,10,30);
//        var nuevaPersona = new Persona("Leslie", "Merino", "lmerino@hotmail.com", fecha);
//        var agregar = personaDao.agregarPersona(nuevaPersona);
//        if (agregar){
//            System.out.println("Registro agregado.");
//        } else {
//            System.out.println("Registro no se pudo agregar.");
//        }
//
//        System.out.println(" --- Personas --- ");
//        var personas = personaDao.listarPersona();
//        personas.forEach(System.out::println);

        // Update Persona
//        var modificaPersona = new Persona(14,"Luis Alejandro", "Perez", "laPerez@hotmail.com", new Date(124, 10, 30) );
//        var modificado = personaDao.modificarPersona(modificaPersona);
//        if (modificado){
//            System.out.println("Registro modificado.");
//        } else {
//            System.out.println("Registro no se pudo modificar.");
//        }
//        System.out.println(" --- Personas --- ");
//        var personas = personaDao.listarPersona();
//        personas.forEach(System.out::println);

        var eliminarPersona = new Persona(15);
        var eliminado = personaDao.borrarPersona(eliminarPersona);
        if (eliminado){
            System.out.println("Registro eliminado.");
        } else {
            System.out.println("Registro no se pudo eliminar.");
        }
        System.out.println(" --- Personas --- ");
        var personas = personaDao.listarPersona();
        personas.forEach(System.out::println);

    }
}
