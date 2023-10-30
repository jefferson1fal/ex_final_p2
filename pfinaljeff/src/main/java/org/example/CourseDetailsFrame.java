package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseDetailsFrame extends JFrame {
    private JTextField courseNameField;
    private JTextField professorField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;

    public CourseDetailsFrame() {
        setTitle("Gestión de Cursos");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear un panel y configurar el administrador de diseño
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        // Crear elementos de la interfaz de usuario
        JLabel courseNameLabel = new JLabel("Nombre del Curso:");
        JLabel professorLabel = new JLabel("Profesor:");
        courseNameField = new JTextField(20);
        professorField = new JTextField(20);
        addButton = new JButton("Agregar");
        deleteButton = new JButton("Eliminar");
        updateButton = new JButton("Actualizar");

        // Agregar ActionListeners a los botones
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCurso();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCurso();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCurso();
            }
        });

        // Agregar elementos a la interfaz utilizando el administrador de diseño
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(courseNameLabel, constraints);

        constraints.gridx = 1;
        panel.add(courseNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(professorLabel, constraints);

        constraints.gridx = 1;
        panel.add(professorField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(addButton, constraints);

        constraints.gridx = 1;
        panel.add(deleteButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(updateButton, constraints);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void agregarCurso() {
        String nombreCurso = courseNameField.getText();
        String profesor = professorField.getText();

        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuarioDB = "postgres";
            String contrasenaDB = "mysecretpassword";

            // Establecer una conexión con la base de datos PostgreSQL
            Connection conexion = DriverManager.getConnection(url, usuarioDB, contrasenaDB);

            // Consulta SQL para insertar un nuevo curso
            String sql = "INSERT INTO cursos (nombre_curso, profesor) VALUES (?, ?)";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nombreCurso);
            statement.setString(2, profesor);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(CourseDetailsFrame.this,
                        "Curso agregado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                // Limpiar los campos del formulario después de agregar el curso
                courseNameField.setText("");
                professorField.setText("");
            } else {
                JOptionPane.showMessageDialog(CourseDetailsFrame.this,
                        "No se pudo agregar el curso",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Manejo de errores en caso de problemas de base de datos
        }
    }

    private void eliminarCurso() {
        String nombreCurso = courseNameField.getText();

        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuarioDB= "postgres";
            String contrasenaDB = "mysecretpassword";

            // Establecer una conexión con la base de datos PostgreSQL
            Connection conexion = DriverManager.getConnection(url, usuarioDB, contrasenaDB);

            // Consulta SQL para eliminar un curso
            String sql = "DELETE FROM cursos WHERE nombre_curso = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nombreCurso);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(CourseDetailsFrame.this,
                        "Curso eliminado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                // Limpiar los campos del formulario después de eliminar el curso
                courseNameField.setText("");
                professorField.setText("");
            } else {
                JOptionPane.showMessageDialog(CourseDetailsFrame.this,
                        "No se pudo eliminar el curso",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Manejo de errores en caso de problemas de base de datos
        }
    }

    private void actualizarCurso() {
        String nombreCurso = courseNameField.getText();
        String nuevoProfesor = professorField.getText();

        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuarioDB = "postgres";
            String contrasenaDB = "mysecretpassword";

            // Establecer una conexión con la base de datos PostgreSQL
            Connection conexion = DriverManager.getConnection(url, usuarioDB, contrasenaDB);

            // Consulta SQL para actualizar el profesor de un curso
            String sql = "UPDATE cursos SET profesor = ? WHERE nombre_curso = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nuevoProfesor);
            statement.setString(2, nombreCurso);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(CourseDetailsFrame.this,
                        "Curso actualizado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                // Limpiar los campos del formulario después de actualizar el curso
                courseNameField.setText("");
                professorField.setText("");
            } else {
                JOptionPane.showMessageDialog(CourseDetailsFrame.this,
                        "No se pudo actualizar el curso",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Manejo de errores en caso de problemas de base de datos
        }
    }

    public static void main(String[] args) {
        // Iniciar la aplicación Swing en un hilo de eventos separado
        SwingUtilities.invokeLater(CourseDetailsFrame::new);
    }
}