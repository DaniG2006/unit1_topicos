import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class MatrizTable extends JFrame implements ActionListener {

    private JButton btnCapturar = new JButton("Capturar");
    private String[][] data = new String[0][5];
    private String[] columnNames = {"Col 1", "Col 2", "Col 3", "Col 4", "Col 5"};
    private DefaultTableModel model = new DefaultTableModel(columnNames, 5);
    private JTable table = new JTable(model);

    public MatrizTable() {
        super("Data Matriz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        for (int r = 0; r < model.getRowCount(); r++) {
            for (int c = 0; c < model.getColumnCount(); c++) {
                model.setValueAt("", r, c);
            }
        }

        table.setPreferredScrollableViewportSize(new Dimension(450, 150));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(btnCapturar);
        btnCapturar.addActionListener(this);
        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void guardar(String a, String b, String c, String d, String e) {
        data = Arrays.copyOf(data, data.length + 1);
        data[data.length - 1] = new String[]{a, b, c, d, e};
        model.addRow(data[data.length - 1]);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if (source == btnCapturar) {
            int pregunta;
            String[] dato = new String[5];
            boolean resp = true;
            while (resp) {
                for (int i = 0; i < 5; i++) {
                    dato[i] = JOptionPane.showInputDialog(this, "Introduzca el Dato " + (i + 1) + ":");
                    if (dato[i] == null) {
                        dato[i] = "";
                    }
                }
                guardar(dato[0], dato[1], dato[2], dato[3], dato[4]);
                pregunta = JOptionPane.showConfirmDialog(this, "¿Está seguro que quiere guardar los datos?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (pregunta == JOptionPane.NO_OPTION) {
                    resp = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MatrizTable());
    }
}