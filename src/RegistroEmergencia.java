import java.time.LocalDateTime;
public class RegistroEmergencia {
    private final String suceso;
    private final String nivelPeligro;
    private final LocalDateTime fechaHora;

    public RegistroEmergencia(String suceso, String nivelPeligro) {
        this.suceso = suceso;
        this.nivelPeligro = nivelPeligro;
        this.fechaHora = LocalDateTime.now();
    }

    // Getters
    public String getSuceso() { return suceso; }
    public String getNivelPeligro() { return nivelPeligro; }
    public LocalDateTime getFechaHora() { return fechaHora; }
}