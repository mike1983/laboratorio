import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    @Override
    public Visitante buscarPorId(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("\"id\": \"" + id + "\"")) {
                    return parsearVisitante(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("[Error] Error al leer el archivo de visitantes: " + e.getMessage());
        }
        return null; // No encontrado
    }

    @Override
    public List<Visitante> obtenerTodos() {
        List<Visitante> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lista.add(parsearVisitante(linea));
                }
            }
        } catch (IOException e) {
            System.err.println("[Error] Error al listar visitantes: " + e.getMessage());
        }
        return lista;
    }
    // Parseador manual para extraer valores del JSON string
    private Visitante parsearVisitante(String json) {
        String id = extraerValor(json, "\"id\": \"(.*?)\"");
        String nombre = extraerValor(json, "\"nombre\": \"(.*?)\"");
        int edad = Integer.parseInt(extraerValor(json, "\"edad\": (\\d+)"));
        return new Visitante(id, nombre, edad);
    }

    private String extraerValor(String texto, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}
