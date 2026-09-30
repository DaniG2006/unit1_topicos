import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;

public class CalculadoraAritmetica extends JFrame {
    public static void main(String[] args) {
            JFrame frame = new JFrame("CALCULADORA ARITMÉTICA");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(860, 640);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            JPanel desktop = new JPanel(new BorderLayout());
            desktop.setBackground(new Color(230, 232, 230));
            desktop.setBorder(new EmptyBorder(10, 10, 10, 10));
            JPanel toolbar = new JPanel(new BorderLayout());
            toolbar.setBackground(new Color(82, 80, 80));
            toolbar.setPreferredSize(new Dimension(0, 38));

            JMenuBar menuBar = new JMenuBar();
            menuBar.setBackground(new Color(82, 80, 80));
            menuBar.setBorderPainted(false);
            menuBar.setOpaque(true);

            JMenu fileMenu = new JMenu("Archivo");
            fileMenu.setForeground(Color.WHITE);
            fileMenu.setBackground(new Color(82, 80, 80));
            fileMenu.setOpaque(true);
            fileMenu.add(new JMenuItem("Nuevo"));
            fileMenu.addSeparator();
            fileMenu.add(new JMenuItem("Abrir"));
            fileMenu.addSeparator();
            fileMenu.add(new JMenuItem("Salir"));

            JMenu editMenu = new JMenu("Editar");
            editMenu.setForeground(Color.WHITE);
            editMenu.setBackground(new Color(82, 80, 80));
            editMenu.setOpaque(true);
            editMenu.add(new JMenuItem("Guardar"));
            editMenu.addSeparator();
            editMenu.add(new JMenuItem("Guardar como"));

            JMenuItem fileNew = new JMenuItem("Nuevo");
            fileNew.setBackground(new Color(82, 80, 80));
            fileNew.setForeground(Color.WHITE);
            JMenuItem fileOpen = new JMenuItem("Abrir");
            fileOpen.setBackground(new Color(82, 80, 80));
            fileOpen.setForeground(Color.WHITE);
            JMenuItem fileExit = new JMenuItem("Salir");
            fileExit.setBackground(new Color(82, 80, 80));
            fileExit.setForeground(Color.WHITE);

            fileMenu.removeAll();
            fileMenu.add(fileNew);
            fileMenu.addSeparator();
            fileMenu.add(fileOpen);
            fileMenu.addSeparator();
            fileMenu.add(fileExit);

            JMenuItem editSave = new JMenuItem("Guardar");
            editSave.setBackground(new Color(82, 80, 80));
            editSave.setForeground(Color.WHITE);
            JMenuItem editSaveAs = new JMenuItem("Guardar como");
            editSaveAs.setBackground(new Color(82, 80, 80));
            editSaveAs.setForeground(Color.WHITE);

            editMenu.removeAll();
            editMenu.add(editSave);
            editMenu.addSeparator();
            editMenu.add(editSaveAs);

            menuBar.add(fileMenu);
            menuBar.add(editMenu);

            JLabel title = new JLabel("Calculadora aritmética");
            title.setForeground(Color.WHITE);
            title.setFont(new Font("SansSerif", Font.PLAIN, 14));

            JPanel rightTools = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 8));
            rightTools.setOpaque(false);

            JButton minimize = new JButton("-");
            minimize.setFocusPainted(false);
            minimize.setFont(new Font("SansSerif", Font.BOLD, 16));
            minimize.setForeground(new Color(35, 35, 35));
            minimize.setBackground(new Color(228, 228, 224));
            minimize.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(110, 110, 110), 1),
                    BorderFactory.createEmptyBorder(4, 10, 4, 10))
            );
            minimize.setPreferredSize(new Dimension(34, 24));

            JButton close = new JButton("×");
            close.setFocusPainted(false);
            close.setFont(new Font("SansSerif", Font.BOLD, 16));
            close.setForeground(new Color(35, 35, 35));
            close.setBackground(new Color(228, 228, 224));
            close.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(110, 110, 110), 1),
                    BorderFactory.createEmptyBorder(4, 10, 4, 10))
            );
            close.setPreferredSize(new Dimension(34, 24));

            rightTools.add(minimize);
            rightTools.add(close);

            toolbar.add(menuBar, BorderLayout.WEST);
            toolbar.add(title, BorderLayout.CENTER);
            toolbar.add(rightTools, BorderLayout.EAST);
            desktop.add(toolbar, BorderLayout.NORTH);
            frame.setJMenuBar(menuBar);

            JPanel area = new JPanel(new GridBagLayout());
            area.setBackground(new Color(235, 237, 237));
            area.setBorder(new EmptyBorder(30, 50, 30, 50));

            JPanel calcPanel = new JPanel(new GridBagLayout());
            calcPanel.setBackground(new Color(210, 210, 200));
            calcPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(140, 140, 140), 2),
                    BorderFactory.createEmptyBorder(10, 12, 12, 12)
            ));

            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(6, 6, 6, 6);

            JTextField display = new JTextField("0");
            display.setEditable(true);
            display.setHorizontalAlignment(SwingConstants.RIGHT);
            display.setBackground(new Color(167, 214, 243));
            display.setForeground(new Color(24, 51, 78));
            display.setFont(new Font("SansSerif", Font.BOLD, 26));
            display.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(120, 120, 120), 1),
                    BorderFactory.createEmptyBorder(2, 8, 2, 8)));
            display.setPreferredSize(new Dimension(260, 44));
            display.setColumns(12);
            display.setCaretPosition(display.getText().length());
            ((AbstractDocument) display.getDocument()).setDocumentFilter(new DigitOnlyFilter());

            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 5;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            calcPanel.add(display, c);

            String[][] labels = {
                {"ON", "C", "%", "raiz", "+"},
                {"MC", "7", "8", "9", "-"},
                {"MR", "4", "5", "6", "/"},
                {"M+", "1", "2", "3", "x"},
                {"M-", "+/-", "0", ".", "="}
            };

            for (int row = 1; row <= 5; row++) {
                for (int col = 0; col < 5; col++) {
                    String text = labels[row - 1][col];
                    JButton btn = createButton(text);
                    c.gridx = col;
                    c.gridy = row;
                    c.gridwidth = 1;
                    c.weightx = 1.0;
                    c.weighty = 1.0;
                    c.fill = GridBagConstraints.BOTH;
                    calcPanel.add(btn, c);
                }
            }

            JPanel calcWrap = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            calcWrap.setOpaque(false);
            calcWrap.add(calcPanel);

            area.add(calcWrap, new GridBagConstraints());
            desktop.add(area, BorderLayout.CENTER);

            frame.setContentPane(desktop);
            frame.setVisible(true);
            frame.pack();
    }

    private static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setForeground(new Color(35, 35, 35));
        button.setBackground(new Color(228, 228, 224));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(110, 110, 110), 1),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        button.setPreferredSize(new Dimension(52, 48));
        button.setOpaque(true);
        return button;
    }

    private static class DigitOnlyFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (string == null) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < string.length(); i++) {
                char c = string.charAt(i);
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            super.insertString(fb, offset, sb.toString(), attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text == null) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            super.replace(fb, offset, length, sb.toString(), attrs);
        }
    }
}
