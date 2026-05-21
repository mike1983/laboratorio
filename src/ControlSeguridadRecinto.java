public class ControlSeguridadRecinto implements RecintoAsignador {
    @Override
    public String determinarRecinto(Dinosaurio dinosaurio) {
        if (dinosaurio.isEsCarnivoro()) {
            return "Zona de Alta Seguridad (Sector " + (dinosaurio.getEdad() > 10 ? "Alfa" : "Bravo") + ")";
        }
        return "Pradera Abierta (Sector Delta)";
    }
}
