package chainofresponsibility;

public class AnfitrionHandler extends IncidentHandler {
    @Override
    public boolean canHandle(Incident incident) {
        return incident.isLowSeverity();
    }

    @Override
    protected String getHandlerName() {
        return "Anfitrión";
    }
}
