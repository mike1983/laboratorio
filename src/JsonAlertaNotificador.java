import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class JsonAlertaNotificador implements AlertaNotificador {
    private final String rutaArchivo;

    public JsonAlertaNotificador(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void enviarAlerta(String suceso, String nivelPeligro) {
        // 1. Instanciamos el registro con la hora exacta de la falla
        RegistroEmergencia registro = new RegistroEmergencia(suceso, nivelPeligro);

        // 2. Formateamos a una estructura JSON clásica en Java puro
        String jsonString = String.format(
                "{\"fechaHora\": \"%s\", \"suceso\": \"%s\", \"nivelPeligro\": \"%s\"}",
                registro.getFechaHora(),
                registro.getSuceso().replace("\"", "\\\""), // Escapamos comillas por seguridad
                registro.getNivelPeligro()
        );

        // 3. Escribimos la contingencia en la bitácora física de incidentes
        try (FileWriter fw = new FileWriter(rutaArchivo, true);
             PrintWriter out = new PrintWriter(fw)) {

            out.println(jsonString);
            System.out.println("[🚨 CAJA NEGRA] Incidente crítico respaldado de forma segura en " + rutaArchivo);

        } catch (IOException e) {
            System.err.println("[💥 Error Crítico] La alarma falló al intentar escribir en la bitácora JSON: " + e.getMessage());
        }
    }
}