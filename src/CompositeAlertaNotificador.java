import java.util.ArrayList;
import java.util.List;
public class CompositeAlertaNotificador implements AlertaNotificador {
    private final List<AlertaNotificador> notificadores = new ArrayList<>();

    public void agregarNotificador(AlertaNotificador notificador) {
        this.notificadores.add(notificador);
    }

    @Override
    public void enviarAlerta(String suceso, String nivelPeligro) {
        // Transmite la alerta de forma idéntica a todos los canales registrados
        for (AlertaNotificador notificador : notificadores) {
            notificador.enviarAlerta(suceso, nivelPeligro);
        }
    }
}
