import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class JsonAlimentacionRepository implements AlimentacionRepository {
    private final String rutaArchivo;

    public JsonAlimentacionRepository(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void guardarHistorial(RegistroAlimentacion registro) {
        String jsonString = String.format(
                "{\"idRegistro\": \"%s\", \"dinosaurioId\": \"%s\", \"tipoAlimento\": \"%s\", \"cantidadKilos\": %.2f, \"fecha\": \"%s\"}",
                registro.getIdRegistro(), registro.getDinosaurioId(), registro.getTipoAlimento(),
                registro.getCantidadKilos(), registro.getFechaHora()
        );

        try (FileWriter fw = new FileWriter(rutaArchivo, true);
             PrintWriter out = new PrintWriter(fw)) {

            out.println(jsonString);
            System.out.println("[JSON Alimentos] Bitácora de nutrición actualizada.");

        } catch (IOException e) {
            System.err.println("[Error] No se pudo registrar la alimentación: " + e.getMessage());
        }
    }
}
