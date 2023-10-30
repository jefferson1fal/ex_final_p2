package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.sql.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private PrincipalFrame principalFrame;

    public LoginFrame() {
        // Configuración de la ventana de inicio de sesión
        setTitle("Inicio de Sesión");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creación de un panel y elementos de la interfaz de usuario
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Iniciar Sesión");
        JLabel usernameLabel = new JLabel("Usuario:");
        JLabel passwordLabel = new JLabel("Contraseña");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Iniciar Sesión");

        // Configuración de colores personalizados
        Color backgroundColor = new Color(240, 240, 240); // Color de fondo claro
        Color labelColor = new Color(34, 49, 63); // Color de etiquetas (Azul oscuro)
        Color buttonColor = new Color(52, 152, 219); // Color de botón (Azul claro)
        Color textColor = new Color(34, 49, 63); // Color de texto (Azul oscuro)

        // Configuración de colores y estilo de elementos
        panel.setBackground(backgroundColor);
        titleLabel.setForeground(labelColor);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        usernameLabel.setForeground(labelColor);
        passwordLabel.setForeground(labelColor);
        usernameField.setBackground(Color.WHITE);
        passwordField.setBackground(Color.WHITE);
        loginButton.setBackground(buttonColor);
        loginButton.setForeground(Color.WHITE);

        // Alineación de elementos en el centro
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);

        // Agregar ActionListener al botón de inicio de sesión
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                // Llamar al método 'autenticar' para verificar las credenciales
                if (autenticar(username, password)) {
                    // Abrir la ventana principal
                    abrirVentanaPrincipal();
                } else {
                    // Mostrar un mensaje de error en caso de credenciales incorrectas
                    JOptionPane.showMessageDialog(LoginFrame.this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Borrar la contraseña en memoria y el campo de contraseña visible
                Arrays.fill(passwordChars, ' ');
                passwordField.setText("");
            }
        });

        // Agregar elementos de la interfaz a los paneles
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        buttonPanel.add(loginButton);

        // Agregar paneles al panel principal
        panel.add(titleLabel);
        panel.add(usernamePanel);
        panel.add(passwordPanel);
        panel.add(buttonPanel);

        // Agregar el panel principal a la ventana
        add(panel);

        // Configurar la ubicación de la ventana y hacerla visible
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para autenticar las credenciales del usuario
    private boolean autenticar(String username, String password) {
        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuarioDB = "postgres";
            String contrasenaDB = "mysecretpassword";

            // Establecer una conexión con la base de datos PostgreSQL
            Connection conexion = DriverManager.getConnection(url, usuarioDB, contrasenaDB);

            // Consulta SQL para verificar las credenciales
            String sql = "SELECT * FROM login WHERE username = ? AND password = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Las credenciales son correctas
                resultSet.close();
                statement.close();
                conexion.close();
                return true;
            }

            resultSet.close();
            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Manejo de errores en caso de problemas de base de datos
        }
        return false; // Autenticación fallida por defecto
    }

    // Método para abrir la ventana principal
    private void abrirVentanaPrincipal() {
        // Crear una instancia de la ventana principal
        principalFrame = new PrincipalFrame();
        principalFrame.setVisible(true);
        dispose(); // Cierra la ventana de inicio de sesión
    }

    public static void main(String[] args) {
        // Iniciar la aplicación Swing en el hilo de despacho de eventos
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
