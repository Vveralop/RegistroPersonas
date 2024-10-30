package persona.dominio;

import java.sql.Date;
import java.util.Objects;


public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private Date fechaRegistro;

    public Persona() {
    }

    public Persona(int id) {
        this.id = id;
    }

    public Persona(String nombre, String apellido, String email, Date fechaRegistro) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
    }

    public Persona(int id, String nombre, String apellido, String email, Date fechaRegistro) {
        this(nombre, apellido, email, fechaRegistro);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return id == persona.id && Objects.equals(nombre, persona.nombre) && Objects.equals(apellido, persona.apellido) && Objects.equals(email, persona.email) && Objects.equals(fechaRegistro, persona.fechaRegistro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, email, fechaRegistro);
    }
}
