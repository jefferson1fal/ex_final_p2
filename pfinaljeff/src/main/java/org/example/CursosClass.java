package org.example;

import java.util.Objects;

public class CursosClass {
    private int idCurso;
    private String nombreCurso;
    private String profesor;

    // Constructor predeterminado (no se muestra en el código) o puedes agregar uno si es necesario.

    // Getter para obtener el ID del curso
    public int getIdCurso() {
        return idCurso;
    }

    // Setter para establecer el ID del curso
    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    // Getter para obtener el nombre del curso
    public String getNombreCurso() {
        return nombreCurso;
    }

    // Setter para establecer el nombre del curso
    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    // Getter para obtener el nombre del profesor
    public String getProfesor() {
        return profesor;
    }

    // Setter para establecer el nombre del profesor
    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    // Método equals para comparar dos objetos de tipo CursosClass
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Si son la misma instancia, son iguales
        if (o == null || getClass() != o.getClass()) return false; // Si o es nulo o no es de la misma clase, no son iguales
        CursosClass that = (CursosClass) o; // Hacemos un casting a CursosClass
        // Comparamos los campos idCurso, nombreCurso y profesor
        return idCurso == that.idCurso && Objects.equals(nombreCurso, that.nombreCurso) && Objects.equals(profesor, that.profesor);
    }

    // Método hashCode para generar un código hash basado en los campos de la clase
    @Override
    public int hashCode() {
        return Objects.hash(idCurso, nombreCurso, profesor);
    }
}
