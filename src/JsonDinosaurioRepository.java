import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
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
        } catch (IOException e) {
            System.err.println("[Error] No se pudo registrar: " + e.getMessage());
        }
    }

    @Override
    public Dinosaurio buscarPorId(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Verificamos si esta línea del JSON contiene el ID que buscamos
                if (linea.contains("\"id\": \"" + id + "\"")) {
                    return parsearDinosaurio(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("[Error] No se pudo leer el archivo JSON: " + e.getMessage());
        }
        return null;
    }
    @Override
    public List<String> obtenerTodosLosIds() {
        List<String> listaIds = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String id = extraerValor(linea, "\"id\": \"(.*?)\"");
                if (!id.isEmpty()) {
                    listaIds.add(id);
                }
            }
        } catch (IOException e) {
            System.err.println("[Error] No se pudo leer la lista de dinosaurios: " + e.getMessage());
        }

        return listaIds;
    }
    @Override
    public List<Dinosaurio> obtenerTodos() {
        List<Dinosaurio> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lista.add(parsearDinosaurio(linea)); // Reutiliza el parseador que ya programamos
                }
            }
        } catch (IOException e) {
            System.err.println("[Error] No se pudo leer la lista de dinosaurios: " + e.getMessage());
        }
        return lista;
    }
    // Metodo auxiliar con Expresiones Regulares para extraer los valores del texto JSON
    private Dinosaurio parsearDinosaurio(String json) {
        String id = extraerValor(json, "\"id\": \"(.*?)\"");
        String nombre = extraerValor(json, "\"nombre\": \"(.*?)\"");
        String especie = extraerValor(json, "\"especie\": \"(.*?)\"");
        int edad = Integer.parseInt(extraerValor(json, "\"edad\": (\\d+)"));
        boolean esCarnivoro = Boolean.parseBoolean(extraerValor(json, "\"esCarnivoro\": (true|false)"));

        return new Dinosaurio(id, nombre, especie, edad, esCarnivoro);
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
