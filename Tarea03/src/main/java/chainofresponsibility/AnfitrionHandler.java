package chainofresponsibility;

public class AnfitrionHandler extends IncidentHandler {
    @Override
    public boolean canHandle(Incident incident) {
        return incident.getSeverity() <= 2;
    }

    @Override
    public void process(Incident incident) {
        System.out.println("Anfitrión manejando incidente: " + incident.getDescription());
        incident.markResolved();
    }
}
