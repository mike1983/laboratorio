public class ControlAlimentacionService{
    private final AlimentacionRepository alimentacionRepository;
    private final DinosaurioRepository dinosaurioRepository; // Dependencia agregada
    private final DietaCalculadora dietaCalculadora;
    private final IdGenerator idGenerator;

    public ControlAlimentacionService(AlimentacionRepository alimentacionRepository,
                                      DinosaurioRepository dinosaurioRepository,
                                      DietaCalculadora dietaCalculadora,
                                      IdGenerator idGenerator) {
        this.alimentacionRepository = alimentacionRepository;
        this.dinosaurioRepository = dinosaurioRepository;
        this.dietaCalculadora = dietaCalculadora;
        this.idGenerator = idGenerator;
    }

    public void alimentarDinosaurioPorId(String idDinosaurio) {
        // 1. Ir a buscar el dinosaurio al archivo JSON
        Dinosaurio dinosaurio = dinosaurioRepository.buscarPorId(idDinosaurio);

        // Validación en caso de que no exista en el archivo
        if (dinosaurio == null) {
            System.err.println(String.format("[Alerta] Operación cancelada. El dinosaurio con ID %s no fue encontrado en el sistema.", idDinosaurio));
            return;
        }

        // 2. Procesar la dieta utilizando los datos recuperados del JSON
        String alimento = dietaCalculadora.determinarTipoAlimento(dinosaurio);
        double kilos = dietaCalculadora.calcularKilosRequeridos(dinosaurio);
        String idOrden = idGenerator.generarId();

        // 3. Guardar en la bitácora de alimentos
        RegistroAlimentacion registro = new RegistroAlimentacion(idOrden, dinosaurio.getId(), alimento, kilos);
        alimentacionRepository.guardarHistorial(registro);

        // 4. Imprimir la orden de trabajo
        System.out.println(String.format(
                "==== ORDEN DE ALIMENTACIÓN PROCESADA ==== \n" +
                        "Código de Orden: %s \n" +
                        "Dinosaurio Recuperado del JSON: %s (%s) \n" +
                        "Menú Asignado: %s \n" +
                        "Porción Requerida: %.2f Kg\n" +
                        "=========================================",
                registro.getIdRegistro(), dinosaurio.getNombre(), dinosaurio.getEspecie(),
                registro.getTipoAlimento(), registro.getCantidadKilos()
        ));
    }
}