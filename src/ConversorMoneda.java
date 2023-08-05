import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.*;
import java.util.List;
import org.json.JSONObject;

public class ConversorMoneda {
    private Moneda moneda;

    public ConversorMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public void ejecutar() {
        String valorCapturado = null;
        boolean valorValido = false;

        while (!valorValido) {
            UIManager.put("OptionPane.okButtonText", "Continuar");
            UIManager.put("OptionPane.cancelButtonText", "Volver");
            valorCapturado = JOptionPane.showInputDialog("Ingrese un valor:");

            try {
                BigDecimal valorDeclarado = new BigDecimal(valorCapturado);
                valorValido = true;

                List<String> opcionesMonedas = moneda.obtenerMonedasDisponibles();

                String monedaOrigen = (String) JOptionPane.showInputDialog(
                        null,
                        "Elija la moneda de origen:",
                        "Moneda de Origen",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        opcionesMonedas.toArray(),
                        opcionesMonedas.get(0)
                );

                String monedaDestino = (String) JOptionPane.showInputDialog(
                        null,
                        "Elija la moneda a la que desea convertir:",
                        "Moneda de Destino",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        opcionesMonedas.toArray(),
                        opcionesMonedas.get(0)
                );

                BigDecimal tasaOrigen = moneda.obtenerTasa(monedaOrigen);
                BigDecimal tasaDestino = moneda.obtenerTasa(monedaDestino);

                BigDecimal valorConvertido = valorDeclarado.multiply(tasaOrigen).divide(tasaDestino, 2, RoundingMode.DOWN);
                JOptionPane.showMessageDialog(
                        null,
                        "El valor de la conversión es de: " + valorConvertido + " " + monedaDestino,
                        "Resultado de Conversión",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (NumberFormatException e) {
                UIManager.put("OptionPane.okButtonText", "Intentar de nuevo");
                JOptionPane.showMessageDialog(
                        null,
                        "Ingrese solo números y use '.' para decimales.",
                        "Valor inválido",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
