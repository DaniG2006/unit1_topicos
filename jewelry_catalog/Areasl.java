import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Areasl extends JFrame implements ActionListener {
    JPanel panel = new JPanel(new FlowLayout());
    JMenu menu = new JMenu("File");
    JMenuBar menubar = new JMenuBar();
    JMenuItem item1 = new JMenuItem("Area");
    JMenuItem item2 = new JMenuItem("Form");
    JMenuItem item3 = new JMenuItem("Exit");

    JLabel lbBaseJLabel = new JLabel("Base:");
    JLabel lbHeight = new JLabel("Height:");
    JLabel lbResult = new JLabel("Result:");
    JLabel lbResultArea = new JLabel();

    JTextField tfBase = new JTextField(10);
    JTextField tfHeight = new JTextField(10);

    public Areasl() {
        super("Area Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        add(panel);
        menubar.add(menu);
        setJMenuBar(menubar);

        menu.add(item1);
        menu.addSeparator();
        menu.add(item2);
        menu.addSeparator();
        menu.add(item3);

        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item1.setToolTipText("Click the option");

        panel.add(lbBaseJLabel);
        panel.add(tfBase);
        panel.add(lbHeight);
        panel.add(tfHeight);
        panel.add(lbResult);
        panel.add(lbResultArea);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == item1) {
            try {
                double base = Double.parseDouble(tfBase.getText().trim());
                double height = Double.parseDouble(tfHeight.getText().trim());
                double area = 0.5 * base * height;
                lbResultArea.setText("The Area is: " + area);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for Base and Height.");
            }
        } else if (e.getSource() == item2) {
            JFrame form = new JFrame("Form");
            form.add(new JLabel("This is a form"));
            form.setLayout(new FlowLayout(FlowLayout.CENTER));
            form.setSize(300, 200);
            form.setLocationRelativeTo(this);
            form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            form.setVisible(true);
        } else if (e.getSource() == item3) {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Areasl());
    }
}