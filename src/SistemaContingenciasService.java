import java.util.Random;
public class SistemaContingenciasService {
    private final AlertaNotificador notificador;
    private final Random random;

    // Matriz con los sucesos y sus respectivos niveles de peligro establecidos por el parque
    private final String[][] sucesos = {
            {"Escape de dinosaurio (¡Posible ataque a turistas detectado!)", "MÁXIMO - CÓDIGO ROJO"},
            {"Apagón Masivo (Sistemas de seguridad y cercas eléctricas caídos)", "CRÍTICO - CÓDIGO NEGRO"},
            {"Tormenta torrencial (Riesgo de inundación y fallas estructurales)", "ALTO - CÓDIGO AMARILLO"}
    };

    public SistemaContingenciasService(AlertaNotificador notificador) {
        this.notificador = notificador;
        this.random = new Random();
    }

    /**
     * Evalúa si ocurre una emergencia basándose en una probabilidad (ej. 30% de probabilidad).
     * Si la suerte lo decide, detona una de las alertas de la lista.
     */
    public void evaluarProbabilidadEmergencia() {
        // Simulamos una probabilidad del 30% de que algo salga mal en este instante
        if (random.nextInt(100) < 30) {
            int indiceSuceso = random.nextInt(sucesos.length);
            String suceso = sucesos[indiceSuceso][0];
            String peligro = sucesos[indiceSuceso][1];

            // Despachamos la alerta usando la abstracción inyectada
            notificador.enviarAlerta(suceso, peligro);
        }
    }
}
