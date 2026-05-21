import java.util.List;
public interface VisitanteRepository {
    void guardar(Visitante visitante);
    Visitante buscarPorId(String id); // Para buscar un boleto/ID
    List<Visitante> obtenerTodos(); //para mostrar todos los boletos
}
