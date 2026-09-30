import javax.swing.*;
import java.awt.*;

public class Password extends JFrame {

    private final JLabel label1 = new JLabel("Nombre de usuario:");
    private final JLabel label2 = new JLabel("Contraseña de usuario:");
    private final JLabel label3 = new JLabel("Comentarios:");

    private final JTextField textField = new JTextField(8);
    private final JPasswordField passwordField = new JPasswordField(8);
    private final JTextArea textArea = new JTextArea(5, 20);

    public Password() {
        setTitle("Area y Password");
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        formPanel.add(label1);
        formPanel.add(textField);

        formPanel.add(label2);
        formPanel.add(passwordField);

        formPanel.add(label3);
        formPanel.add(textArea);

        content.add(formPanel, BorderLayout.CENTER);

        add(content);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Password frame = new Password();
            frame.setVisible(true);
        });
    }
}

