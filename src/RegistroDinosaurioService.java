public class RegistroDinosaurioService {
    private final DinosaurioRepository repository;
    private final RecintoAsignador recintoAsignador;
    private final IdGenerator idGenerator;

    public RegistroDinosaurioService(DinosaurioRepository repository,
                                     RecintoAsignador recintoAsignador,
                                     IdGenerator idGenerator) {
        this.repository = repository;
        this.recintoAsignador = recintoAsignador;
        this.idGenerator = idGenerator;
    }

    public void registrarDinosaurio(String nombre, String especie, int edad, boolean esCarnivoro) {
        // 1. Generar ID único para el espécimen
        String nuevoId = idGenerator.generarId();

        // 2. Instanciar el objeto
        Dinosaurio dino = new Dinosaurio(nuevoId, nombre, especie, edad, esCarnivoro);

        // 3. Evaluar e indicar medidas de seguridad
        String zonaAsignada = recintoAsignador.determinarRecinto(dino);

        // 4. Persistir en el archivo JSON
        repository.guardar(dino);

        // 5. Imprimir reporte de ingreso técnico
        System.out.println(String.format(
                "=== REPORTE DE INGRESO BIOLÓGICO === \n" +
                        "Código Identificador: %s \n" +
                        "Espécimen: %s (%s) \n" +
                        "Ubicación Asignada: %s\n" +
                        "====================================",
                dino.getId(), dino.getNombre(), dino.getEspecie(), zonaAsignada
        ));
    }
}
