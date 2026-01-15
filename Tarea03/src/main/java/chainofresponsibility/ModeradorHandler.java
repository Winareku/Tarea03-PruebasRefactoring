package chainofresponsibility;

public class ModeradorHandler extends IncidentHandler {
    @Override
    protected boolean canHandle(Incident incident) {
        return incident.isMediumSeverity();
    }

    @Override
    protected String getHandlerName() {
        return "Moderador";
    }
}
