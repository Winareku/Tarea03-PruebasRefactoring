package chainofresponsibility;

public class SoporteLegalHandler extends IncidentHandler {
    @Override
    public boolean canHandle(Incident incident) {
        return incident.isHighSeverity();
    }

    @Override
    protected String getHandlerName() {
        return "Soporte Legal";
    }
}