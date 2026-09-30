import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EventButtonExample extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private final JButton button1 = new JButton("Button 1");
    private final JButton button2 = new JButton("Button 2");
    private final JButton button3 = new JButton("Button 3");
    private final JButton button4 = new JButton("Button 4");
    private final JButton resetButton = new JButton("Reset");
    private final JLabel label1 = new JLabel("1", SwingConstants.CENTER);
    private final JLabel label2 = new JLabel("2", SwingConstants.CENTER);

    public EventButtonExample() {
        setTitle("Event Button Example");
        setSize(320, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        button1.setActionCommand("Button 1");
        button2.setActionCommand("Button 2");
        button3.setActionCommand("Button 3");
        button4.setActionCommand("Button 4");
        resetButton.setActionCommand("Reset");

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        resetButton.addActionListener(this);

        panel.add(button1);
        panel.add(button2);
        panel.add(label1);
        panel.add(button3);
        panel.add(label2);
        panel.add(button4);
        panel.add(resetButton);
        panel.add(new JLabel());

        label1.setFont(new Font("Arial", Font.BOLD, 18));
        label2.setFont(new Font("Arial", Font.BOLD, 18));

        add(panel);
    }
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == button1) {
            setTitle("Title Button 1");
            label1.setText("Button 1");
        } else if (source == button2) {
            setTitle("Title Button 2");
            label1.setText("Button 2");
        } else if (source == button3) {
            setTitle("Title Button 3");
            label1.setText("Button 3");
        } else if (source == button4) {
            setTitle("Title Button 4");
            label2.setText("Button 4");
            label2.setFont(new Font("Arial", Font.BOLD, 24));
            label2.setForeground(Color.BLUE);
        } else if (source == resetButton) {
            setTitle("Event Button Example");
            label1.setText("1");
            label2.setText("2");
            label1.setFont(new Font("Arial", Font.BOLD, 18));
            label2.setFont(new Font("Arial", Font.BOLD, 18));
            label2.setForeground(Color.BLACK);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EventButtonExample example = new EventButtonExample();
            example.setVisible(true);
        });
    }
}