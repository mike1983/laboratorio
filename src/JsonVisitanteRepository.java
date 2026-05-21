import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class JsonVisitanteRepository implements VisitanteRepository{
    private final String rutaArchivo;
    public JsonVisitanteRepository(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
    @Override
    public void guardar(Visitante visitante) {

        String jsonString = String.format(
                "{\"id\": \"%s\", \"nombre\": \"%s\", \"edad\": %d}",
                visitante.getId(),
                visitante.getNombre(),
                visitante.getEdad()
        );


        try (FileWriter fw = new FileWriter(rutaArchivo, true);
             PrintWriter out = new PrintWriter(fw)) {

            out.println(jsonString);
            System.out.println("[JSON File] Registro guardado exitosamente en " + rutaArchivo);

        } catch (IOException e) {
            System.err.println("[Error] No se pudo guardar en el archivo JSON: " + e.getMessage());
        }
    }
}
