import java.util.List;
public interface DinosaurioRepository {
    void guardar(Dinosaurio dinosaurio);
    Dinosaurio buscarPorId(String id);
    List<String> obtenerTodosLosIds();
}
