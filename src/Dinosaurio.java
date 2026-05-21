public class Dinosaurio {
    private final String id;
    private final String nombre;
    private final String especie;
    private final int edad;
    private final boolean esCarnivoro;

    public Dinosaurio(String id, String nombre, String especie, int edad, boolean esCarnivoro) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.esCarnivoro = esCarnivoro;
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public int getEdad() { return edad; }
    public boolean isEsCarnivoro() { return esCarnivoro; }
}
