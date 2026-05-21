public class ParqueTarifaCalculadora implements TarifaCalculadora{
    private static final double TARIFA_BASE = 100.0;

    @Override
    public double calcularPrecio(Visitante visitante) {
        // Descuento para niños pequeños o adultos mayores
        if (visitante.getEdad() < 5 || visitante.getEdad() >= 60) {
            return TARIFA_BASE * 0.5; // 50% de descuento
        }
        return TARIFA_BASE;
    }
}
