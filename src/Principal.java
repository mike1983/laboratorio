import java.util.Scanner;

public class Principal {
    public static  void main (String[] args)
    {
        Scanner entrada = new Scanner(System.in);
        int opcion = 0;


        while (opcion != 6) {
            System.out.println("\n========================================");
            System.out.println("    SISTEMA DE GESTIÓN DEL PARQUE JURASICO");
            System.out.println("========================================");
            System.out.println("1. Registro de dinosaurios");
            System.out.println("2. Control de alimentación y estado");
            System.out.println("3. Gestión de visitantes");
            System.out.println("4. Venta de entradas");
            System.out.println("5. Reportes");
            System.out.println("6. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opción (1-6): ");


            if (entrada.hasNextInt()) {
                opcion = entrada.nextInt();
                entrada.nextLine();
                System.out.println();


                switch (opcion) {
                    case 1:
                        menuRegistroDinosaurios(entrada);
                        break;
                    case 2:
                        menuControlAlimentacion(entrada);
                        break;
                    case 3:
                        menuGestionVisitantes(entrada);
                        break;
                    case 4:
                        menuVentaEntradas(entrada);
                        break;
                    case 5:
                        menuReportes(entrada);
                        break;
                    case 6:
                        System.out.println("Cerrando el sistema... ¡Buen día, guardián del parque!");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elija un número entre 1 y 6.");
                }
            } else {
                System.out.println("\n[Error] Debe ingresar un número válido.");
                entrada.nextLine(); // Limpiar el búfer si el usuario ingresó letras
            }
        }

        entrada.close();
    }

    private static void menuRegistroDinosaurios(Scanner entrada) {
        System.out.println("--- REGISTRO DE DINOSAURIOS ---");
        DinosaurioRepository dinoRepo = new JsonDinosaurioRepository("dinosaurios.json");
        RecintoAsignador seguridad = new ControlSeguridadRecinto();
        IdGenerator idDinosaurios = new SecuencialIdGenerator("DINO");
        RegistroDinosaurioService servicioDinos = new RegistroDinosaurioService(dinoRepo, seguridad, idDinosaurios);
        int opc=1,alimentacion=1;
        String nombre="";
        String especie="";
        int edad=0;
        boolean carniboro=false;
        while(opc!=2)
        {
            System.out.println("Ingrese el nombre del dinosaurio");
            nombre=entrada.nextLine();
            System.out.println("Ingrese la especie del dinosario");
            nombre=entrada.nextLine();
            System.out.println("Ingrese la edad del dinosaurio");
            edad=entrada.nextInt();
            System.out.println("Es carniboro 1:si, 2:no");
            alimentacion=entrada.nextInt();
            if (alimentacion==1)
                carniboro=true;
            entrada.nextLine();
            servicioDinos.registrarDinosaurio(nombre, especie, edad, carniboro);
            System.out.println("Desea ingresar otro dinosaurio 1:Si 2:No");
            opc =entrada.nextInt();
            entrada.nextLine();
            System.out.println();
        }
    }

    private static void menuControlAlimentacion(Scanner entrada) {
        System.out.println("--- CONTROL DE ALIMENTACIÓN Y ESTADO ---");
        System.out.println("Monitoreando niveles de hambre y salud de los recintos...");

    }

    private static void menuGestionVisitantes(Scanner entrada) {
        System.out.println("--- GESTIÓN DE VISITANTES ---");
        System.out.println("Gestion de actividades de los turistas...");

    }

    private static void menuVentaEntradas(Scanner entrada) {
        System.out.println("--- VENTA DE ENTRADAS ---");
        System.out.println("Procesando nueva venta de boletos...");
        VisitanteRepository repository = new JsonVisitanteRepository("visitantes.json");
        TarifaCalculadora calculadora = new ParqueTarifaCalculadora();
        IdGenerator generadorIds = new SecuencialIdGenerator("PARQ");
        RegistroParqueService servicioParque = new RegistroParqueService(repository, calculadora, generadorIds);
        int opc=1;
        String nombre="";
        int edad=0;
        while(opc!=2)
        {
            System.out.println("Ingrese el nombre del visitante");
            nombre=entrada.nextLine();
            System.out.println("Ingrese la edad del visitante");
            edad=entrada.nextInt();
            servicioParque.registrarIngreso(nombre, edad);
            entrada.nextLine();
            System.out.println("Desea ingresar otro visitante 1:Si 2:No");
            opc =entrada.nextInt();
            entrada.nextLine();
            System.out.println();
        }
    }

    private static void menuReportes(Scanner entrada) {
        System.out.println("--- REPORTES GENERALES ---");
        System.out.println("Generando balance de incidentes, ingresos y estado del parque...");
    }

}
