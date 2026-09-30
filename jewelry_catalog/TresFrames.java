import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class TresFrames {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TresFrames::crearFrames);
    }

    private static void crearFrames() {
        JFrame primero = crearFrame(
                "Primer frame",
                "Cerrar este frame no termina el programa",
                300,
                180,
                80,
                80,
                JFrame.DISPOSE_ON_CLOSE
        );

        JFrame segundo = crearFrame(
                "Segundo frame",
                "Cerrar este frame cierra todo el programa",
                450,
                260,
                430,
                120,
                JFrame.EXIT_ON_CLOSE
        );

        JFrame tercero = crearFrame(
                "Tercer frame",
                "Cerrar este frame no termina el programa",
                360,
                220,
                930,
                180,
                JFrame.DISPOSE_ON_CLOSE
        );

        primero.setVisible(true);
        segundo.setVisible(true);
        tercero.setVisible(true);
    }

    private static JFrame crearFrame(
            String titulo,
            String texto,
            int ancho,
            int alto,
            int x,
            int y,
            int operacionCerrar
    ) {
        JFrame frame = new JFrame(titulo);
        frame.setSize(ancho, alto);
        frame.setLocation(x, y);
        frame.setDefaultCloseOperation(operacionCerrar);
        frame.add(new JLabel(texto, SwingConstants.CENTER));
        return frame;
    }
}
