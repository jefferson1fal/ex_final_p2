package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class StudentDetailsFrame extends JFrame {
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;

    // Constructor de la clase
    public StudentDetailsFrame() {
        setTitle("Gestión de Estudiantes"); // Establece el título de la ventana
        setSize(400, 250); // Establece el tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Establece la operación de cierre

        // Crear un panel y elementos de la interfaz de usuario
        JPanel panel = new JPanel(new GridLayout(5, 2)); // Crea un panel con una cuadrícula de 5 filas y 2 columnas
        JLabel nameLabel = new JLabel("Nombre:");
        JLabel lastNameLabel = new JLabel("Apellido:");
        JLabel emailLabel = new JLabel("Email:");
        nameField = new JTextField(20);
        lastNameField = new JTextField(20);
        emailField = new JTextField(20);
        addButton = new JButton("Agregar");
        deleteButton = new JButton("Eliminar");
        updateButton = new JButton("Actualizar");

        // Configurar colores personalizados
        Color backgroundColor = new Color(34, 49, 63); // Color de fondo oscuro
        Color labelColor = new Color(236, 240, 241); // Color de etiquetas
        Color buttonColor = new Color(46, 204, 113); // Color del botón (Turquesa)
        Color buttonTextColor = new Color(255, 255, 255); // Color del texto en los botones (Blanco)

        panel.setBackground(backgroundColor);
        nameLabel.setForeground(labelColor);
        lastNameLabel.setForeground(labelColor);
        emailLabel.setForeground(labelColor);
        nameField.setBackground(Color.LIGHT_GRAY);
        lastNameField.setBackground(Color.LIGHT_GRAY);
        emailField.setBackground(Color.LIGHT_GRAY);
        addButton.setBackground(buttonColor);
        addButton.setForeground(buttonTextColor);
        deleteButton.setBackground(buttonColor);
        deleteButton.setForeground(buttonTextColor);
        updateButton.setBackground(buttonColor);
        updateButton.setForeground(buttonTextColor);

        // Agregar ActionListeners a los botones
        addButton.addActionListener(e -> agregarEstudiante());
        deleteButton.addActionListener(e -> eliminarEstudiante());
        updateButton.addActionListener(e -> actualizarEstudiante());

        // Agregar elementos de la interfaz al panel y el panel a la ventana
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(updateButton);

        add(panel); // Agrega el panel a la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true); // Hace visible la ventana
    }

    // Método para agregar un estudiante a la base de datos
    private void agregarEstudiante() {
        String nombre = nameField.getText();
        String apellido = lastNameField.getText();
        String email = emailField.getText();

        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuarioDB = "postgres";
            String contrasenaDB = "mysecretpassword";

            // Establecer una conexión con la base de datos PostgreSQL
            Connection conexion = DriverManager.getConnection(url, usuarioDB, contrasenaDB);

            // Consulta SQL para insertar un nuevo estudiante
            String sql = "INSERT INTO estudiantes (nombre, apellido, email) VALUES (?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, email);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(StudentDetailsFrame.this,
                        "Estudiante agregado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                // Limpiar los campos del formulario después de agregar el estudiante
                nameField.setText("");
                lastNameField.setText("");
                emailField.setText("");
            } else {
                JOptionPane.showMessageDialog(StudentDetailsFrame.this,
                        "No se pudo agregar el estudiante",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Manejo de errores en caso de problemas de base de datos
        }
    }
    private void eliminarEstudiante() {
        String nombre = nameField.getText();
        String apellido = lastNameField.getText();

        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuarioDB = "postgres";
            String contrasenaDB = "mysecretpassword";

            // Establecer una conexión con la base de datos PostgreSQL
            Connection conexion = DriverManager.getConnection(url, usuarioDB, contrasenaDB);

            // Consulta SQL para eliminar un estudiante por nombre y apellido
            String sql = "DELETE FROM estudiantes WHERE nombre = ? AND apellido = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, apellido);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(StudentDetailsFrame.this,
                        "Estudiante eliminado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                // Limpiar los campos del formulario después de eliminar el estudiante
                nameField.setText("");
                lastNameField.setText("");
                emailField.setText("");
            } else {
                JOptionPane.showMessageDialog(StudentDetailsFrame.this,
                        "No se pudo eliminar el estudiante",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Manejo de errores en caso de problemas de base de datos
        }
    }

    private void actualizarEstudiante() {
        String nombre = nameField.getText();
        String apellido = lastNameField.getText();
        String email = emailField.getText();

        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuarioDB = "postgres";
            String contrasenaDB = "mysecretpassword";

            // Establecer una conexión con la base de datos PostgreSQL
            Connection conexion = DriverManager.getConnection(url, usuarioDB, contrasenaDB);

            // Consulta SQL para actualizar el email de un estudiante por nombre y apellido
            String sql = "UPDATE estudiantes SET email = ? WHERE nombre = ? AND apellido = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, nombre);
            statement.setString(3, apellido);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(StudentDetailsFrame.this,
                        "Estudiante actualizado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(StudentDetailsFrame.this,
                        "No se pudo actualizar el estudiante",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Manejo de errores en caso de problemas de base de datos
        }
    }
}
