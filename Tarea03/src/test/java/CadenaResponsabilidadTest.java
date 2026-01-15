import chainofresponsibility.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class CadenaResponsabilidadTest {

    private IncidentHandler handlerPrincipal;

    @BeforeEach
    void crearCadena() {
        AnfitrionHandler anfitrionHandler = new AnfitrionHandler();
        ModeradorHandler moderadorHandler = new ModeradorHandler();
        SoporteLegalHandler soporteLegalHandler = new SoporteLegalHandler();

        anfitrionHandler.setNext(moderadorHandler).setNext(soporteLegalHandler);
        handlerPrincipal = anfitrionHandler;
    }

    @Test
    @DisplayName("Incidente de severidad 1")
    void testCadena_IncidenteSeveridad1() {
        Incident incident = new Incident("C01", "Descripción", 1);

        handlerPrincipal.handleIncident(incident);

        assertTrue(incident.isResolved(), "El incidente debería estar resuelto");
    }

    @Test
    @DisplayName("Incidente de severidad 4")
    void testCadena_IncidenteSeveridad4() {
        Incident incident = new Incident("C02", "Descripción", 4);

        handlerPrincipal.handleIncident(incident);

        assertTrue(incident.isResolved(), "El incidente debería estar resuelto");
    }

    @Test
    @DisplayName("Incidente de severidad 7")
    void testCadena_IncidenteSeveridad7() {
        Incident incident = new Incident("C03", "Descripción", 7);

        handlerPrincipal.handleIncident(incident);

        assertTrue(incident.isResolved(), "El incidente debería estar resuelto");
    }

    @Test
    @DisplayName("Incidente de severidad 8 con cadena parcial")
    void testCadena_Parcial_Severidad8() {
        AnfitrionHandler anfitrionHandler = new AnfitrionHandler();
        SoporteLegalHandler soporteLegalHandler = new SoporteLegalHandler();
        anfitrionHandler.setNext(soporteLegalHandler);

        Incident incident = new Incident("C04", "Descripción", 8);

        anfitrionHandler.handleIncident(incident);

        assertTrue(incident.isResolved(),
                "SoporteLegal debería manejar el incidente que Anfitrion no puede");
    }

    @Test
    @DisplayName("Incidente de severidad 5 con cadena incompleta")
    void testCadena_Incompleta_Severidad5() {
        AnfitrionHandler handlerIncompleto = new AnfitrionHandler();
        Incident incident = new Incident("C05", "Descripción", 5);

        handlerIncompleto.handleIncident(incident);

        assertFalse(incident.isResolved(),
                "El incidente no debería estar resuelto si ningún handler lo maneja");
    }
}