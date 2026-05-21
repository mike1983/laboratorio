import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class SistemaContingenciasService {
    private final AlertaNotificador notificador;
    private final List<String> listaSucesos;
    private final Map<String, String> mapaSucesos;
    private final Random random;

    // Inyectamos el cargador de configuración por medio del constructor
    public SistemaContingenciasService(AlertaNotificador notificador, ConfiguracionEmergenciaLoader loader) {
        this.notificador = notificador;
        this.mapaSucesos = loader.cargarSucesos();
        this.listaSucesos = new ArrayList<>(mapaSucesos.keySet()); // Lista de llaves para acceso aleatorio por índice
        this.random = new Random();
    }

    public void evaluarProbabilidadEmergencia() {
        // Si el archivo de configuración está vacío o no se leyó, salimos del proceso de forma segura
        if (listaSucesos.isEmpty()) return;

        // Mantenemos una probabilidad simulada del 30%
        if (random.nextInt(100) < 30) {
            // Seleccionamos un índice aleatorio basado en el archivo de configuración dinámico
            int indiceAleatorio = random.nextInt(listaSucesos.size());
            String sucesoDetectado = listaSucesos.get(indiceAleatorio);
            String nivelPeligro = mapaSucesos.get(sucesoDetectado);

            // Disparamos la alerta
            notificador.enviarAlerta(sucesoDetectado, nivelPeligro);
        }
    }
}
