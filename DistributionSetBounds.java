import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

public class DistributionSetBounds extends JFrame {

    public DistributionSetBounds() {
        super("Distribution Set Bounds Example");
        metodoVentana();
    }
    private void metodoVentana() {
        setTitle("Distribution Set Bounds Example");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel labelAgenda = new JLabel("Mi agenda");
        labelAgenda.setBounds(230, 20, 100, 25);
        add(labelAgenda);

        JLabel labelNombre = new JLabel("Nombre(s):");
        labelNombre.setBounds(50, 50, 100, 25);
        add(labelNombre);

        JTextField textNombre = new JTextField();
        textNombre.setBounds(50, 80, 400, 25);
        add(textNombre);

        JLabel labelApellido = new JLabel("Apellido(s):");
        labelApellido.setBounds(50, 110, 100, 25);
        add(labelApellido);

        JTextField textApellido = new JTextField();
        textApellido.setBounds(50, 140, 400, 25);
        add(textApellido);

        JLabel labelCorreo = new JLabel("Correo elec:");
        labelCorreo.setBounds(50, 170, 100, 25);
        add(labelCorreo);

        JTextField textCorreo = new JTextField();
        textCorreo.setBounds(50, 200, 150, 25);
        add(textCorreo);

        JLabel labelCelular = new JLabel("Núm de celular:");
        labelCelular.setBounds(300, 170, 100, 25);
        add(labelCelular);

        JTextField textCelular = new JTextField();
        textCelular.setBounds(300, 200, 150, 25);
        add(textCelular);

        JLabel labelFecha = new JLabel("Fecha de nacimiento:");
        labelFecha.setBounds(50, 230, 100, 25);
        add(labelFecha);

        JTextField textFecha = new JTextField();
        textFecha.setBounds(50, 260, 150, 25);
        add(textFecha);

        JLabel labelCalle = new JLabel("Calle y Numero:");
        labelCalle.setBounds(50, 290, 100, 25);
        add(labelCalle);

        JTextField textCalle = new JTextField();
        textCalle.setBounds(50, 320, 400, 25);
        add(textCalle);

        JLabel labelColonia = new JLabel("Fracc o Colonia:");
        labelColonia.setBounds(50, 350, 100, 25);
        add(labelColonia);
        JTextField textColonia = new JTextField();
        textColonia.setBounds(50, 380, 400, 25);
        add(textColonia);

        JButton buttonGuardar = new JButton("Guardar");
        buttonGuardar.setBounds(50, 425, 150, 30);
        add(buttonGuardar);

        JButton buttonCancelar = new JButton("Cancelar");
        buttonCancelar.setBounds(300, 425, 150, 30);
        add(buttonCancelar);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            DistributionSetBounds frame = new DistributionSetBounds();
            frame.setVisible(true);
        });
    }
}