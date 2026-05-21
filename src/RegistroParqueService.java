import java.time.LocalDateTime;
public class RegistroParqueService {
    private final VisitanteRepository repository;
    private final TarifaCalculadora tarifaCalculadora;
    private final IdGenerator idGenerator;

    public RegistroParqueService(VisitanteRepository repository,
                                 TarifaCalculadora tarifaCalculadora,
                                 IdGenerator idGenerator) {
        this.repository = repository;
        this.tarifaCalculadora = tarifaCalculadora;
        this.idGenerator = idGenerator;
    }

    public void registrarIngreso(String nombre, int edad) {
        // 1. Generar el ID único de forma automática
        String nuevoId = idGenerator.generarId();

        // 2. Construir el objeto Visitante
        Visitante visitante = new Visitante(nuevoId, nombre, edad);

        // 3. Calcular tarifa y guardar
        double costo = tarifaCalculadora.calcularPrecio(visitante);
        repository.guardar(visitante);

        // 4. Imprimir Ticket
        System.out.println(String.format(
                "--- TICKET DE INGRESO --- \n" +
                        "ID Acceso: %s \n" +
                        "Visitante: %s \n" +
                        "Total: $%s\n" +
                        "-------------------------",
                visitante.getId(), visitante.getNombre(), costo
        ));
    }
}
