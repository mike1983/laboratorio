public class SecuencialIdGenerator implements IdGenerator {
    private int contador = 0;
    private final String prefijo;

    public SecuencialIdGenerator(String prefijo) {
        this.prefijo = prefijo;
    }

    @Override
    public synchronized String generarId() {
        contador++;
        // Formatea el número con 5 dígitos llenos de ceros a la izquierda
        return String.format("%s-%05d", prefijo, contador);
        // Ejemplo de salida: PRQ-00001
    }
}
