import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.util.List;

public class AplicacionConversor {

    public static void main(String[] args) {
        UIManager.put("OptionPane.okButtonText", "Continuar");
        UIManager.put("OptionPane.cancelButtonText", "Salir");

        Moneda moneda = new Moneda();

        String[] opciones = { "Conversor de Moneda", "Conversor de Temperatura" };
        boolean bucle = true;

        while (bucle) {
            String opcionSeleccionada = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione una opción:",
                    "Menú",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (opcionSeleccionada == null) {
                bucle = false;
                continue;
            }




                switch (opcionSeleccionada) {
                    case "Conversor de Moneda":
                        ConversorMoneda conversorMoneda1 = new ConversorMoneda(moneda);
                        conversorMoneda1.ejecutar();
                        break;

                    case "Conversor de Temperatura":
                        ConversorTemperatura conversorTemperatura = new ConversorTemperatura();
                        conversorTemperatura.ejecutar();
                        break;

                    default:
                        JOptionPane.showMessageDialog(
                                null,
                                "Opción no válida.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        break;
                }

// ...



        }
    }
}
