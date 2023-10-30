package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LeerEstudianteFrame extends JFrame {
    private JTextField nombreEstudianteTextField;
    private JButton leerEstudianteButton;

    // Constructor de la clase
    public LeerEstudianteFrame() {
        setTitle("Leer Datos del Estudiante"); // Establece el título de la ventana
        setSize(400, 150); // Establece el tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Establece la operación de cierre

        JPanel panel = new JPanel(new GridLayout(2, 2)); // Crea un panel con una cuadrícula de 2 filas y 2 columnas

        JLabel nombreEstudianteLabel = new JLabel("Nombre del Estudiante:");
        nombreEstudianteTextField = new JTextField(20);
        leerEstudianteButton = new JButton("Leer Estudiante");

        panel.add(nombreEstudianteLabel);
        panel.add(nombreEstudianteTextField);
        panel.add(leerEstudianteButton);

        add(panel); // Agrega el panel a la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true); // Hace visible la ventana

        // Agrega un ActionListener al botón para manejar eventos de clic
        leerEstudianteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreEstudiante = nombreEstudianteTextField.getText().trim();
                leerEstudiante(nombreEstudiante);
            }
        });
    }

    // Método para leer datos de un estudiante por nombre
    private void leerEstudiante(String nombreEstudiante) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String usuario = "postgres";
        String contraseña = "mysecretpassword";

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "SELECT * FROM estudiantes WHERE nombre = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreEstudiante);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Crear una nueva ventana para mostrar los detalles del estudiante
                JFrame detallesFrame = new JFrame("Detalles del Estudiante");
                detallesFrame.setSize(300, 150);

                JPanel detallesPanel = new JPanel(new GridLayout(4, 2)); // Crea un panel con una cuadrícula de 4 filas y 2 columnas
                detallesPanel.add(new JLabel("ID:"));
                detallesPanel.add(new JLabel(String.valueOf(rs.getInt("id_estudiante"))));
                detallesPanel.add(new JLabel("Nombre:"));
                detallesPanel.add(new JLabel(rs.getString("nombre")));
                detallesPanel.add(new JLabel("Apellido:"));
                detallesPanel.add(new JLabel(rs.getString("apellido")));
                detallesPanel.add(new JLabel("Email:"));
                detallesPanel.add(new JLabel(rs.getString("email")));

                detallesFrame.add(detallesPanel);
                detallesFrame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
                detallesFrame.setVisible(true); // Hace visible la ventana de detalles
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron datos para el estudiante: " + nombreEstudiante, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al leer datos del estudiante", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LeerEstudianteFrame();
            }
        });
    }
}
