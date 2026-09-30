import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class WebServer extends JFrame {

    public WebServer() {
        setTitle("Windows Live Messenger");
        setSize(1200, 760);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu estadoMenu = new JMenu("Estado");
        JMenuItem disponibleItem = new JMenuItem("Disponible");
        JMenuItem ocupadoItem = new JMenuItem("Ocupado");
        JMenuItem ausenteItem = new JMenuItem("Ausente");

        estadoMenu.add(disponibleItem);
        estadoMenu.addSeparator();
        estadoMenu.add(ocupadoItem);
        estadoMenu.addSeparator();
        estadoMenu.add(ausenteItem);

        menuBar.add(estadoMenu);
        setJMenuBar(menuBar);

        JPanel root = new JPanel(new BorderLayout());
        root.setOpaque(false);
        root.setBackground(null);
        root.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel card = new JPanel();
        card.setLayout(new GridBagLayout());
        card.setOpaque(false);
        card.setBackground(null);
        card.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 2));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel(
            "<html>" +
            "<div style='font-family:Segoe UI; font-size:28px; color:#2d3d4a;'>Iniciar sesión en</div>" +
            "<div style='font-family:Segoe UI; font-size:42px; color:#2d3d4a;'>Windows Live <b>Messenger</b></div>" +
            "</html>"
        );
        title.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        card.add(title, gbc);

        JPanel avatarPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();

                g2.setColor(new Color(20, 29, 54));
                g2.fill(new Ellipse2D.Double(0, 0, w, h));

                g2.setColor(Color.WHITE);

                g2.fill(new Ellipse2D.Double(w * 0.32, h * 0.14, w * 0.36, h * 0.36));
                g2.fill(new Ellipse2D.Double(w * 0.18, h * 0.42, w * 0.64, h * 0.38));
                g2.fill(new Ellipse2D.Double(w * 0.22, h * 0.53, w * 0.56, h * 0.28));

                g2.dispose();
            }
        };
        avatarPanel.setPreferredSize(new Dimension(220, 220));
        avatarPanel.setOpaque(false);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        card.add(avatarPanel, gbc);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JTextField email = new JTextField();
        email.setText("example@gmail.com");
        email.setForeground(Color.GRAY);
        email.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        email.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if ("example@gmail.com".equals(email.getText())) {
                    email.setText("");
                    email.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (email.getText().trim().isEmpty()) {
                    email.setText("example@gmail.com");
                    email.setForeground(Color.GRAY);
                }
            }
        });

        JPasswordField password = new JPasswordField();
        password.setText("Contraseña");
        password.setEchoChar((char) 0);
        password.setForeground(Color.GRAY);
        password.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        password.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String value = new String(password.getPassword());
                if ("Contraseña".equals(value)) {
                    password.setText("");
                    password.setEchoChar('●');
                    password.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (password.getPassword().length == 0) {
                    password.setText("Contraseña");
                    password.setEchoChar((char) 0);
                    password.setForeground(Color.GRAY);
                }
            }
        });

        JLabel forgot = new JLabel("¿Has olvidado tu contraseña?");
        forgot.setForeground(new Color(21, 94, 190));
        forgot.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        forgot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgot.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel statusLabel = new JLabel("Iniciar sesión como:");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        statusLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        String[] estados = {"Disponible", "Ocupado", "Ausente"};
        JComboBox<String> estadoCombo = new JComboBox<>(estados);
        estadoCombo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        estadoCombo.setPreferredSize(new Dimension(180, 28));
        estadoCombo.setMaximumSize(new Dimension(180, 28));
        estadoCombo.setFocusable(false);

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        statusPanel.setOpaque(false);
        statusPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statusPanel.add(statusLabel);
        statusPanel.add(Box.createHorizontalStrut(10));
        statusPanel.add(estadoCombo);

        JPanel smallPanel = new JPanel();
        smallPanel.setOpaque(false);
        smallPanel.setLayout(new BoxLayout(smallPanel, BoxLayout.Y_AXIS));
        smallPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        smallPanel.add(Box.createVerticalStrut(8));
        smallPanel.add(email);
        smallPanel.add(Box.createVerticalStrut(12));
        smallPanel.add(password);
        smallPanel.add(Box.createVerticalStrut(8));
        smallPanel.add(forgot);
        smallPanel.add(Box.createVerticalStrut(8));
        smallPanel.add(statusPanel);

        formPanel.add(smallPanel);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(formPanel, gbc);

        JPanel optionPanel = new JPanel();
        optionPanel.setOpaque(false);
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        optionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JCheckBox remember = new JCheckBox("Recordar mi ID y contraseña");
        remember.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        remember.setOpaque(false);

        JCheckBox auto = new JCheckBox("Iniciar sesión automáticamente");
        auto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        auto.setOpaque(false);

        JLabel optionsLink = new JLabel("Opciones");
        optionsLink.setForeground(new Color(21, 94, 190));
        optionsLink.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        optionsLink.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel rememberRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        rememberRow.setOpaque(false);
        rememberRow.add(remember);

        JPanel autoRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        autoRow.setOpaque(false);
        autoRow.add(auto);
        autoRow.add(Box.createHorizontalStrut(8));
        autoRow.add(optionsLink);

        optionPanel.add(rememberRow);
        optionPanel.add(Box.createVerticalStrut(6));
        optionPanel.add(autoRow);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(optionPanel, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 18, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton loginBtn = new JButton("Iniciar sesión");
        loginBtn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        loginBtn.setPreferredSize(new Dimension(150, 38));

        JButton cancelBtn = new JButton("Cancelar");
        cancelBtn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        cancelBtn.setPreferredSize(new Dimension(120, 38));

        buttonPanel.add(loginBtn);
        buttonPanel.add(cancelBtn);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(buttonPanel, gbc);

        JLabel register = new JLabel(
            "<html>¿No tienes un Windows Live ID? <font color='#1b5eb8'>Regístrate</font></html>"
        );
        register.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        register.setHorizontalAlignment(SwingConstants.LEFT);
        register.setAlignmentX(Component.LEFT_ALIGNMENT);
        register.setCursor(new Cursor(Cursor.HAND_CURSOR));

        register.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                register.setText(
                    "<html>¿No tienes un Windows Live ID? <font color='#1b5eb8'><u>Regístrate</u></font></html>"
                );
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(register, gbc);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        footerPanel.setOpaque(false);
        footerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel privacidad = new JLabel("Declaración de privacidad");
        privacidad.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        privacidad.setForeground(new Color(60, 60, 60));

        JLabel terminos = new JLabel("Términos de uso");
        terminos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        terminos.setForeground(new Color(60, 60, 60));

        JLabel estado = new JLabel("Estado del servidor");
        estado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        estado.setForeground(new Color(60, 60, 60));

        JLabel acerca = new JLabel("Acerca de");
        acerca.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        acerca.setForeground(new Color(60, 60, 60));

        footerPanel.add(privacidad);
        footerPanel.add(terminos);
        footerPanel.add(estado);
        footerPanel.add(acerca);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(footerPanel, gbc);

        root.add(card, BorderLayout.CENTER);
        setContentPane(root);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WebServer());
    }
}