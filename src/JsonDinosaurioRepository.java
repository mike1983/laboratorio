import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class JsonDinosaurioRepository implements DinosaurioRepository {
    private final String rutaArchivo;

    public JsonDinosaurioRepository(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void guardar(Dinosaurio dinosaurio) {
        String jsonString = String.format(
                "{\"id\": \"%s\", \"nombre\": \"%s\", \"especie\": \"%s\", \"edad\": %d, \"esCarnivoro\": %b}",
                dinosaurio.getId(), dinosaurio.getNombre(), dinosaurio.getEspecie(), dinosaurio.getEdad(), dinosaurio.isEsCarnivoro()
        );

        try (FileWriter fw = new FileWriter(rutaArchivo, true);
             PrintWriter out = new PrintWriter(fw)) {

            out.println(jsonString);
            System.out.println("[JSON Dino] Historial actualizado en " + rutaArchivo);

        } catch (IOException e) {
            System.err.println("[Error] No se pudo registrar al dinosaurio: " + e.getMessage());
        }
    }
}
