import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.*;
import java.util.List;

public class ConversorTemperatura {
    public void ejecutar() {
        String gradoCapturado = null;
        boolean gradoValido = false;

        while (!gradoValido) {
            UIManager.put("OptionPane.okButtonText", "Continuar");
            UIManager.put("OptionPane.cancelButtonText", "Volver");
            gradoCapturado = JOptionPane.showInputDialog("Ingrese un grado:");

            try {
                BigDecimal gradoDeclarado = new BigDecimal(gradoCapturado);
                gradoValido = true;

                String[] opcionesEscalas = {
                        "Celsius",
                        "Fahrenheit",
                        "Kelvin"
                };

                String escalaOrigen = (String) JOptionPane.showInputDialog(
                        null,
                        "Elija la escala de origen:",
                        "Escala de Origen",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        opcionesEscalas,
                        opcionesEscalas[0]
                );

                String escalaDestino = (String) JOptionPane.showInputDialog(
                        null,
                        "Elija la escala a la que desea convertir:",
                        "Escala de Destino",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        opcionesEscalas,
                        opcionesEscalas[0]
                );

                BigDecimal resultado = convertirTemperatura(gradoDeclarado, escalaOrigen, escalaDestino);

                JOptionPane.showMessageDialog(
                        null,
                        "El valor de la conversión es de: " + resultado + " " + escalaDestino,
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

    private BigDecimal convertirTemperatura(BigDecimal grado, String escalaOrigen, String escalaDestino) {
        if (escalaOrigen.equals(escalaDestino)) {
            return grado;
        }

        if (escalaOrigen.equals("Celsius")) {
            if (escalaDestino.equals("Fahrenheit")) {
                return grado.multiply(new BigDecimal("1.8")).add(new BigDecimal("32"));
            } else if (escalaDestino.equals("Kelvin")) {
                return grado.add(new BigDecimal("273.15"));
            }
        } else if (escalaOrigen.equals("Fahrenheit")) {
            if (escalaDestino.equals("Celsius")) {
                return grado.subtract(new BigDecimal("32")).multiply(new BigDecimal("0.5556"));
            } else if (escalaDestino.equals("Kelvin")) {
                return grado.subtract(new BigDecimal("32")).multiply(new BigDecimal("0.5556")).add(new BigDecimal("273.15"));
            }
        } else if (escalaOrigen.equals("Kelvin")) {
            if (escalaDestino.equals("Celsius")) {
                return grado.subtract(new BigDecimal("273.15"));
            } else if (escalaDestino.equals("Fahrenheit")) {
                return grado.subtract(new BigDecimal("273.15")).multiply(new BigDecimal("1.8")).add(new BigDecimal("32"));
            }
        }

        return BigDecimal.ZERO;
    }
}
