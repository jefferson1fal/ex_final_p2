package org.example;

import java.util.Objects;

public class EstudiantesClass {
    private int idEstudiante;
    private String nombre;
    private String apellido;
    private String email;

    // Constructor predeterminado (no se muestra en el código) o puedes agregar uno si es necesario.

    // Getter para obtener el ID del estudiante
    public int getIdEstudiante() {
        return idEstudiante;
    }

    // Setter para establecer el ID del estudiante
    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    // Getter para obtener el nombre del estudiante
    public String getNombre() {
        return nombre;
    }

    // Setter para establecer el nombre del estudiante
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para obtener el apellido del estudiante
    public String getApellido() {
        return apellido;
    }

    // Setter para establecer el apellido del estudiante
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Getter para obtener el email del estudiante
    public String getEmail() {
        return email;
    }

    // Setter para establecer el email del estudiante
    public void setEmail(String email) {
        this.email = email;
    }

    // Método equals para comparar dos objetos de tipo EstudiantesClass
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Si son la misma instancia, son iguales
        if (o == null || getClass() != o.getClass()) return false; // Si o es nulo o no es de la misma clase, no son iguales
        EstudiantesClass that = (EstudiantesClass) o; // Hacemos un casting a EstudiantesClass
        // Comparamos los campos idEstudiante, nombre, apellido y email
        return idEstudiante == that.idEstudiante && Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido) && Objects.equals(email, that.email);
    }

    // Método hashCode para generar un código hash basado en los campos de la clase
    @Override
    public int hashCode() {
        return Objects.hash(idEstudiante, nombre, apellido, email);
    }
}
