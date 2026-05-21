public class ParqueDietaCalculadora implements DietaCalculadora {

    @Override
    public double calcularKilosRequeridos(Dinosaurio dinosaurio) {

        double multiplicadorEdad = dinosaurio.getEdad() * 1.5;
        if (dinosaurio.isEsCarnivoro()) {
            return 50.0 + (multiplicadorEdad * 3); // Base 50kg + extra por edad
        }
        return 30.0 + (multiplicadorEdad * 2); // Base 30kg + extra por edad
    }

    @Override
    public String determinarTipoAlimento(Dinosaurio dinosaurio) {
        return dinosaurio.isEsCarnivoro() ? "Carne de Res/Cabrito" : "Follaje de Helechos/Plantas altas";
    }
}