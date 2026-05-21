import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReporteDinosauriosPrinter {
    private final DinosaurioRepository repository;

    public ReporteDinosauriosPrinter(DinosaurioRepository repository) {
        this.repository = repository;
    }

    public void imprimirReporteConsola() {
        List<Dinosaurio> dinosaurios = repository.obtenerTodos();
        String fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        // Cabecera del Reporte
        System.out.println("=========================================================================");
        System.out.println("                      REPORTE DE INVENTARIO BIOLÓGICO                    ");
        System.out.println("                      Fecha de Emisión: " + fechaActual);
        System.out.println("=========================================================================");

        // Formato de columnas: ID (12 caracteres), Nombre (15), Especie (22), Edad (6), Dieta (12)
        String formatoColumnas = "| %-10s | %-13s | %-20s | %-4s | %-10s |\n";

        String result= String.format(formatoColumnas, "ID", "NOMBRE", "ESPECIE", "EDAD", "DIETA");
        System.out.println(result);
        System.out.println("-------------------------------------------------------------------------");

        int conteoCarnivoros = 0;
        result="";
        // Cuerpo de la tabla
        for (Dinosaurio d : dinosaurios) {
            String dieta = d.isEsCarnivoro() ? "Carnívoro" : "Herbívoro";
            if (d.isEsCarnivoro()) conteoCarnivoros++;

            result=  String.format(formatoColumnas,
                    d.getId(),
                    d.getNombre(),
                    d.getEspecie(),
                    d.getEdad() + "a",
                    dieta
            );
            System.out.println(result);
        }

        // Resumen estadístico (Pie del reporte)
        System.out.println("=========================================================================");
        System.out.println(" Resumen de Activos:");
        System.out.println("   - Total de Ejemplares: " + dinosaurios.size());
        System.out.println("   - Ejemplares Carnívoros: " + conteoCarnivoros);
        System.out.println("   - Ejemplares Herbívoros: " + (dinosaurios.size() - conteoCarnivoros));
        System.out.println("=========================================================================");
    }
}