import java.util.HashMap;
import java.util.Map;
public class MemoriaVisitanteRepository implements VisitanteRepository {
    private final Map<String, Visitante> baseDatos = new HashMap<>();

    @Override
    public void guardar(Visitante visitante) {
        baseDatos.put(visitante.getId(), visitante);
        System.out.println("[DB] Visitante registrado con éxito: " + visitante.getNombre());
    }
}
