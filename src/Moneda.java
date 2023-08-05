import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class Moneda {
    private static final String URL_API = "http://api.exchangeratesapi.io/v1/latest?access_key=50d35bf79afe3c9c261bb966db77c459";
    private JSONObject tasas;

    public Moneda() {
        try {
            URL url = new URL(URL_API);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader lector = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String respuesta = lector.readLine();
            lector.close();

            JSONObject respuestaJson = new JSONObject(respuesta);
            tasas = respuestaJson.getJSONObject("rates");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BigDecimal obtenerTasa(String moneda) {
        return tasas.getBigDecimal(moneda);
    }

    public List<String> obtenerMonedasDisponibles() {
        List<String> monedas = new ArrayList<>();
        Iterator<String> keys = tasas.keys();
        while (keys.hasNext()) {
            monedas.add(keys.next());
        }
        return monedas;
    }
}
