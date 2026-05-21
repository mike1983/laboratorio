import java.time.LocalDateTime;
public class RegistroAlimentacion {
    private final String idRegistro;
    private final String dinosaurioId;
    private final String tipoAlimento;
    private final double cantidadKilos;
    private final LocalDateTime fechaHora;

    public RegistroAlimentacion(String idRegistro, String dinosaurioId, String tipoAlimento, double cantidadKilos) {
        this.idRegistro = idRegistro;
        this.dinosaurioId = dinosaurioId;
        this.tipoAlimento = tipoAlimento;
        this.cantidadKilos = cantidadKilos;
        this.fechaHora = LocalDateTime.now();
    }

    // Getters
    public String getIdRegistro() { return idRegistro; }
    public String getDinosaurioId() { return dinosaurioId; }
    public String getTipoAlimento() { return tipoAlimento; }
    public double getCantidadKilos() { return cantidadKilos; }
    public LocalDateTime getFechaHora() { return fechaHora; }
}
