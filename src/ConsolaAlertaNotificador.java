public class ConsolaAlertaNotificador implements AlertaNotificador {
    @Override
    public void enviarAlerta(String suceso, String nivelPeligro) {
        System.err.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.err.println("   🚨 ALERTA DE EMERGENCIA CRÍTICA DEL SISTEMA 🚨");
        System.err.println("   SUCESO DETECTADO: " + suceso.toUpperCase());
        System.err.println("   NIVEL DE RIESGO:  " + nivelPeligro);
        System.err.println("   ACCION:           Por favor, siga los protocolos de evacuación.");
        System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
    }
}
