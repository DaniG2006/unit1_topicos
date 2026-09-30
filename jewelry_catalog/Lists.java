import java.awt.BorderLayout;
import javax.swing.*;

public class Lists {
    public static void main(String[] args) {
        JFrame frame = new JFrame("List Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        String[] listItems = {"List Item 1", "List Item 2", "List Item 3", "List Item 4"};
        JList<String> list = new JList<>(listItems);
        JScrollPane scrollPane = new JScrollPane(list);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(comboBox, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
