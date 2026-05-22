Sistema de Gestión y Contingencias del Parque (Jurassic Park CMS)
Este proyecto es un simulador de backend desarrollado en Java Puro (sin frameworks externos) y siguiendo los principios SOLID
El sistema simula las operaciones diarias de un parque de atracciones de alta seguridad, integrando un motor dinámico de contingencias y catástrofes aleatorias.
Características Principales

Generación de Identificadores Únicos: Algoritmo secuencial parametrizado con prefijos personalizados (PARQ-00001).
Cálculo de Tarifas Automatizado: Estructura basada en reglas de negocio centralizadas para procesar los totales de los tickets.
Persistencia de Clientes: Guardado directo de la información de los turistas en formato estructurado.

Motor de Contingencias (Módulo de Emergencias)
Evaluación de Probabilidades: Motor que calcula dinámicamente si ocurre una catástrofe basada en una tasa de probabilidad configurable.
El catálogo de incidentes no está cableado en código; se lee en tiempo de ejecución desde un archivo .properties externo. Puedes añadir amenazas como Escape de dinosaurios, Apagón Masivo o Tormentas torrenciales sin recompilar la aplicación.
Persistencia incremental con marcas de tiempo de segundos de cada fallo en un archivo histórico JSON.
Arquitectura y Principios SOLID Aplicados
El software se diseñó bajo el enfoque de desacoplamiento absoluto, garantizando que los cambios en la infraestructura tecnológica no afecten las reglas de negocio del parque:
S - Single Responsibility Principle (SRP): Cada clase tiene una única e inequívoca responsabilidad de cambio. El cargador de propiedades solo lee el disco; el notificador JSON solo formatea y escribe texto; el servicio de contingencias solo computa matemáticas de probabilidad.
O - Open/Closed Principle (OCP): Gracias al uso de abstracciones y patrones como Composite, el sistema es infinitamente extensible pero cerrado a modificaciones.
├── src/
│   ├── Main.java                        # Orquestador del sistema (Dependency Injection Container)
│   │
│   ├─── core/                           # Módulo de operaciones diarias y taquilla
│   │    ├── VisitanteRepository.java    # Interfaz para almacenamiento de visitantes
│   │    ├── JsonVisitanteRepository.java# Persistencia de clientes en archivos JSON
│   │    ├── TarifaCalculadora.java      # Interfaz de lógica financiera de boletos
│   │    ├── ParqueTarifaCalculadora.java# Implementación de reglas de precios
│   │    ├── IdGenerator.java            # Interfaz de generación de códigos de barra
│   │    ├── SecuencialIdGenerator.java  # Generador de folios con prefijo incremental
│   │    └── RegistroParqueService.java  # Servicio orquestador de ingresos al parque
│   │
│   └─── emergencias/                    # Módulo de control de crisis y seguridad perimetral
│        ├── AlertaNotificador.java      # Interfaz para canales de transmisión de alarmas
│        ├── ConsolaAlertaNotificador.java# Canal visual para terminal de operadores
│        ├── JsonAlertaNotificador.java   # Grabador físico del histórico de desastres (JSON)
│        ├── CompositeAlertaNotificador.java# Despachador multicanal simultáneo
│        ├── ConfiguracionEmergenciaLoader.java # Interfaz para lecturas de catálogos
│        ├── PropertiesEmergenciaLoader.java # Lector concreto de archivos de configuración
│        ├── SistemaContingenciasService.java # Motor de evaluación probabilística
│        └── RegistroEmergencia.java     # Entidad modelo del incidente para la caja negra
│
├── emergencias.properties               # Catálogo externo de riesgos editables
├── visitantes.json                      # Archivo autogenerado con los registros de taquilla
├── dinosaurios.json                     # Archivo autogenerado con los registros de Dinosaurios en el parque
├── historial_alimentacion.json          # Archivo autogenerado con los registros de la alimentacion de los Dinosaurios en el parque
└── historial_emergencias.json           # Archivo autogenerado con el historial de catástrofes

Configuración e Instalación
Requisitos del Entorno 
Java Development Kit (JDK): Versión 17 instalado y configurado en tus variables de entorno.
Cualquier editor de texto o IDE estándar (IntelliJ IDEA, Eclipse, VS Code).

Archivo de Configuración de Crisis (emergencias.properties)
Puedes personalizar el catálogo de desastres editando directamente el archivo de propiedades en la raíz del proyecto. El formato consiste en pares de suceso=nivel_de_peligro:
# Archivo de Configuración de Catástrofes del Parque
Escape\ de\ dinosaurio\ (posible\ ataque\ a\ turista)=MÁXIMO - CÓDIGO ROJO
Apagón\ Masivo\ (sistemas\ de\ seguridad\ caídos)=CRÍTICO - CÓDIGO NEGRO
Tormenta\ torrencial\ con\ inundaciones=ALTO - CÓDIGO AMARILLO
Falla\ en\ los\ laboratorios\ genéticos=MEDIO - CÓDIGO NARANJA
-----------------------------------------------------------------------------------------------------------------
Demostración del Comportamiento en Producción
Al ejecutar el flujo, el sistema procesa los accesos del personal y clientes de prueba. En cada iteración, el motor perimetral evalúa los sensores de seguridad, lo que genera salidas como esta de forma dinámica:

========================================
    SISTEMA DE GESTIÓN DEL PARQUE JURASICO
========================================
1. Registro de dinosaurios
2. Control de alimentación y estado
3. Gestión de visitantes
4. Venta de entradas
5. Reportes
6. Salir
========================================
Seleccione una opción (1-6): 5

--- REPORTES GENERALES ---
=========================================================================
                      REPORTE DE INVENTARIO BIOLÓGICO                    
                      Fecha de Emisión: 21/05/2026 17:45:00
=========================================================================
| ID         | NOMBRE        | ESPECIE              | EDAD | DIETA      |

-------------------------------------------------------------------------
| DINO-00001 | cuernos       | triceratops          | 12a  | Herbívoro  |

| DINO-00002 | muelas        | brachiosaurus        | 23a  | Herbívoro  |

| DINO-00003 | velociraptor  | raptor               | 12a  | Carnívoro  |

=========================================================================
 Resumen de Activos:
   - Total de Ejemplares: 3
   - Ejemplares Carnívoros: 1
   - Ejemplares Herbívoros: 2
=========================================================================

Presione ENTER para regresar al menu...


!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
   🚨 ALERTA DE EMERGENCIA CRÍTICA DEL SISTEMA 🚨
   SUCESO DETECTADO: TORMENTA TORRENCIAL CON INUNDACIONES
   NIVEL DE RIESGO:  ALTO - CÃDIGO AMARILLO
   ACCION:           Por favor, siga los protocolos de evacuación.
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

[🚨 CAJA NEGRA] Incidente crítico respaldado de forma segura en historial_emergencias.json
[Config] Configuración de emergencias cargada desde: emergencias.properties
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Formato de Salida de los Archivos del Sistema
visitantes.json 
{"idAcceso": "PARQ-00001", "nombre": "Ian Malcolm", "totalPagado": 100.0}
{"idAcceso": "PARQ-00002", "nombre": "Dennis Nedry", "totalPagado": 100.0}

dinosaurios.json
{"id": "DINO-00001", "nombre": "cuernos", "especie": "triceratops", "edad": 12, "esCarnivoro": false}
{"id": "DINO-00002", "nombre": "muelas", "especie": "brachiosaurus", "edad": 23, "esCarnivoro": false}
{"id": "DINO-00003", "nombre": "velociraptor", "especie": "raptor", "edad": 12, "esCarnivoro": true}

historial_alimentacion.json
{"idRegistro": "FEED-00001", "dinosaurioId": "DINO-00001", "tipoAlimento": "Carne de Res/Cabrito", "cantidadKilos": 302.00, "fecha": "2026-05-21T15:42:42.292937100"}
{"idRegistro": "FEED-00002", "dinosaurioId": "DINO-00002", "tipoAlimento": "Carne de Res/Cabrito", "cantidadKilos": 203.00, "fecha": "2026-05-21T15:42:42.293937800"}

historial_emergencias.json
{"fechaHora": "2026-05-21T17:45:01.723970400", "suceso": "Tormenta torrencial con inundaciones", "nivelPeligro": "ALTO - CÃDIGO AMARILLO"}
{"fechaHora": "2026-05-21T17:45:09.851743100", "suceso": "Escape de dinosaurio (posible ataque a turista)", "nivelPeligro": "MÃXIMO - CÃDIGO ROJO"}

Licencia
Este proyecto está bajo la Licencia MIT. Eres libre de utilizarlo, modificarlo y distribuirlo con fines didácticos o como plantilla base para tus proyectos empresariales construidos bajo metodologías limpias de desarrollo.
