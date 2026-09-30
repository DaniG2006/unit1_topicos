import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SalaryCalculator extends JFrame {

    private final Color GOLD = new Color(232, 196, 82);
    private final Color DARK_BG = new Color(7, 62, 72);
    private final Color BTN_BG = new Color(9, 46, 53);

    private final JTextField txtName = new JTextField(18);
    private final JComboBox<String> cmbPosition = new JComboBox<>(new String[] {
            "Gerente", "Supervisor", "Operador"
    });
    private final JSpinner spnHours = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));

    private final JLabel lblOutput = new JLabel();
    private final DefaultTableModel recordsModel = new DefaultTableModel(new String[]{"Nombre", "Puesto", "Horas (h)", "Sueldo ($)", "Registrado"}, 0) {
        public boolean isCellEditable(int row, int column) {
            return column == 0 || column == 1 || column == 2;
        }

        public void setValueAt(Object aValue, int row, int column) {
            super.setValueAt(aValue, row, column);
            if (column == 1 || column == 2) {
                String puesto = getValueAt(row, 1) != null ? getValueAt(row, 1).toString() : "";
                String horasStr = getValueAt(row, 2) != null ? getValueAt(row, 2).toString() : "0";
                // 提取数字
                String digits = horasStr.replaceAll("\\D+", "");
                int horas = 0;
                try { horas = Integer.parseInt(digits); } catch (Exception ex) { horas = 0; }

                int bono;
                switch (puesto) {
                    case "Gerente": bono = 150; break;
                    case "Supervisor": bono = 100; break;
                    case "Operador": bono = 50; break;
                    default: bono = 0;
                }
                int sueldo = horas * 50 + bono;
                super.setValueAt(horas + " h", row, 2);
                super.setValueAt("$" + sueldo, row, 3);
                java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String ahora = java.time.LocalDateTime.now().format(fmt);
                super.setValueAt(ahora, row, 4); 
                saveAllRecordsToFile();
            }
        }
    };
    private final JTable recordsTable = new JTable(recordsModel);

    private final JButton btnSueldo = new JButton("Sueldo");
    private final JButton btnReset = new JButton("Reset");
    private final JButton btnEditar = new JButton("Editar");
    private final JButton btnEliminar = new JButton("Eliminar");
    private final JButton btnExportar = new JButton("Guardar como");

    public SalaryCalculator() {
        super("Payroll - Salary Calculator");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
    public void windowClosing(java.awt.event.WindowEvent e) {
        int option = JOptionPane.showConfirmDialog(
                SalaryCalculator.this,
                "Deseas salir del programa?",
                "Warning",
                JOptionPane.YES_NO_OPTION
        );

        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
});
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(DARK_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JLabel logoLabel = new JLabel(createLogoIcon(120, 120));
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        JLabel title = new JLabel("MI EMPRESA");
        title.setFont(new Font("Arial", Font.BOLD, 42));
        title.setForeground(GOLD);

        JLabel subtitle = new JLabel("S.A DE C.V");
        subtitle.setFont(new Font("Arial", Font.BOLD, 28));
        subtitle.setForeground(GOLD);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);
        titlePanel.add(title);
        titlePanel.add(subtitle);

        headerPanel.add(logoLabel);
        headerPanel.add(titlePanel);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(40, 40, 40), 2));
        formPanel.setPreferredSize(new Dimension(700, 220));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 18));
        lblNombre.setForeground(GOLD);

        JLabel lblPuesto = new JLabel("Puesto:");
        lblPuesto.setFont(new Font("Arial", Font.BOLD, 18));
        lblPuesto.setForeground(GOLD);

        JLabel lblHoras = new JLabel("Horas:");
        lblHoras.setFont(new Font("Arial", Font.BOLD, 18));
        lblHoras.setForeground(GOLD);

        txtName.setFont(new Font("Arial", Font.PLAIN, 18));
        txtName.setForeground(GOLD);
        txtName.setBackground(new Color(9, 46, 53));
        txtName.setCaretColor(GOLD);
        txtName.setBorder(BorderFactory.createLineBorder(GOLD, 2));

        UIManager.put("ComboBox.focus", new Color(0, 0, 0, 0));
        UIManager.put("ComboBox.focusCellHighlightBorder", BorderFactory.createEmptyBorder());
        UIManager.put("ComboBox.selectionBackground", new Color(9, 46, 53));
        UIManager.put("ComboBox.selectionForeground", GOLD);
        
        cmbPosition.setFont(new Font("Arial", Font.PLAIN,18));
        cmbPosition.setForeground(GOLD);
        cmbPosition.setBackground(new Color(9, 46, 53));
        cmbPosition.setBorder(BorderFactory.createLineBorder(GOLD, 2));
        cmbPosition.setOpaque(true);
        cmbPosition.setFocusable(false);
        cmbPosition.setRequestFocusEnabled(false);

        spnHours.setFont(new Font("Arial", Font.PLAIN, 18));
        spnHours.setForeground(GOLD);
        spnHours.setBackground(new Color(9, 46, 53));
        spnHours.setBorder(BorderFactory.createLineBorder(GOLD, 2));
        spnHours.setOpaque(true);

        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spnHours.getEditor();
        editor.getTextField().setBackground(new Color(9, 46, 53));
        editor.getTextField().setForeground(GOLD);
        editor.getTextField().setCaretColor(GOLD);
        editor.getTextField().setBorder(BorderFactory.createLineBorder(GOLD, 2));

        btnSueldo.setFont(new Font("Arial", Font.BOLD, 16));
        btnSueldo.setForeground(GOLD);
        btnSueldo.setBackground(BTN_BG);
        btnSueldo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GOLD, 2),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        btnSueldo.setFocusPainted(false);
        btnSueldo.setOpaque(true);

        btnReset.setFont(new Font("Arial", Font.BOLD, 16));
        btnReset.setForeground(GOLD);
        btnReset.setBackground(BTN_BG);
        btnReset.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GOLD, 2),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        btnReset.setFocusPainted(false);
        btnReset.setOpaque(true);

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(lblNombre, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.gridwidth = 3;
        formPanel.add(txtName, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(lblPuesto, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        formPanel.add(cmbPosition, gbc);

        gbc.gridx = 2; gbc.gridy = 1;
        formPanel.add(lblHoras, gbc);

        gbc.gridx = 3; gbc.gridy = 1;
        formPanel.add(spnHours, gbc);

        gbc.gridx = 3; gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(btnSueldo, gbc);

        gbc.gridx = 3; gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(btnReset, gbc);

        JPanel resultPanel = new JPanel();
        resultPanel.setOpaque(false);
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(GOLD),
        "PAGO",
        0,
        0,
        new Font("Arial", Font.BOLD, 20),
        GOLD
       ));
        resultPanel.setPreferredSize(new Dimension(700, 200));

        recordsTable.setFillsViewportHeight(true);
        recordsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recordsTable.setRowHeight(28);
        recordsTable.setFont(new Font("Arial", Font.PLAIN, 16));
        recordsTable.setForeground(GOLD);
        recordsTable.setBackground(new Color(9, 46, 53));
        recordsTable.setShowGrid(true);
        recordsTable.setGridColor(new Color(80, 80, 80));
        recordsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        recordsTable.getTableHeader().setBackground(BTN_BG);
        recordsTable.getTableHeader().setForeground(GOLD);
        recordsTable.getTableHeader().setReorderingAllowed(false);

        recordsTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(new Color(9, 46, 53));
                c.setForeground(GOLD);
                setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
                if (isSelected) {
                    c.setBackground(new Color(34, 90, 100));
                }
                return c;
            }
        });
        JScrollPane tableScroll = new JScrollPane(recordsTable);
        tableScroll.setPreferredSize(new Dimension(650, 150));

        resultPanel.add(Box.createVerticalStrut(10));
        resultPanel.add(tableScroll);

        JPanel tableButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 6));
        tableButtons.setOpaque(false);

        JButton[] smallBtns = new JButton[]{btnEditar, btnEliminar, btnExportar};
        for (JButton b : smallBtns) {
            b.setFont(new Font("Arial", Font.BOLD, 14));
            b.setForeground(GOLD);
            b.setBackground(BTN_BG);
            b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GOLD, 2),
                BorderFactory.createEmptyBorder(6, 12, 6, 12)
            ));
            b.setFocusPainted(false);
            b.setOpaque(true);
            tableButtons.add(b);
        }

        resultPanel.add(tableButtons);
        resultPanel.add(Box.createVerticalStrut(10));

        mainPanel.add(headerPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(formPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(resultPanel);

        add(mainPanel, BorderLayout.CENTER);

        loadRecordsFromFile();

        btnSueldo.addActionListener(e -> calcularSueldo());
        btnReset.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                    SalaryCalculator.this,
                    "Estás seguro que quieres resetear la programa?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION
            );
            if (opcion == JOptionPane.YES_OPTION) {
                resetFormulario();
            }
        });
        btnEliminar.addActionListener(e -> {
            int row = recordsTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Por favor selecciona una fila para eliminar", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            int conf = JOptionPane.showConfirmDialog(this, "Deseas eliminar la fila seleccionada?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (conf == JOptionPane.YES_OPTION) {
                recordsModel.removeRow(row);
                saveAllRecordsToFile();
            }
        });

        btnEditar.addActionListener(e -> {
            int row = recordsTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Por favor selecciona una fila para editar", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String curName = recordsModel.getValueAt(row, 0).toString();
            String curPuesto = recordsModel.getValueAt(row, 1).toString();
            String curHoras = recordsModel.getValueAt(row, 2).toString().replaceAll("\\D+", "");
            int horasVal = 0;
            try { horasVal = Integer.parseInt(curHoras); } catch (Exception ex) { horasVal = 0; }

            JPanel editPanel = new JPanel(new GridBagLayout());
            editPanel.setBackground(DARK_BG);
            GridBagConstraints gbc2 = new GridBagConstraints();
            gbc2.insets = new Insets(6,6,6,6);
            gbc2.fill = GridBagConstraints.HORIZONTAL;

            gbc2.gridx = 0; gbc2.gridy = 0;
            JLabel lblEditName = new JLabel("Nombre:");
            lblEditName.setForeground(GOLD);
            lblEditName.setFont(new Font("Arial", Font.BOLD, 14));
            editPanel.add(lblEditName, gbc2);
            gbc2.gridx = 1;
            JTextField nameField = new JTextField(curName, 18);
            nameField.setBackground(new Color(9,46,53));
            nameField.setForeground(GOLD);
            nameField.setBorder(BorderFactory.createLineBorder(GOLD, 1));
            editPanel.add(nameField, gbc2);

            gbc2.gridx = 0; gbc2.gridy = 1;
            JLabel lblEditPuesto = new JLabel("Puesto:");
            lblEditPuesto.setForeground(GOLD);
            lblEditPuesto.setFont(new Font("Arial", Font.BOLD, 14));
            editPanel.add(lblEditPuesto, gbc2);
            gbc2.gridx = 1;
            JComboBox<String> posField = new JComboBox<>(new String[]{"Gerente","Supervisor","Operador"});
            posField.setSelectedItem(curPuesto);
            posField.setBackground(new Color(9,46,53));
            posField.setForeground(GOLD);
            posField.setBorder(BorderFactory.createLineBorder(GOLD, 1));
            editPanel.add(posField, gbc2);

            gbc2.gridx = 0; gbc2.gridy = 2;
            JLabel lblEditHoras = new JLabel("Horas:");
            lblEditHoras.setForeground(GOLD);
            lblEditHoras.setFont(new Font("Arial", Font.BOLD, 14));
            editPanel.add(lblEditHoras, gbc2);
            gbc2.gridx = 1;
            JSpinner hrsField = new JSpinner(new SpinnerNumberModel(horasVal, 0, 1000, 1));
            JSpinner.DefaultEditor hrsEditor = (JSpinner.DefaultEditor) hrsField.getEditor();
            hrsEditor.getTextField().setBackground(new Color(9,46,53));
            hrsEditor.getTextField().setForeground(GOLD);
            hrsField.setBorder(BorderFactory.createLineBorder(GOLD, 1));
            editPanel.add(hrsField, gbc2);

            int res = JOptionPane.showConfirmDialog(this, editPanel, "Editar registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (res == JOptionPane.OK_OPTION) {
                String nuevoNombre = nameField.getText().trim();
                String nuevoPuesto = (String) posField.getSelectedItem();
                int nuevoHoras = (int) hrsField.getValue();

                int bono;
                switch (nuevoPuesto) {
                    case "Gerente": bono = 150; break;
                    case "Supervisor": bono = 100; break;
                    case "Operador": bono = 50; break;
                    default: bono = 0;
                }
                int nuevoSueldo = nuevoHoras * 50 + bono;
                java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String ahora = java.time.LocalDateTime.now().format(fmt);

                recordsModel.setValueAt(nuevoNombre, row, 0);
                recordsModel.setValueAt(nuevoPuesto, row, 1);
                recordsModel.setValueAt(nuevoHoras + " h", row, 2);
                recordsModel.setValueAt("$" + nuevoSueldo, row, 3);
                recordsModel.setValueAt(ahora, row, 4);
                saveAllRecordsToFile();
            }
        });

        btnExportar.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Guardar como");
            chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV files", "csv"));
            int res = chooser.showSaveDialog(this);
            if (res == JFileChooser.APPROVE_OPTION) {
                File f = chooser.getSelectedFile();
                if (!f.getName().toLowerCase().endsWith(".csv")) {
                    f = new File(f.getParentFile(), f.getName() + ".csv");
                }
                exportRecordsToFile(f);
                JOptionPane.showMessageDialog(this, "Registros exportados a: " + f.getAbsolutePath(), "Exportado", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private final String DATA_FILE = "records.csv";

    private void saveRecordToFile(Object[] row) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(DATA_FILE, true))) {
            pw.println(csvJoin(row));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void saveAllRecordsToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(DATA_FILE))) {
            // header
            pw.println(String.join(",", Arrays.asList("Nombre","Puesto","Horas","Sueldo","Registrado")));
            for (int r = 0; r < recordsModel.getRowCount(); r++) {
                Object[] row = new Object[recordsModel.getColumnCount()];
                for (int c = 0; c < recordsModel.getColumnCount(); c++) {
                    row[c] = recordsModel.getValueAt(r, c);
                }
                pw.println(csvJoin(row));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadRecordsFromFile() {
        Path p = Paths.get(DATA_FILE);
        if (!Files.exists(p)) return;
        try {
            List<String> lines = Files.readAllLines(p);
            boolean first = true;
            for (String line : lines) {
                if (first) { first = false; continue; } // skip header
                if (line.trim().isEmpty()) continue;
                String[] parts = splitCsvLine(line);
                if (parts.length < 5) continue;
                recordsModel.addRow(new Object[]{parts[0], parts[1], parts[2], parts[3], parts[4]});
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void exportRecordsToFile(File file) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            pw.println(String.join(",", Arrays.asList("Nombre","Puesto","Horas","Sueldo","Registrado")));
            for (int r = 0; r < recordsModel.getRowCount(); r++) {
                Object[] row = new Object[recordsModel.getColumnCount()];
                for (int c = 0; c < recordsModel.getColumnCount(); c++) {
                    row[c] = recordsModel.getValueAt(r, c);
                }
                pw.println(csvJoin(row));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al exportar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String csvJoin(Object[] row) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row.length; i++) {
            if (i > 0) sb.append(',');
            sb.append(escapeCsv(String.valueOf(row[i] != null ? row[i] : "")));
        }
        return sb.toString();
    }

    private String escapeCsv(String s) {
        String out = s.replace("\"", "\"\"");
        return "\"" + out + "\"";
    }

    private String[] splitCsvLine(String line) {
        List<String> parts = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder cur = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (ch == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    cur.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (ch == ',' && !inQuotes) {
                parts.add(cur.toString());
                cur.setLength(0);
            } else {
                cur.append(ch);
            }
        }
        parts.add(cur.toString());
        return parts.toArray(new String[0]);
    }
    private ImageIcon createLogoIcon(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();

        GradientPaint gold = new GradientPaint(0, 0, new Color(234, 201, 103), width, height, new Color(159, 118, 32));
        g2.setPaint(gold);
        g2.setStroke(new BasicStroke(18f));

        Arc2D arc = new Arc2D.Double(12, 12, width - 24, height - 24, 40, 290, Arc2D.OPEN);
        g2.draw(arc);

        g2.setColor(new Color(7, 62, 72));
        g2.fill(new Ellipse2D.Double(34, 34, width - 68, height - 68));

        g2.setPaint(gold);
        g2.setStroke(new BasicStroke(12f));
        g2.draw(new Ellipse2D.Double(34, 34, width - 68, height - 68));

        g2.dispose();
        return new ImageIcon(image);
    }

    private void calcularSueldo() {
        String nombre = txtName.getText().trim();
        String puesto = (String) cmbPosition.getSelectedItem();
        int horas = (int) spnHours.getValue();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa tu nombre", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (horas == 0) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un numero valido para la hora", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        

        int bono;

        switch (puesto) {
            case "Gerente":
                bono = 150;
                break;
            case "Supervisor":
                bono = 100;
                break;
            case "Operador":
                bono = 50;
                break;
            default:
                bono = 0;
        }

        int sueldo = horas * 50 + bono;

        java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String ahora = java.time.LocalDateTime.now().format(fmt);
        String sueldoStr = "$" + sueldo;
        Object[] newRow = new Object[]{nombre, puesto, horas + " h", sueldoStr, ahora};
        recordsModel.addRow(newRow);
        saveRecordToFile(newRow);
    }

    private void resetFormulario() {
        txtName.setText("");
        cmbPosition.setSelectedIndex(0);
        spnHours.setValue(0);
        lblOutput.setText("<html><center>Nombre: <br>Puesto: <br>Sueldo: $0</center></html>");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SalaryCalculator().setVisible(true));
    }
}