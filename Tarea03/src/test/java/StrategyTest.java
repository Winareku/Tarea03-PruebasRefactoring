import strategy.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class StrategyTest {

    @Test
    @DisplayName("EmailNotification envía mensaje correctamente")
    void testEmailNotification_Send() {
        EmailNotification emailNotification = new EmailNotification();

        assertDoesNotThrow(() -> emailNotification.send("Test Email", "test@test.com"));
    }

    @Test
    @DisplayName("SMSNotification envía mensaje correctamente")
    void testSMSNotification_Send() {
        SMSNotification smsNotification = new SMSNotification();

        assertDoesNotThrow(() -> smsNotification.send("Test SMS", "+593987654321"));
    }

    @Test
    @DisplayName("PushNotification envía notificación correctamente")
    void testPushNotification_Send() {
        PushNotification pushNotification = new PushNotification();

        assertDoesNotThrow(() -> pushNotification.send("Test Push", "Dispositivo 01"));
    }

    @Test
    @DisplayName("INotification con EmailNotification - Polimorfismo")
    void testInterface_EmailNotificationPolymorphism() {
        INotification notification = new EmailNotification();

        assertDoesNotThrow(() -> notification.send("Mensaje prueba", "usuario@test.com"));

        assertTrue(notification instanceof EmailNotification);
        assertTrue(notification instanceof INotification);
    }

    @Test
    @DisplayName("INotification con SMSNotification - Polimorfismo")
    void testInterface_SMSNotificationPolymorphism() {
        INotification notification = new SMSNotification();

        assertDoesNotThrow(() -> notification.send("Mensaje prueba", "555-1234"));

        assertTrue(notification instanceof SMSNotification);
        assertTrue(notification instanceof INotification);
    }

    @Test
    @DisplayName("INotification con PushNotification - Polimorfismo")
    void testInterface_PushNotificationPolymorphism() {
        INotification notification = new PushNotification();

        assertDoesNotThrow(() -> notification.send("Mensaje prueba", "dispositivo-xyz"));

        assertTrue(notification instanceof PushNotification);
        assertTrue(notification instanceof INotification);
    }
}