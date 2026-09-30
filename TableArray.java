import javax.swing.*;
import java.awt.*;

public class TableArray extends JFrame {

    public TableArray() {
        setTitle("Matrix Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String[][] data = {
            {"1", "A", "I"},
            {"2", "B", "II"},
            {"3", "C", "III"},
            {"4", "D", "IV"},
            {"5", "E", "V"}
        };

        String[] columnNames = {"Num", "Letter", "Roman"};
        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(300, 100));
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);      
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TableArray frame = new TableArray();
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}