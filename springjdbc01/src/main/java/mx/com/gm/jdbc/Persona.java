package mx.com.gm.jdbc;

public class Persona {

    private long id;
    private String nombre;
    private String paterno;
    private String materno;
    private String email;

    public Persona() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Persona {" +
                "Id: " + id +
                ", Nombre: " + nombre +
                ", Paterno: " + paterno +
                ", Materno: " + materno +
                ", Email: " + email +
                "}";
    }
}
