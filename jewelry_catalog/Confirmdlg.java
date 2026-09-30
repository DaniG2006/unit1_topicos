import javax.swing.*;

public class Confirmdlg extends JFrame {
    public Confirmdlg() {
        super("Confirm Dialog Example");
        metodoVentana();
        metodoComponentes();
    }
    private void metodoVentana() {
        setTitle("Confirm Dialog Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void metodoComponentes() {
        JButton button = new JButton("Continue with the action");
        button.setBounds(100, 100, 200, 30);
        button.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(this, "Do you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "You chose Yes.");
            } else {
                JOptionPane.showMessageDialog(this, "You chose No.");
            }
        });
        add(button);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Confirmdlg frame = new Confirmdlg();
            frame.setVisible(true);
        });
    }
}