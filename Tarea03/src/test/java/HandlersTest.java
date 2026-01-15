import chainofresponsibility.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class HandlersTest {

    @Test
    @DisplayName("Incidente de severidad 2")
    void testAnfitrionHandler_Severidad2() {
        AnfitrionHandler handler = new AnfitrionHandler();
        Incident incident = new Incident("A01", "Descripción", 2);
        assertTrue(handler.canHandle(incident));
    }

    @Test
    @DisplayName("Incidente de severidad 3")
    void testAnfitrionHandler_Severidad3() {
        AnfitrionHandler handler = new AnfitrionHandler();
        Incident incident = new Incident("A02", "Descripción", 3);
        assertFalse(handler.canHandle(incident));
    }

    @Test
    @DisplayName("Incidente de severidad 4")
    void testModeradorHandler_Severidad4() {
        ModeradorHandler handler = new ModeradorHandler();
        Incident incident = new Incident("H01", "Descripción", 4);
        assertTrue(handler.canHandle(incident));
    }

    @Test
    @DisplayName("Incidente de severidad 6")
    void testModeradorHandler_Severidad6() {
        ModeradorHandler handler = new ModeradorHandler();
        Incident incident = new Incident("H02", "Descripción", 6);
        assertFalse(handler.canHandle(incident));
    }

    @Test
    @DisplayName("Incidente de severidad 7")
    void testSoporteLegalHandler_Severidad7() {
        SoporteLegalHandler handler = new SoporteLegalHandler();
        Incident incident = new Incident("S01", "Descripción", 7);
        assertTrue(handler.canHandle(incident));
    }

    @Test
    @DisplayName("Incidente de severidad 5")
    void testSoporteLegalHandler_Severidad5() {
        SoporteLegalHandler handler = new SoporteLegalHandler();
        Incident incident = new Incident("S02", "Descripción", 5);
        assertFalse(handler.canHandle(incident));
    }
}