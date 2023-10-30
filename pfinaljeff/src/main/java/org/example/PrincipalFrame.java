package org.example;

import javax.swing.*;
import java.awt.*;

class PrincipalFrame extends JFrame {
    private JButton addButtonStudent;
    private JButton addButtonCourse;
    private JButton addButtonRegistration;
    private JButton readButtonStudent;

    // Constructor de la clase
    public PrincipalFrame() {
        setTitle("Menú Principal"); // Establece el título de la ventana
        setSize(400, 250); // Establece el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Establece la operación de cierre

        JPanel panel = new JPanel(new GridLayout(4, 1)); // Crea un panel con una cuadrícula de 4 filas y 1 columna
        addButtonStudent = new JButton("Agregar Estudiante");
        addButtonCourse = new JButton("Agregar Curso");
        addButtonRegistration = new JButton("Agregar Inscripción");
        readButtonStudent = new JButton("Leer Estudiante");

        // Definición de colores y fuente para los botones y el panel
        Color backgroundColor = new Color(52, 73, 94);
        Color buttonColor = new Color(41, 128, 185);
        Color buttonTextColor = new Color(255, 255, 255);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        // Establece los colores y fuente para los botones y el panel
        panel.setBackground(backgroundColor);
        addButtonStudent.setBackground(buttonColor);
        addButtonCourse.setBackground(buttonColor);
        addButtonRegistration.setBackground(buttonColor);
        readButtonStudent.setBackground(buttonColor);

        addButtonStudent.setForeground(buttonTextColor);
        addButtonCourse.setForeground(buttonTextColor);
        addButtonRegistration.setForeground(buttonTextColor);
        readButtonStudent.setForeground(buttonTextColor);

        addButtonStudent.setFont(buttonFont);
        addButtonCourse.setFont(buttonFont);
        addButtonRegistration.setFont(buttonFont);
        readButtonStudent.setFont(buttonFont);

        // Agrega los botones al panel
        panel.add(addButtonStudent);
        panel.add(addButtonCourse);
        panel.add(addButtonRegistration);
        panel.add(readButtonStudent);

        add(panel); // Agrega el panel a la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true); // Hace visible la ventana

        // Agrega ActionListener a los botones para abrir los formularios correspondientes
        addButtonStudent.addActionListener(e -> abrirFormulario(new StudentDetailsFrame()));
        addButtonCourse.addActionListener(e -> abrirFormulario(new CourseDetailsFrame()));
        addButtonRegistration.addActionListener(e -> abrirFormulario(new InscripcionFrame()));
        readButtonStudent.addActionListener(e -> abrirFormulario(new LeerEstudianteFrame()));
    }

    // Método para abrir un formulario en una nueva ventana
    private void abrirFormulario(JFrame frame) {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PrincipalFrame::new);
    }
}
