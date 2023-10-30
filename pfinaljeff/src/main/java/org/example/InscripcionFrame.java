package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class InscripcionFrame extends JFrame {
    private JTextField studentIdField;
    private JTextField courseIdField;
    private JTextField registrationDateField;
    private JButton registerButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton exitButton;

    private Connection connection;

    public InscripcionFrame() {
        setTitle("Inscripción");
        setSize(600, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Establecer información de conexión
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String usuario = "postgres";
        String contraseña = "mysecretpassword";

        try {
            connection = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error de conexión a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Agrega espacio alrededor de los componentes
        panel.setBackground(Color.LIGHT_GRAY); // Color de fondo

        studentIdField = createLabeledTextField("ID Estudiante");
        courseIdField = createLabeledTextField("ID Curso");
        registrationDateField = createLabeledTextField("Fecha de Inscripción");

        registerButton = createButton("Registrar");
        updateButton = createButton("Actualizar Datos");
        deleteButton = createButton("Eliminar");
        exitButton = createButton("Salir");

        addFieldAndButton(panel, studentIdField);
        addFieldAndButton(panel, courseIdField);
        addFieldAndButton(panel, registrationDateField);
        addFieldAndButton(panel, registerButton);
        addFieldAndButton(panel, updateButton);
        addFieldAndButton(panel, deleteButton);
        addFieldAndButton(panel, exitButton);

        registerButton.addActionListener(e -> registrarEstudianteEnCurso());
        updateButton.addActionListener(e -> actualizarEstudianteEnCurso());
        deleteButton.addActionListener(e -> eliminarEstudianteDeCurso());
        exitButton.addActionListener(e -> System.exit(0)); // Cierra la aplicación

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JTextField createLabeledTextField(String label) {
        JTextField textField = new JTextField(15);
        textField.setBorder(BorderFactory.createTitledBorder(label));
        return textField;
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        return button;
    }

    private void addFieldAndButton(JPanel panel, JComponent component) {
        panel.add(component);
    }
    private void registrarEstudianteEnCurso() {
        try {
            String studentId = studentIdField.getText();
            int studentIdValue = Integer.parseInt(studentId);

            String courseId = courseIdField.getText();
            int courseIdValue = Integer.parseInt(courseId); // Convertir a INTEGER

            String registrationDate = registrationDateField.getText();

            if (studentId.isEmpty() || courseId.isEmpty() || registrationDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener la fecha actual
            Timestamp fechaInscripcion = Timestamp.valueOf(registrationDate + " 00:00:00");

            // Insertar datos en la tabla
            String sql = "INSERT INTO inscripciones (id_estudiante, id_curso, fecha_inscripcion) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, studentIdValue);
                statement.setInt(2, courseIdValue);
                statement.setTimestamp(3, fechaInscripcion);

                int filasAfectadas = statement.executeUpdate();

                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(this, "Estudiante inscrito correctamente en el curso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo realizar la inscripción", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private void actualizarEstudianteEnCurso() {
        try {
            String studentId = studentIdField.getText();
            int studentIdValue = Integer.parseInt(studentId);

            String courseId = courseIdField.getText();
            int courseIdValue = Integer.parseInt(courseId);

            String registrationDate = registrationDateField.getText();

            if (studentId.isEmpty() || courseId.isEmpty() || registrationDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener la fecha actual
            Timestamp fechaInscripcion = Timestamp.valueOf(registrationDate + " 00:00:00");

            // Actualizar datos en la tabla
            String sql = "UPDATE inscripciones SET fecha_inscripcion = ? WHERE id_estudiante = ? AND id_curso = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setTimestamp(1, fechaInscripcion);
                statement.setInt(2, studentIdValue);
                statement.setInt(3, courseIdValue);

                int filasAfectadas = statement.executeUpdate();

                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(this, "Datos actualizados correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo realizar la actualización", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private void eliminarEstudianteDeCurso() {
        try {
            String studentId = studentIdField.getText();
            int studentIdValue = Integer.parseInt(studentId);

            String courseId = courseIdField.getText();
            int courseIdValue = Integer.parseInt(courseId);

            if (studentId.isEmpty() || courseId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete los campos de ID Estudiante y ID Curso", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Eliminar datos de la tabla
            String sql = "DELETE FROM inscripciones WHERE id_estudiante = ? AND id_curso = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, studentIdValue);
                statement.setInt(2, courseIdValue);

                int filasAfectadas = statement.executeUpdate();

                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(this, "Estudiante eliminado correctamente del curso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(InscripcionFrame::new);
    }
}