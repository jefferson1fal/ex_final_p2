package org.example;

import java.sql.Date;
import java.util.Objects;

public class InscripcionesClass {
    private int idInscripcion;
    private Integer idEstudiante;
    private Integer idCurso;
    private Date fechaInscripcion;

    // Constructor predeterminado (no se muestra en el código) o puedes agregar uno si es necesario.

    // Getter para obtener el ID de la inscripción
    public int getIdInscripcion() {
        return idInscripcion;
    }

    // Setter para establecer el ID de la inscripción
    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    // Getter para obtener el ID del estudiante
    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    // Setter para establecer el ID del estudiante
    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    // Getter para obtener el ID del curso
    public Integer getIdCurso() {
        return idCurso;
    }

    // Setter para establecer el ID del curso
    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    // Getter para obtener la fecha de inscripción
    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    // Setter para establecer la fecha de inscripción
    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    // Método equals para comparar dos objetos de tipo InscripcionesClass
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Si son la misma instancia, son iguales
        if (o == null || getClass() != o.getClass()) return false; // Si o es nulo o no es de la misma clase, no son iguales
        InscripcionesClass that = (InscripcionesClass) o; // Hacemos un casting a InscripcionesClass
        // Comparamos los campos idInscripcion, idEstudiante, idCurso y fechaInscripcion
        return idInscripcion == that.idInscripcion && Objects.equals(idEstudiante, that.idEstudiante) && Objects.equals(idCurso, that.idCurso) && Objects.equals(fechaInscripcion, that.fechaInscripcion);
    }

    // Método hashCode para generar un código hash basado en los campos de la clase
    @Override
    public int hashCode() {
        return Objects.hash(idInscripcion, idEstudiante, idCurso, fechaInscripcion);
    }
}
