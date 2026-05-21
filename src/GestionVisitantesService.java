import java.util.List;
public class GestionVisitantesService {
    private final VisitanteRepository repository;

    public GestionVisitantesService(VisitanteRepository repository) {
        this.repository = repository;
    }

    // Operación 1: Validar si un código de acceso es auténtico (Check-in / Control de accesos)
    public void verificarAcceso(String idVisitante) {
        Visitante visitante = repository.buscarPorId(idVisitante);

        if (visitante == null) {
            System.out.println(String.format("[DENEGADO] El ID %s no corresponde a ningún boleto activo.", idVisitante));
            return;
        }

        System.out.println(String.format("[PERMITIDO] Pase válido verificado. Bienvenido al parque, %s (Edad: %d años).",
                visitante.getNombre(), visitante.getEdad()));
    }

    // Operación 2: Monitoreo/Auditoría de personas registradas
    public void generarReporteOcupacion() {
        List<Visitante> visitantes = repository.obtenerTodos();

        System.out.println("====== REPORTE DE VISITANTES REGISTRADOS ======");
        System.out.println(String.format("Total de personas en historial: %d", visitantes.size()));
        System.out.println("-----------------------------------------------");

        for (Visitante v : visitantes) {
            System.out.println(String.format("- [%s] %s (Rango: %s)",
                    v.getId(),
                    v.getNombre(),
                    v.getEdad() >= 60 ? "Adulto Mayor" : (v.getEdad() < 12 ? "Niño" : "Adulto")
            ));
        }
        System.out.println("===============================================");
    }
}
