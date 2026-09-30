import javax.swing.*;
import java.awt.*;

public class Button extends JFrame {

    private final JLabel label1 = new JLabel("Instituto Tecnologico de Durango");
    private final JLabel label2 = new JLabel("Num de Control:");
    private final JLabel label3 = new JLabel("Nombre:");
    private final JLabel label4 = new JLabel("Correo:");
    private final JLabel label5 = new JLabel("Carrera:");
    private final JLabel label6 = new JLabel("Semestre:");

    private final JTextField textField1 = new JTextField(30);
    private final JTextField textField2 = new JTextField(30);
    private final JTextField textField3 = new JTextField(30);
    private final JTextField textField4 = new JTextField("Ingeniería en Sistemas Computacionales", 30);
    private final JTextField textField5 = new JTextField(30);

    private final JButton button1 = new JButton("Inscribir");
    private final JButton button2 = new JButton("Salir");

    public Button() {
        setTitle("Cedula de inscripcion");
        setSize(500, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(label1);
        content.add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.add(label2);
        formPanel.add(textField1);

        formPanel.add(label3);
        formPanel.add(textField2);

        formPanel.add(label4);
        formPanel.add(textField3);

        formPanel.add(label5);
        formPanel.add(textField4);

        formPanel.add(label6);
        formPanel.add(textField5);

        formPanel.add(new JLabel(""));

        content.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
       
        content.add(buttonPanel, BorderLayout.SOUTH);

        add(content);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Button frame = new Button();
            frame.setVisible(true);
        });
    }
}