import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
public class PropertiesEmergenciaLoader implements ConfiguracionEmergenciaLoader {
    private final String rutaArchivo;

    public PropertiesEmergenciaLoader(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public Map<String, String> cargarSucesos() {
        Map<String, String> mapaSucesos = new HashMap<>();
        Properties propiedades = new Properties();

        try (FileInputStream fis = new FileInputStream(rutaArchivo)) {
            propiedades.load(fis);

            // Convertimos las propiedades de Java a un mapa nativo
            for (String llave : propiedades.stringPropertyNames()) {
                mapaSucesos.put(llave, propiedades.getProperty(llave));
            }
            System.out.println("[Config] Configuración de emergencias cargada desde: " + rutaArchivo);

        } catch (IOException e) {
            System.err.println("[Error] No se pudo leer el archivo de configuración, usando catálogo vacío: " + e.getMessage());
        }

        return mapaSucesos;
    }
}
