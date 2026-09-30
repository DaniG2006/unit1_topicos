import javax.swing.*;
import java.awt.*;

public class RadioButton extends JFrame {

    private final JLabel titleLabel = new JLabel("Instituto Tecnologico de Durango", SwingConstants.CENTER);
    private final JLabel careerLabel = new JLabel("Selecciona tu carrera:");
    private final JLabel subjectsLabel = new JLabel("Selecciona tus materias:");

    private final JRadioButton option1 = new JRadioButton("Ing. en Sistemas Computacionales");
    private final JRadioButton option2 = new JRadioButton("Ing. en Informática");
    private final JRadioButton option3 = new JRadioButton("Ing. en Tecnologías de la Información y Comunicaciones");
    private final JRadioButton option4 = new JRadioButton("Ing. en Industrial");
    private final JRadioButton option5 = new JRadioButton("Ing. en Electronica");

    private final JCheckBox check1 = new JCheckBox("Calculo Integral");
    private final JCheckBox check2 = new JCheckBox("Topicos Avanzados de Programacion");
    private final JCheckBox check3 = new JCheckBox("Fundamentos de bases de datos");
    private final JCheckBox check4 = new JCheckBox("Programacion Web");
    private final JCheckBox check5 = new JCheckBox("Desarrollo de Aplicaciones Moviles");

    private final JButton button1 = new JButton("Aceptar");
    private final JButton button2 = new JButton("Cancelar");

    public RadioButton() {
        setTitle("Seleccion ITD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 420);
        setLocationRelativeTo(null);

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        content.add(titleLabel, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel careersPanel = new JPanel(new BorderLayout(5, 5));
        careersPanel.add(careerLabel, BorderLayout.NORTH);
        JPanel careersList = new JPanel(new GridLayout(5, 1, 5, 5));
        careersList.add(option1);
        careersList.add(option2);
        careersList.add(option3);
        careersList.add(option4);
        careersList.add(option5);
        careersPanel.add(careersList, BorderLayout.CENTER);

        JPanel subjectsPanel = new JPanel(new BorderLayout(5, 5));
        subjectsPanel.add(subjectsLabel, BorderLayout.NORTH);
        JPanel subjectsList = new JPanel(new GridLayout(5, 1, 5, 5));
        subjectsList.add(check1);
        subjectsList.add(check2);
        subjectsList.add(check3);
        subjectsList.add(check4);
        subjectsList.add(check5);
        subjectsPanel.add(subjectsList, BorderLayout.CENTER);

        center.add(careersPanel);
        center.add(subjectsPanel);
        content.add(center, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        content.add(buttonPanel, BorderLayout.SOUTH);

        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);
        group.add(option5);

        setContentPane(content);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RadioButton frame = new RadioButton();
            frame.setVisible(true);
        });
    }
}