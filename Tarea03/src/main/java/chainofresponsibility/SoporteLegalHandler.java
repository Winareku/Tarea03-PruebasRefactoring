package chainofresponsibility;

public class SoporteLegalHandler extends IncidentHandler {
    @Override
    public boolean canHandle(Incident incident) {
        return incident.getSeverity() > 5;
    }

    @Override
    public void process(Incident incident) {
        System.out.println("Soporte Legal manejando incidente: " + incident.getDescription());
        incident.markResolved();
    }
}